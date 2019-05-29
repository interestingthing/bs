package personal.bs.controller;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.SessionAttribute;
import personal.bs.service.GoodsService;
import personal.bs.service.TypeService;

import javax.annotation.Resource;
import java.util.Map;

@Controller
@Slf4j
public class PageController {

    @Resource
    TypeService typeService;

    @Resource
    private GoodsService goodsService;

    /**
     * 项目默认主页
     *
     * @return
     */
    @GetMapping("/")
    public String index() {
        return "forward:/user/index";
    }

    /**
     * 用户主页
     *
     * @param username
     * @param model
     * @return
     */
    @GetMapping("user/index")
    public String jumpIndex(@SessionAttribute(value = "username", required = false) String username, Model model) {
        if (StringUtils.isNotBlank(username)) {
            model.addAttribute("username", username);
        }
        Map map = typeService.showTypeList();
        model.addAttribute("map", map);
        return "user/index";
    }

    /**
     * 用户页面
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

    @GetMapping("/operate/login.html")
    public String operateLogin() {

        return "operate-manager/admin/login";
    }

    /**
     * 运营商主页
     *
     * @return
     */
    @GetMapping("/operate/{page}.html")
    public String operateManager(@PathVariable("page") String page, @SessionAttribute(value = "operateName", required = false) String username, Model model) {
        if (username == null || username == "") {
            model.addAttribute("msg", "请登录后操作");
            return "operate-manager/admin/login";
        }

        return "operate-manager/admin/" + page;
    }


    @GetMapping("/store/login.html")
    public String storeLogin() {
        return "store-manager/admin/login";
    }

    @GetMapping("/store/register.html")
    public String storeRegister() {
        return "store-manager/admin/register";
    }

    /**
     * 商家后台
     *
     * @return
     */
    @GetMapping("/store/{page}.html")
    public String storeManager(@PathVariable("page") String page, @SessionAttribute(value = "storeName", required = false) String username,Model model) {
        if (username == null || username == "") {
            model.addAttribute("msg", "请登录后操作");
            return "redirect:/store/login.html";
        }
        return "store-manager/admin/" + page;
    }

//    @GetMapping("/operate/good_edit.html")
//    public String operateEditGoods(Integer id,Model model) {
//        Goods goods = goodsService.findOne(id);
//        model.addAttribute()
//        return "operate-manager/admin/" + page;
//    }
//
//    @GetMapping("/store/good_edit.html")
//    public String storeEditGoods(@PathVariable("page") String page) {
//        return "operate-manager/admin/" + page;
//    }

}
