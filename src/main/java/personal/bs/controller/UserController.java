package personal.bs.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import personal1.bs.dao.mapper.UserDOMapper;
import personal1.bs.domain.po.UserDO;
import personal1.bs.domain.po.UserDOExample;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author: chenjingang  2019/4/19 17:37
 */
@RestController
public class UserController {

    @Resource
    UserDOMapper userDOMapper;

    @GetMapping("getUser")
    public List getUserDOMapper() {
        UserDOExample userDOExample = new UserDOExample();
        UserDOExample.Criteria criteria = userDOExample.createCriteria();



        List<UserDO> userDOS = userDOMapper.selectByExample(userDOExample);
        return userDOS;
    }
}
