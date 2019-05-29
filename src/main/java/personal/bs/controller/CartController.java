package personal.bs.controller;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;
import personal.bs.domain.po.OrderItemPO;
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
    public Result showCart(@SessionAttribute(name = "userId", required = false) Integer id) {
        List<Cart> cartListFromRedis = cartService.findCartListFromRedis(id);
        return new Result();
    }

    @Autowired(required = false)
    private HttpServletRequest request;

    @Autowired(required = false)
    private HttpServletResponse response;

    @GetMapping("/del")
    public List<Cart> delSkuFromCartList(@SessionAttribute(name = "userId", required = false) Integer id, Integer skuId) {


        String cartListString = CookieUtil.getCookieValue(request, "cartList", "UTF-8");
        if (cartListString == null || cartListString.equals("")) {
            cartListString = "[]";
        }
        List<Cart> cartList = JSON.parseArray(cartListString, Cart.class);


        //如果未登录
        if (id != null) {
            //如果已登录
            //获取redis购物车
            cartList = cartService.findCartListFromRedis(id);
        }

        for (Cart cart : cartList) {
            List<OrderItemPO> orderItemList = cart.getOrderItemList();
            for (OrderItemPO orderItemPO : orderItemList) {
                if (orderItemPO.getSkuId().equals(skuId)) {
                    orderItemList.remove(orderItemPO);
                    // TODO
                    CookieUtil.setCookie(request, response, "cartList", cartListString, 3600 * 24, "UTF-8");
                    return cartList;
                }


            }

        }
        CookieUtil.setCookie(request, response, "cartList", cartListString, 3600 * 24, "UTF-8");
        return cartList;

    }

    @RequestMapping("/findCartList")
    public List<Cart> findCartList(@SessionAttribute(name = "userId", required = false) Integer id) {
        String cartListString = CookieUtil.getCookieValue(request, "cartList", "UTF-8");
        if (cartListString == null || cartListString.equals("")) {
            cartListString = "[]";
        }
        List<Cart> cartList_cookie = JSON.parseArray(cartListString, Cart.class);
        //如果未登录返回cookie中的购物车
        if (id == null) {
            return cartList_cookie;
        } else {
            //如果已登录获取redis购物车
            List<Cart> cartList_redis = cartService.findCartListFromRedis(id);
            //判断当cookie购物车中是否存在数据
            if (cartList_cookie.size() > 0) {
                //合并购物车
                List<Cart> cartList = cartService.mergeCartList(cartList_cookie, cartList_redis);
                //将合并后的购物车存入redis
                cartService.saveCartListToRedis(id, cartList);
                //清除cookie中的购物车
                CookieUtil.deleteCookie(request, response, "cartList");
                return cartList;
            }
            return cartList_redis;
        }
    }

    @RequestMapping("/addGoodsToCartList")
    public Result addGoodsToCartList(Integer itemId, Integer num, @SessionAttribute(name = "userId", required = false) Integer id) {
        try {
            List<Cart> cart = findCartList(id);
            cart = cartService.addGoodsToCartList(cart, itemId, num);
            if (id == null) {
                String cartListString = JSON.toJSONString(cart);
                CookieUtil.setCookie(request, response, "cartList", cartListString, 3600 * 24, "UTF-8");
                log.info("向cookie存储购物车");
            } else {//如果登录
                cartService.saveCartListToRedis(id, cart);
            }
            return new Result(true, "存入购物车成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, "存入购物车失败");
        }
    }
}
