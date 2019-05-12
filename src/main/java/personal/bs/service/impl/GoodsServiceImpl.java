package personal.bs.service.impl;


import com.alibaba.fastjson.JSON;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import personal.bs.dao.mapper.*;
import personal.bs.domain.po.*;
import personal.bs.domain.vo.Goods;
import personal.bs.domain.vo.GoodsDescDto;
import personal.bs.domain.vo.PageResult;
import personal.bs.service.GoodsService;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;
import java.util.Map;


@Service
@Transactional
@Slf4j
public class GoodsServiceImpl implements GoodsService {

    @Resource
    private SpuPOMapper spuPOMapper;

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

    @Resource
    private TbGoodsDescMapper goodsDescMapper;

    @Resource
    private SkuPOMapper skuPOMapper;

    @Resource
    private TypePOMapper typePOMapper;


    @Resource
    private StorePOMapper storePOMapper;

    /**
     * 增加
     */
    @Override
    public void add(Goods goods) {
        goods.getGoods().setStatus("0");
        // 设置审核的状态

        int insert = spuPOMapper.insert(goods.getGoods());
        // 插入商品信息

        log.warn("插入Spu{}", goods.getGoods().getId());
        goods.getGoodsDesc().setSpuid(goods.getGoods().getId());

        GoodsDescDto goodsDesc = goods.getGoodsDesc();
        TbGoodsDesc tbGoodsDesc = TbGoodsDesc.builder().spuid(goods.getGoods().getId()).
                customAttributeItems(goodsDesc.getCustomAttributeItems().toString())
                .introduction(goodsDesc.getIntroduction())
                .itemImages(goodsDesc.getItemImages().toString())
                .packageList(goodsDesc.getPackageList())
                .saleService(goodsDesc.getSaleService())
                .specificationItems(goodsDesc.getSpecificationItems().toString()).build();

        log.warn("插入Spu{}", goods.getGoods().getId());
        goodsDescMapper.insert(tbGoodsDesc);
        // 插入商品的扩展信息

        setItemList(goods);
    }

    private void setItemList(Goods goods) {
        if ("1".equals(goods.getGoods().getStatus())) {
            // 启用规格
            // 保存SKU列表的信息:
            for (SkuPO item : goods.getSkuPOList()) {
                // 设置SKU的数据：
                String title = goods.getGoods().getName();
                Map<String, String> map = JSON.parseObject(item.getSpec(), Map.class);
                //Map<String,String> map = item.getSpec();
                for (String key : map.keySet()) {
                    title += " " + map.get(key);
                }
                item.setTitle(title);

                setValue(goods, item);

                skuPOMapper.insert(item);
            }
        } else {
            // 没有启用规格
            SkuPO item = new SkuPO();

            item.setTitle(goods.getGoods().getName());

            item.setPrice(goods.getGoods().getPrice());

            item.setStockCount(999);

            //item.set("0");

            item.setIsDefault("1");
            item.setSpec("{}");
            //item.setSpec(new HashMap());
            setValue(goods, item);
            skuPOMapper.insert(item);
        }
    }

    private void setValue(Goods goods, SkuPO item) {
        //TODO 图片
//        List<Map> imageList = JSON.parseArray(goods.getSpuPODesc().getItemImages(), Map.class);
//        if (imageList.size() > 0) {
//            item.setImgUrl((String) imageList.get(0).get("url"));
//        }

        // 保存2级分类的ID:
        item.setTypeId(goods.getGoods().getType2Id());
//		item.set(new Date());
//		item.setUpdateTime(new Date());
        // 设置商品ID
        item.setSpuId(goods.getGoods().getId());
        item.setStoreId(goods.getGoods().getStoreId());

        TypePO itemCat = typePOMapper.selectByPrimaryKey(goods.getGoods().getType2Id());
        item.setType(itemCat.getName());

        StorePO seller = storePOMapper.selectByPrimaryKey(goods.getGoods().getStoreId());

        item.setStore(seller.getName());
    }

    /**
     * 修改
     */
    @Override
    public void update(Goods goods) {
        // 修改商品信息
        goods.getGoods().setStatus("0");
        spuPOMapper.updateByPrimaryKey(goods.getGoods());
        // 修改商品扩展信息:
        //goodsDescMapper.updateByPrimaryKey(goods.getSpuPODesc());
        // 修改SKU信息:
        // 先删除，再保存:
        // 删除SKU的信息:
        SkuPOExample example = new SkuPOExample();
        SkuPOExample.Criteria criteria = example.createCriteria();
        criteria.andIdEqualTo(goods.getGoods().getId());
        skuPOMapper.deleteByExample(example);
        // 保存SKU的信息
        setItemList(goods);
    }

    /**
     * 根据ID获取实体
     *
     * @param id
     * @return
     */
    @Override
    public Goods findOne(Integer id) {
        Goods goods = new Goods();
        // 查询商品表的信息
        SpuPO tbGoods = spuPOMapper.selectByPrimaryKey(id);
        goods.setGoods(tbGoods);
        // 查询商品扩展表的信息
        TbGoodsDesc tbGoodsDesc = goodsDescMapper.selectByPrimaryKey(id);
        System.out.println(tbGoodsDesc);
        GoodsDescDto goodsDesc = goods.getGoodsDesc();
        goodsDesc.builder().spuid(goodsDesc.getSpuid()).
                customAttributeItems(null).
                introduction(tbGoodsDesc.getIntroduction())
                .itemImages(null)
                .packageList(tbGoodsDesc.getPackageList())
                .saleService(tbGoodsDesc.getSaleService())
                .specificationItems(null).build();
        goods.setGoodsDesc(goodsDesc);

        // 查询SKU表的信息:
        SkuPOExample example = new SkuPOExample();
        SkuPOExample.Criteria criteria = example.createCriteria();
        criteria.andSpuIdEqualTo(id);
        List<SkuPO> list = skuPOMapper.selectByExample(example);
        goods.setSkuPOList(list);

        return goods;
    }

    /**
     * 批量删除
     */
    @Override
    public void delete(Integer[] ids) {
        for (Integer id : ids) {
//			spuPOMapper.deleteByPrimaryKey(id);
            SpuPO tbGoods = spuPOMapper.selectByPrimaryKey(id);
            tbGoods.setIsDelete("1");
            spuPOMapper.updateByPrimaryKey(tbGoods);
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
        //TODO criteria.ANDS(status);//状态
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
