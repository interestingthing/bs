package personal.bs.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class PageController {

    @GetMapping("{page}.html")
    public String jump(@PathVariable("page") String page) {
        System.out.println(page);
        return page;
    }

    @GetMapping("/")
    public String index() {
        System.out.println("==================");
        return "index";
    }

    @GetMapping("/operate")
    public String operateManager() {
        return "wgxcb-operate-manager/login";
    }

    @GetMapping("/store")
    public String storeManager() {

        return "wgxcb-store-manager/login";
    }


}
