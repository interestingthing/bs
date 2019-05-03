package personal.bs.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import personal.bs.domain.vo.Result;

/**
 * @Author: chenjingang@gauzi.com  2019/5/1 19:29
 */
@RestController
@RequestMapping("cart")
public class CartController {

    @GetMapping("showCart")
    public Result showCart(){

        return new Result();
    }
}
