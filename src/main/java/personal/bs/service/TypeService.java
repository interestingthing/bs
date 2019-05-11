package personal.bs.service;


import personal.bs.domain.po.TypePO;
import personal.bs.domain.vo.PageResult;

import java.util.List;
import java.util.Map;

/**
 * 服务层接口
 */
public interface TypeService {

    /**
     * 返回全部列表
     *
     * @return
     */
    public List<TypePO> findAll();


    /**
     * 返回分页列表
     *
     * @return
     */
    public PageResult findPage(int pageNum, int pageSize);


    /**
     * 增加
     */
    public void add(TypePO typePO);


    /**
     * 修改
     */
    public void update(TypePO typePO);


    /**
     * 根据ID获取实体
     *
     * @param id
     * @return
     */
    public TypePO findOne(Integer id);


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
    public PageResult findPage(TypePO typePO, int pageNum, int pageSize);

    /**
     * 根据父ID查询分类的方法
     *
     * @param parentId
     * @return
     */
    public List<TypePO> findByParentId(Integer parentId);

    public Map showTypeList();

}
