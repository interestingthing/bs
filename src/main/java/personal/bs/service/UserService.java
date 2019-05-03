package personal.bs.service;

import personal.bs.domain.po.UserPO;
import personal.bs.domain.vo.PageResult;

import java.util.List;

/**
 * 服务层接口
 */
public interface UserService {

    /**
     * 返回全部列表
     *
     * @return
     */
    List<UserPO> findAll();


    /**
     * 返回分页列表
     *
     * @return
     */
    PageResult findPage(int pageNum, int pageSize);


    /**
     * 增加
     */
    void add(UserPO user);


    /**
     * 修改
     */
    void update(UserPO user);


    /**
     * 根据ID获取实体
     *
     * @param id
     * @return
     */
    UserPO findOne(Long id);


    /**
     * 批量删除
     *
     * @param ids
     */
    void delete(Long[] ids);

    /**
     * 分页
     *
     * @param pageNum  当前页码
     * @param pageSize 每页记录数
     * @return
     */
    PageResult findPage(UserPO user, int pageNum, int pageSize);

    int findUserByUsername(UserPO userPO);

    UserPO findUserByUsernameAndPassword(UserPO user);
}
