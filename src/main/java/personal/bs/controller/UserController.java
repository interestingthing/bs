package personal.bs.controller;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import personal.bs.domain.po.UserPO;
import personal.bs.domain.vo.Cart;
import personal.bs.domain.vo.PageResult;
import personal.bs.domain.vo.Result;
import personal.bs.service.CartService;
import personal.bs.service.UserService;
import personal.bs.utils.CookieUtil;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: chenjingang  2019/4/19 17:37
 */
@Controller
@RequestMapping("/user")
@Slf4j
public class UserController {

    @Resource
    private UserService userService;

    @Resource
    CartService cartService;


    @PostMapping("/changePWD")
    @ResponseBody
    public Map changePWD(@SessionAttribute(value = "username", required = false) String username, @SessionAttribute(value = "userId", required = false) Integer id) {
        Map map = new HashMap();
        // 获得用户名信息:
        map.put("username", username);
        map.put("userId", id);
        return map;
    }

    @GetMapping("/showName")
    @ResponseBody
    public Map showName(@SessionAttribute(value = "username", required = false) String username, @SessionAttribute(value = "userId", required = false) Integer id) {
        Map map = new HashMap();
        // 获得用户名信息:
        map.put("username", username);
        map.put("userId", id);
        return map;
    }


    @GetMapping("findUserByUsername")
    @ResponseBody
    public Result findUserByUsername(String username, String phone) {

        UserPO userPO = new UserPO();
        if (StringUtils.isNotEmpty(username)) {
            userPO.setUsername(username);
            int num = userService.findUserByUsername(userPO);
            if (num > 0) {
                return new Result(true, 1, "用户名已存在！");
            }
        }

        if (StringUtils.isNotEmpty(phone)) {
            userPO.setUsername(null);
            userPO.setPhone(phone);
            int num = userService.findUserByUsername(userPO);
            if (num > 0) {
                return new Result(true, 2, "手机号已存在！");
            }
        }

        return new Result(true, 0, "");
    }

    @PostMapping("login")
    @ResponseBody
    public Result login(@Validated @RequestBody UserPO user, HttpServletRequest request, HttpServletResponse response) {
        UserPO userPO = userService.findUserByUsernameAndPassword(user);
        if (userPO != null) {
            HttpSession session = request.getSession();
            session.setAttribute("username", userPO.getUsername());
            session.setAttribute("userId", userPO.getId());
            //登陆之后将用户在cookie中的购物车同步至redis存储
            String cartListString = CookieUtil.getCookieValue(request, "cartList", "UTF-8");
            if (cartListString == null || cartListString.equals("")) {
                cartListString = "[]";
            }
            List<Cart> cartList_cookie = JSON.parseArray(cartListString, Cart.class);
            cartService.saveCartListToRedis(userPO.getId(), cartList_cookie);
            CookieUtil.deleteCookie(request, response, "cartList");
            return new Result(true, "登录成功");
        }
        return new Result(false, "账户名与密码不匹配，请重新输入");
    }

    @GetMapping("logout")
    public String logout(HttpServletRequest request) {
        request.getSession().invalidate();
        return "redirect:/user/index";
    }

    /**
     * 注册
     *
     * @param user
     * @return
     */
    @PostMapping("/add")
    @ResponseBody
    public Result add(@Validated @RequestBody UserPO user) {
        try {
            userService.add(user);
            return new Result(true, "增加成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, "增加失败");
        }
    }

    /**
     * 返回全部列表
     *
     * @return
     */
    @RequestMapping("/findAll")
    @ResponseBody
    public List<UserPO> findAll() {
        return userService.findAll();
    }


    /**
     * 返回全部列表
     *
     * @return
     */
    @RequestMapping("/findPage")
    @ResponseBody
    public PageResult findPage(int page, int rows) {
        return userService.findPage(page, rows);
    }

    /**
     * 修改
     *
     * @param user
     * @return
     */
    @PostMapping("/update")
    @ResponseBody
    public Result update(@RequestBody UserPO user) {
        try {
            userService.update(user);
            return new Result(true, "修改成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, "修改失败");
        }
    }

    /**
     * 获取实体
     *
     * @param id
     * @return
     */
    @RequestMapping("/findOne")
    @ResponseBody
    public UserPO findOne(Integer id) {
        return userService.findOne(id);
    }

    /**
     * 批量删除
     *
     * @param ids
     * @return
     */
    @RequestMapping("/delete")
    @ResponseBody
    public Result delete(Integer[] ids) {
        try {
            userService.delete(ids);
            return new Result(true, "删除成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, "删除失败");
        }
    }

    /**
     * 查询+分页
     *
     * @param page
     * @param rows
     * @return
     */
    @PostMapping("/search")
    @ResponseBody
    public PageResult search(@RequestBody UserPO user, int page, int rows) {
        return userService.findPage(user, page, rows);
    }

}

