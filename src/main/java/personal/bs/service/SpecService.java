package personal.bs.service;

import personal.bs.domain.po.SpecPO;
import personal.bs.domain.vo.PageResult;
import personal.bs.domain.vo.Specification;

import java.util.List;
import java.util.Map;

/**
 * 服务层接口
 *
 * @author Administrator
 */
public interface SpecService {

    /**
     * 返回全部列表
     *
     * @return
     */
    public List<SpecPO> findAll();


    /**
     * 返回分页列表
     *
     * @return
     */
    public PageResult findPage(int pageNum, int pageSize);


    /**
     * 增加
     */
    public void add(Specification spec);


    /**
     * 修改
     */
    public void update(Specification spec);


    /**
     * 根据ID获取实体
     *
     * @param id
     * @return
     */
    public Specification findOne(Integer id);


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
    public PageResult findPage(SpecPO spec, int pageNum, int pageSize);

    public List<Map> selectOptionList();
}
