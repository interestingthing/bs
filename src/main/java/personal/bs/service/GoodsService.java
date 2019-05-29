package personal.bs.service;


import personal.bs.domain.po.SkuPO;
import personal.bs.domain.po.SpuPO;
import personal.bs.domain.dto.GoodsDto;
import personal.bs.domain.vo.GoodsVO;
import personal.bs.domain.vo.PageResult;

import java.util.List;

/**
 * 服务层接口
 *
 * @author Administrator
 */
public interface GoodsService {

    /**
     * 生成商品详细页
     * @param goodsId
     * @return
     */
    public boolean genSkuHtml(Integer goodsId);

    /**
     * 删除商品详细页
     * @param goodsIds
     * @return
     */
    public boolean deleteItemHtml(Integer[] goodsIds);

    /**
     * 返回全部列表
     *
     * @return
     */
    public List<SpuPO> findAll();


    /**
     * 返回分页列表
     *
     * @return
     */
    public PageResult findPage(int pageNum, int pageSize);


    /**
     * 增加
     */
    public void add(GoodsDto goodsDto);


    /**
     * 修改
     */
    public void update(GoodsDto goodsDto);


    /**
     * 根据ID获取实体
     *
     * @param id
     * @return
     */
    public GoodsVO findOne(Integer id);


    /**
     * 批量删除
     *
     * @param ids
     */
    public void delete(Integer[] ids);

    /**
     * 分页
     *
     * @param pageNum  当前页 码
     * @param pageSize 每页记录数
     * @return
     */
    PageResult findPage(SpuPO goods, int pageNum, int pageSize);

    public void updateStatus(Integer[] ids, String status);


    /**
     * 根据SPU的ID集合查询SKU列表
     *
     * @param goodsIds
     * @param status
     * @return
     */
    public List<SkuPO> findItemListByGoodsIdListAndStatus(Integer[] goodsIds, String status);

    PageResult searchByTypeId(Integer typeId, int page, int rows);
}
