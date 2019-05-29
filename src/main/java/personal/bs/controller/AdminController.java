package personal.bs.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import personal.bs.domain.po.UserPO;
import personal.bs.service.UserService;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: chenjingang@gauzi.com  2019/5/25 21:50
 */
@Controller
@RequestMapping("/operate")
@Slf4j
public class AdminController {

    @Resource
    private UserService userService;

    @GetMapping("/showName")
    @ResponseBody
    public Map showName(@SessionAttribute(value = "operateName", required = false) String username, @SessionAttribute(value = "operateId", required = false) Integer id) {
        Map map = new HashMap();
        // 获得用户名信息:
        map.put("operateName", username);
        map.put("operateId", id);
        return map;
    }

    @PostMapping("login")
    public String login(UserPO user, HttpServletRequest request, HttpServletResponse response, Model model) {
        UserPO userPO = userService.findUserByUsernameAndPassword(user);
        if (userPO == null) {
            model.addAttribute("msg", "账户名与密码不匹配，请重新输入");
            return "operate-manager/admin/login";
        }
        if (userPO.getRole().equals("0")) {
            model.addAttribute("msg", "无权限");
            return "operate-manager/admin/login";
        }
        HttpSession session = request.getSession();
        session.setAttribute("operateName", userPO.getUsername());
        session.setAttribute("operateId", userPO.getId());
        return "redirect:/operate/index.html";
    }

    @GetMapping("logout")
    public String logout(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.invalidate();
        return "redirect:/operate/index.html";
    }
}
