package personal.bs.controller;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import personal.bs.domain.vo.Cart;
import personal.bs.domain.vo.Result;
import personal.bs.service.CartService;
import personal.bs.utils.CookieUtil;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @Author: chenjingang@gauzi.com  2019/5/1 19:29
 */
@Slf4j
@RestController
@RequestMapping("cart")
public class CartController {

    @Resource
    CartService cartService;

    @GetMapping("showCart")
    public Result showCart(@SessionAttribute(name = "id",required = false) Integer id) {
        List<Cart> cartListFromRedis = cartService.findCartListFromRedis(id);
        return new Result();
    }

    @Autowired(required = false)
    private HttpServletRequest request;

    @Autowired(required = false)
    private HttpServletResponse response;

    @RequestMapping("/findCartList")
    public List<Cart> findCartList(@SessionAttribute(name = "id",required = false) Integer id) {


        String cartListString = CookieUtil.getCookieValue(request, "cartList", "UTF-8");
        if (cartListString == null || cartListString.equals("")) {
            cartListString = "[]";
        }
        List<Cart> cartList_cookie = JSON.parseArray(cartListString, Cart.class);
        //如果未登录
        if (id == null) {
            //从cookie中提取购物车
            return cartList_cookie;
        } else {
            //如果已登录
            //获取redis购物车
            List<Cart> cartList_redis = cartService.findCartListFromRedis(id);
            //判断当本地购物车中存在数据
            if (cartList_cookie.size() > 0) {
                //得到合并后的购物车
                List<Cart> cartList = cartService.mergeCartList(cartList_cookie, cartList_redis);
                //将合并后的购物车存入redis
                cartService.saveCartListToRedis(id, cartList);
                //本地购物车清除
                CookieUtil.deleteCookie(request, response, "cartList");
                return cartList;
            }
            return cartList_redis;
        }

    }

    @RequestMapping("/addGoodsToCartList")
    //@CrossOrigin(origins = "http://localhost:9105")
    public Result addGoodsToCartList(Integer itemId, Integer num, @SessionAttribute(name = "id",required = false) Integer id) {
        try {
            //提取购物车
            List<Cart> cartList = findCartList(id);
            //调用服务方法操作购物车
            cartList = cartService.addGoodsToCartList(cartList, itemId, num);

            if (id == null){
            //如果未登录
                //将新的购物车存入cookie
                String cartListString = JSON.toJSONString(cartList);
                CookieUtil.setCookie(request, response, "cartList", cartListString, 3600 * 24, "UTF-8");
                log.info("向cookie存储购物车");
            } else{//如果登录
                cartService.saveCartListToRedis(id, cartList);
            }

            return new Result(true, "存入购物车成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, "存入购物车失败");
        }


    }
}
