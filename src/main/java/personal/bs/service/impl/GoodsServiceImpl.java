package personal.bs.service.impl;


import com.alibaba.fastjson.JSON;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import freemarker.template.Configuration;
import freemarker.template.Template;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;
import personal.bs.dao.mapper.*;
import personal.bs.domain.dto.GoodsDescDto;
import personal.bs.domain.dto.GoodsDto;
import personal.bs.domain.dto.SkuPODto;
import personal.bs.domain.po.*;
import personal.bs.domain.vo.GoodsVO;
import personal.bs.domain.vo.PageResult;
import personal.bs.service.GoodsService;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileWriter;
import java.io.Writer;
import java.util.*;


@Service
@Transactional
@Slf4j
public class GoodsServiceImpl implements GoodsService {


    @Resource
    private FreeMarkerConfigurer freeMarkerConfigurer;

    @Value("${PAGE_PATH}")
    private String PAGE_PATH;

    @Resource
    private StorePOMapper storePOMapper;

    @Resource
    private SpuPOMapper spuPOMapper;

    @Resource
    private TbGoodsDescMapper goodsDescMapper;

    @Resource
    private TypePOMapper typePOMapper;

    @Resource
    private SkuPOMapper skuPOMapper;

    @Override
    public boolean genSkuHtml(Integer spuId) {
        Configuration configuration = freeMarkerConfigurer.getConfiguration();
        try {
            Template template = configuration.getTemplate("item.ftl");
            //创建数据模型
            Map dataModel = new HashMap<>();
            //1.商品主表数据
            SpuPO goods = spuPOMapper.selectByPrimaryKey(spuId);
            dataModel.put("goods", goods);
            //2.商品扩展表数据
            TbGoodsDesc goodsDesc = goodsDescMapper.selectByPrimaryKey(spuId);
            dataModel.put("goodsDesc", goodsDesc);
            //3.读取商品分类
            TypePO typePO1 = typePOMapper.selectByPrimaryKey(goods.getType1Id());
            TypePO typePO2 = typePOMapper.selectByPrimaryKey(goods.getType2Id());
            dataModel.put("type1", typePO1);
            dataModel.put("type2", typePO2);
            //4.读取SKU列表
            SkuPOExample example = new SkuPOExample();
            SkuPOExample.Criteria criteria = example.createCriteria();
            //SPU ID
            criteria.andSpuIdEqualTo(spuId);
            //criteria.("1");//状态有效
            //按是否默认字段进行降序排序，目的是返回的结果第一条为默认SKU
            example.setOrderByClause("is_default desc");

            List<SkuPO> itemList = skuPOMapper.selectByExample(example);
            // List<String> imgList = Arrays.asList(itemList.get(0).getImgUrl().split(","));
            dataModel.put("itemList", itemList);
            log.warn("[" + itemList.get(0).getImgUrl() + "]");

            //TODO

            ArrayList<String[]> img = new ArrayList<>();

            for (SkuPO skuPO : itemList) {
                if (skuPO.getImgUrl() != null) {
                    String[] split = skuPO.getImgUrl().split(",");
                    img.add(split);
                }
            }

            Writer out = new FileWriter(PAGE_PATH + spuId + ".html");
            dataModel.put("img", img);
            template.process(dataModel, out);
            //输出
            out.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }

    @Override
    public boolean deleteItemHtml(Integer[] goodsIds) {
        try {
            for (Integer goodsId : goodsIds) {
                new File(PAGE_PATH + goodsId + ".html").delete();
            }
            return true;
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 查询全部
     */
    @Override
    public List<SpuPO> findAll() {
        return spuPOMapper.selectByExample(null);
    }

    /**
     * 按分页查询
     */
    @Override
    public PageResult findPage(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        Page<SpuPO> page = (Page<SpuPO>) spuPOMapper.selectByExample(null);
        return new PageResult(page.getTotal(), page.getResult());
    }


    /**
     * 增加
     */
    @Override
    public void add(GoodsDto goodsDto) {
        // 设置审核的状态
        goodsDto.getGoods().setStatus("0");
        goodsDto.getGoods().setSaleNum(0);
        goodsDto.getGoods().setUploadDate(new Date());
        int insert = spuPOMapper.insert(goodsDto.getGoods());
        // 插入商品信息

        log.warn("插入Spu{}", goodsDto.getGoods().getId());
        goodsDto.getGoodsDesc().setSpuid(goodsDto.getGoods().getId());

        GoodsDescDto goodsDesc = goodsDto.getGoodsDesc();
        TbGoodsDesc tbGoodsDesc = TbGoodsDesc.builder().spuId(goodsDto.getGoods().getId()).
                customAttributeItems(JSON.toJSON(goodsDesc.getCustomAttributeItems()).toString())
                .introduction(goodsDesc.getIntroduction())
                .itemImages(JSON.toJSON(goodsDesc.getItemImages()).toString())
                .packageList(goodsDesc.getPackageList())
                .saleService(goodsDesc.getSaleService())
                .specificationItems(JSON.toJSON(goodsDesc.getSpecificationItems()).toString()).build();

        log.warn("插入SpuDesc{}", goodsDto.getGoods().getId());
        // 插入商品的扩展信息
        goodsDescMapper.insert(tbGoodsDesc);

        //插入sku
        setItemList(goodsDto);

        spuPOMapper.updateByPrimaryKey(goodsDto.getGoods());
    }

    private void setItemList(GoodsDto goodsDto) {
        //更新defaultSkuId
        if (goodsDto.getGoods().getDefaultSkuId() == null) {
            if (goodsDto.getSkuPOList() != null && !goodsDto.getSkuPOList().isEmpty()) {
                goodsDto.getGoods().setDefaultSkuId(goodsDto.getSkuPOList().get(0).getId());
            }
        }
        if ("1".equals(goodsDto.getGoods().getIsEnableSpec())) {

            // 启用规格
            // 保存SKU列表的信息:
            for (SkuPODto item : goodsDto.getSkuPOList()) {
                // 设置SKU的数据：
                String title = goodsDto.getGoods().getName();

                Map<String, String> map = JSON.parseObject(item.getSpec().toString(), Map.class);
//                Map<String,String> map = item.getSpec();
                for (String key : map.keySet()) {
                    title += " " + map.get(key);
                }
                item.setTitle(title);

                String url = StringUtils.join(item.getImgUrl(), ",");

                SkuPO skuPO = SkuPO.builder().id(item.getId()).imgUrl(url)
                        .isDefault(item.getIsDefault()).price(item.getPrice()).spec(item.getSpec().toJSONString())
                        .spuId(item.getSpuId()).stockCount(item.getStockCount())
                        .store(item.getStore()).storeId(item.getStoreId()).title(item.getTitle())
                        .type(item.getType()).typeId(item.getTypeId()).status(item.getStatus()).build();
                addSkuPro(goodsDto, skuPO);

                skuPO.setSaleNum(0);
                skuPO.setUploaddate(new Date());


                if (skuPO.getId() != null) {
                    skuPOMapper.updateByPrimaryKeySelective(skuPO);
                } else {
                    skuPOMapper.insert(skuPO);
                }

                if ("1".equals(skuPO.getIsDefault())) {
                    goodsDto.getGoods().setDefaultSkuId(skuPO.getId());
                    goodsDto.setGoods(goodsDto.getGoods());
                }

            }
        } else {
            // 没有启用规格
            SkuPO item = new SkuPO();
            item.setTitle(goodsDto.getGoods().getName());
            item.setPrice(goodsDto.getGoods().getPrice());
            item.setStockCount(999);
            item.setIsDefault("1");
            item.setSpec("{}");
                //0表示本身，没有启用spec

            //item.setSpec(new HashMap());
            addSkuPro(goodsDto, item);


            skuPOMapper.insert(item);
            goodsDto.getGoods().setDefaultSkuId(item.getId());
        }
    }

    private void addSkuPro(GoodsDto goodsDto, SkuPO skuPO) {
        if ("1".equals(skuPO.getIsDefault())) {
            SpuPO goods = goodsDto.getGoods();
            goods.setDefaultSkuId(skuPO.getId());
            goodsDto.setGoods(goods);
        }
//        //TODO 图片
//        List<Map<String, String>> imageList = goodsDto.getGoodsDesc().getItemImages();
//        if (imageList.size() > 0) {
//
//            skuPO.setImgUrl(imageList.get(0).get("imgUrl"));
//        }

        // 保存2级分类的ID:
        skuPO.setTypeId(goodsDto.getGoods().getType2Id());
        skuPO.setUploaddate(new Date());
        // 设置商品spu ID
        skuPO.setSpuId(goodsDto.getGoods().getId());
        skuPO.setStoreId(goodsDto.getGoods().getStoreId());

        TypePO itemCat = typePOMapper.selectByPrimaryKey(goodsDto.getGoods().getType2Id());
        skuPO.setType(itemCat.getName());

        StorePO seller = storePOMapper.selectByPrimaryKey(goodsDto.getGoods().getStoreId());

        skuPO.setStore(seller.getNickname());
    }

    /**
     * 修改
     */
    @Override
    public void update(GoodsDto goodsDto) {
        // 修改商品信息
        //goodsDto.getGoods().setStatus("0");
        spuPOMapper.updateByPrimaryKey(goodsDto.getGoods());
        // 修改商品扩展信息:

        GoodsDescDto goodsDesc = goodsDto.getGoodsDesc();
        ArrayList<String> images = new ArrayList<>();
        for (SkuPODto skuPODto : goodsDto.getSkuPOList()) {
            if (skuPODto.getImgUrl() != null && !skuPODto.getImgUrl().isEmpty()) {
                images.add(skuPODto.getImgUrl().get(0));
            }
        }
        goodsDesc.setItemImages(images);
        TbGoodsDesc spuDesc = TbGoodsDesc.builder().specificationItems(JSON.toJSON(goodsDesc.getSpecificationItems()).toString())
                .saleService(goodsDesc.getSaleService()).packageList(goodsDesc.getPackageList())
                .packageList(goodsDesc.getPackageList()).itemImages(JSON.toJSON(goodsDesc.getItemImages()).toString())
                .introduction(goodsDesc.getIntroduction()).customAttributeItems(JSON.toJSON(goodsDesc.getCustomAttributeItems()).toString())
                .spuId(goodsDto.getGoods().getId()).build();

        goodsDescMapper.updateByPrimaryKey(spuDesc);
        // 修改SKU信息:
//        // 先删除，再保存:
//        // 删除SKU的信息:
//        SkuPOExample example = new SkuPOExample();
//        SkuPOExample.Criteria criteria = example.createCriteria();
//        criteria.andSpuIdEqualTo(goodsDto.getGoods().getId());
//        skuPOMapper.deleteByExample(example);
        // 更新SKU的信息
        setItemList(goodsDto);
    }

    /**
     * 根据ID获取实体
     *
     * @param id
     * @return
     */
    @Override
    public GoodsVO findOne(Integer id) {
        GoodsVO goodsVO = new GoodsVO();
        // 查询商品表的信息
        SpuPO tbGoods = spuPOMapper.selectByPrimaryKey(id);
        goodsVO.setGoods(tbGoods);
        // 查询商品扩展表的信息
        TbGoodsDesc tbGoodsDesc = goodsDescMapper.selectByPrimaryKey(id);
        goodsVO.setGoodsDesc(tbGoodsDesc);
        // 查询SKU表的信息:
        SkuPOExample example = new SkuPOExample();
        SkuPOExample.Criteria criteria = example.createCriteria();
        criteria.andSpuIdEqualTo(id);
        List<SkuPO> list = skuPOMapper.selectByExample(example);
        goodsVO.setSkuPOList(list);
        return goodsVO;
    }

    /**
     * 批量删除
     */
    @Override
    public void delete(Integer[] ids) {
        for (Integer id : ids) {
//			spuPOMapper.deleteByPrimaryKey(id);
            SpuPO spuPO = spuPOMapper.selectByPrimaryKey(id);
            spuPO.setIsDelete("1");
            spuPOMapper.updateByPrimaryKey(spuPO);
        }
    }


    @Override
    public PageResult findPage(SpuPO goods, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);

        SpuPOExample example = new SpuPOExample();
        SpuPOExample.Criteria criteria = example.createCriteria();

        criteria.andIsDeleteIsNull();

        if (goods != null) {
            if (goods.getStoreId() != null && goods.getStoreId() > 0) {
                criteria.andStoreIdEqualTo(goods.getStoreId());
            }
            if (goods.getName() != null && goods.getName().length() > 0) {
                criteria.andNameLike("%" + goods.getName() + "%");
            }
            if (goods.getStatus() != null && goods.getStatus().length() > 0) {
                criteria.andStatusLike("%" + goods.getStatus() + "%");
            }
            if (goods.getCaption() != null && goods.getCaption().length() > 0) {
                criteria.andCaptionLike("%" + goods.getCaption() + "%");
            }
            if (goods.getPic() != null && goods.getPic().length() > 0) {
                criteria.andPicLike("%" + goods.getPic() + "%");
            }
            if (goods.getIsEnableSpec() != null && goods.getIsEnableSpec().length() > 0) {
                criteria.andIsEnableSpecLike("%" + goods.getIsEnableSpec() + "%");
            }
            if (goods.getIsDelete() != null && goods.getIsDelete().length() > 0) {
                criteria.andIsDeleteLike("%" + goods.getIsDelete() + "%");
            }

        }

        Page<SpuPO> page = (Page<SpuPO>) spuPOMapper.selectByExample(example);
        return new PageResult(page.getTotal(), page.getResult());
    }

    @Override
    public void updateStatus(Integer[] ids, String status) {
        for (Integer id : ids) {
            SpuPO tbGoods = spuPOMapper.selectByPrimaryKey(id);

            tbGoods.setStatus(status);

            spuPOMapper.updateByPrimaryKey(tbGoods);
        }
    }


    /**
     * 根据SPU的ID集合查询SKU列表
     *
     * @param goodsIds
     * @param status
     * @return
     */
    @Override
    public List<SkuPO> findItemListByGoodsIdListAndStatus(Integer[] goodsIds, String status) {

        SkuPOExample example = new SkuPOExample();
        SkuPOExample.Criteria criteria = example.createCriteria();
        //criteria.(status);
        // 状态
        criteria.andSpuIdIn(Arrays.asList(goodsIds));
        //指定条件：SPUID集合
        return skuPOMapper.selectByExample(example);
    }

    @Override
    public PageResult searchByTypeId(Integer typeId, int pageNum, int rows) {
        PageHelper.startPage(pageNum, rows);

        SpuPOExample spuPOExample = new SpuPOExample();
        spuPOExample.or().andType1IdEqualTo(typeId);
        spuPOExample.or().andType2IdEqualTo(typeId);

        Page<SpuPO> page = (Page<SpuPO>) spuPOMapper.selectByExample(spuPOExample);

        return new PageResult(page.getTotal(), page.getResult());
    }
}
