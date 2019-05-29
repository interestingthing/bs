package personal.bs.controller;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import personal.bs.domain.po.TypePO;
import personal.bs.domain.po.TypePOExample;
import personal.bs.domain.po.UserPO;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.LinkedHashMap;

/**
 * @Author: chenjingang@gauzi.com  2019/5/29 10:24
 */
public class lunwen {
@GetMapping("logout")
public String logout(HttpServletRequest request) {
    request.getSession().invalidate();
    return "redirect:/user/index";
}
}
