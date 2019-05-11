package personal.bs.service;


import personal.bs.domain.po.StorePO;
import personal.bs.domain.vo.PageResult;

import java.util.List;

/**
 * 服务层接口
 *
 * @author Administrator
 */
public interface SellerService {

    /**
     * 返回全部列表
     *
     * @return
     */
    public List<StorePO> findAll();


    /**
     * 返回分页列表
     *
     * @return
     */
    public PageResult findPage(int pageNum, int pageSize);


    /**
     * 增加
     */
    public void add(StorePO seller);


    /**
     * 修改
     */
    public void update(StorePO seller);


    /**
     * 根据ID获取实体
     *
     * @param id
     * @return
     */
    public StorePO findOne(Integer id);


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
    public PageResult findPage(StorePO seller, int pageNum, int pageSize);


    public void updateStatus(String sellerId, String status);
}
