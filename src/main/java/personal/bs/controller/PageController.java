package personal.bs.controller;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.SessionAttribute;

@Controller
@Slf4j
public class PageController {

    /**
     * 主页
     *
     * @param page
     * @param username
     * @param model
     * @return
     */
    @GetMapping("user/{page}.html")
    public String jump(@PathVariable("page") String page, @SessionAttribute(value = "username", required = false) String username, Model model) {
        if (StringUtils.isNotBlank(username)) {
            model.addAttribute("username", username);
        }
        return "user/" + page;
    }

    /**
     * 默认主页
     *
     * @return
     */
    @GetMapping("/")
    public String index() {
        return "user/index";
    }

    /**
     * 运营商主页
     *
     * @return
     */
    @GetMapping("/operate/{page}.html")
    public String operateManager(@PathVariable("page") String page) {
        return "operate-manager/admin/" + page;
    }

    /**
     * 商家后台
     *
     * @return
     */
    @GetMapping("/store/{page}.html")
    public String storeManager(@PathVariable("page") String page) {
        return "store-manager/admin/" + page;
    }

}
