package personal.bs.service.impl;


import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import personal.bs.dao.mapper.UserPOMapper;
import personal.bs.domain.po.UserPO;
import personal.bs.domain.po.UserPOExample;
import personal.bs.domain.vo.PageResult;
import personal.bs.service.UserService;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * 服务实现层
 *
 * @author Administrator
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class UserServiceImpl implements UserService {

    @Resource
    private UserPOMapper userPOMapper;

    /**
     * 增加
     */
    @Override
    public void add(UserPO user) {

        user.setCreated(new Date());//用户注册时间
        user.setUpdated(new Date());//修改时间
        user.setPassword(DigestUtils.md5Hex(user.getPassword()));//密码加密

        userPOMapper.insert(user);
    }

    /**
     * 登录
     *
     * @param user
     * @return
     */
    @Override
    public UserPO findUserByUsernameAndPassword(UserPO user) {
        UserPOExample userPOExample = new UserPOExample();
        UserPOExample.Criteria criteria = userPOExample.createCriteria();
        criteria.andUsernameEqualTo(user.getUsername())
                .andPasswordEqualTo(DigestUtils.md5Hex(user.getPassword()));
        return userPOMapper.selectByExample(userPOExample).stream().findFirst().orElse(null);
    }

    /**
     * 查询全部
     */
    @Override
    public List<UserPO> findAll() {
        return userPOMapper.selectByExample(null);

    }

    /**
     * 按分页查询
     */
    @Override
    public PageResult findPage(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        Page<UserPO> page = (Page<UserPO>) userPOMapper.selectByExample(null);
        return new PageResult(page.getTotal(), page.getResult());
    }


    /**
     * 修改
     */
    @Override
    public void update(UserPO user) {
        userPOMapper.updateByPrimaryKey(user);
    }

    /**
     * 根据ID获取实体
     *
     * @param id
     * @return
     */
    @Override
    public UserPO findOne(Long id) {
        return userPOMapper.selectByPrimaryKey(id.intValue());
    }

    /**
     * 批量删除
     */
    @Override
    public void delete(Long[] ids) {
        for (Long id : ids) {
            userPOMapper.deleteByPrimaryKey(id.intValue());
        }
    }


    @Override
    public PageResult findPage(UserPO user, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);

        UserPOExample example = new UserPOExample();
        UserPOExample.Criteria criteria = example.createCriteria();

        if (user != null) {
            if (user.getUsername() != null && user.getUsername().length() > 0) {
                criteria.andUsernameLike("%" + user.getUsername() + "%");
            }
            if (user.getPassword() != null && user.getPassword().length() > 0) {
                criteria.andPasswordLike("%" + user.getPassword() + "%");
            }
            if (user.getPhone() != null && user.getPhone().length() > 0) {
                criteria.andPhoneLike("%" + user.getPhone() + "%");
            }
            if (user.getEmail() != null && user.getEmail().length() > 0) {
                criteria.andEmailLike("%" + user.getEmail() + "%");
            }

            if (user.getNickname() != null && user.getNickname().length() > 0) {
                criteria.andNicknameLike("%" + user.getNickname() + "%");
            }
            if (user.getUsername() != null && user.getUsername().length() > 0) {
                criteria.andUsernameLike("%" + user.getUsername() + "%");
            }
            if (user.getStatus() != null && user.getStatus().length() > 0) {
                criteria.andStatusLike("%" + user.getStatus() + "%");
            }
//            if (user.getHeadPic() != null && user.getHeadPic().length() > 0) {
//                criteria.andHeadPicLike("%" + user.getHeadPic() + "%");
//            }
            if (user.getQq() != null && user.getQq().length() > 0) {
                criteria.andQqLike("%" + user.getQq() + "%");
            }
            if (user.getSex() != null && user.getSex().length() > 0) {
                criteria.andSexLike("%" + user.getSex() + "%");
            }

        }

        Page<UserPO> page = (Page<UserPO>) userPOMapper.selectByExample(null);
        return new PageResult(page.getTotal(), page.getResult());
    }

    @Override
    public int findUserByUsername(UserPO userPO) {
        UserPOExample userPOExample = new UserPOExample();
        UserPOExample.Criteria criteria = userPOExample.createCriteria();
        if (StringUtils.isNotEmpty(userPO.getUsername())) {
            criteria.andUsernameEqualTo(userPO.getUsername());
            List<UserPO> userPOS = userPOMapper.selectByExample(userPOExample);
            return userPOS.size();
        } else if (StringUtils.isNotEmpty(userPO.getPhone())) {
            criteria.andPhoneEqualTo(userPO.getPhone());
            List<UserPO> userPOS = userPOMapper.selectByExample(userPOExample);
            return userPOS.size();
        }
        return 0;
    }

}
