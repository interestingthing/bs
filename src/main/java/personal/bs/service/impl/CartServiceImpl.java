package personal.bs.service.impl;


import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import personal.bs.dao.mapper.SkuPOMapper;
import personal.bs.domain.exception.AppException;
import personal.bs.domain.po.OrderItemPO;
import personal.bs.domain.po.SkuPO;
import personal.bs.domain.vo.Cart;
import personal.bs.service.CartService;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class CartServiceImpl implements CartService {

    @Resource
    private RedisTemplate<String, List<Cart>> redisTemplate;

    @Override
    public List<Cart> findCartListFromRedis(Integer id) {
        System.out.println("从redis中提取购物车" + id);
        List<Cart> cartList = (List<Cart>) redisTemplate.boundHashOps("cartList").get(id);
        if (cartList == null) {
            cartList = new ArrayList();
        }
        return cartList;
    }

    @Override
    public void saveCartListToRedis(Integer id, List<Cart> cartList) {
        redisTemplate.boundHashOps("cartList").put(id, cartList);

    }

    @Override
    public List<Cart> mergeCartList(List<Cart> cartList1, List<Cart> cartList2) {
        for (Cart cart : cartList2) {
            for (OrderItemPO orderItem : cart.getOrderItemList()) {
                cartList1 = addGoodsToCartList(cartList1, orderItem.getSkuId(), orderItem.getNum());
            }
        }
        return cartList1;
    }

    @Resource
    private SkuPOMapper itemMapper;


    @Override
    public List<Cart> addGoodsToCartList(List<Cart> cartList, Integer itemId, Integer num) {
        /*
         * 1.使用SKUID查询SKU商品对象
         * 2.使用商品对象获取商家信息
         * 3.根据商家ID在购物车列表中查询购物车对象
         * 4.购物车列表中不存在该商家的购物车
         *      4.1 创建新的购物车对象，将新的购物车对象添加到购物车列表
         * 5.购物车列表中存在该商家的购物车
         *      5.1 判断该购物车是否存在该商品的明细
         *          5.1.1 不存在，新增该明细到明细列表
         *          5.1.2 存在，在原有的数量上加上新增的数量，并更改金额
         */
        // 1.查询SKU商品对象
        SkuPO item = itemMapper.selectByPrimaryKey(itemId);
        if (item == null) {
            throw new AppException("商品不存在!");
        }
//        if (!item.getStatus().equals(TbItem.STATUS_NORMAL)) {
//            throw new PinyougouException("商品状态异常");
//        }
        // 2.获取商家信息
        Integer storeId = item.getStoreId();
        String storeName = item.getStore();
        // 3.根据商家ID查询购物车中的对象
        Cart cart = searchCartBySellerId(cartList, storeId);
        if (cart == null) {
            // 4.购物车列表中不存在该商家的购物车
            // 创建购物车对象
            cart = new Cart();
            cart.setSellerId(storeId);
            cart.setSellerName(storeName);
            List<OrderItemPO> orderItemList = new ArrayList<>();
            // 使用item对象生成购物车明细
            OrderItemPO tbOrderItem = itemToOrderItem(item, num);
            orderItemList.add(tbOrderItem);
            cart.setOrderItemList(orderItemList);

            // 将购物车放入购物车列表
            cartList.add(cart);

        } else {
            // 5.购物车列表中存在该商家的购物车
            // 5.1 判断该购物车是否存在该商品的明细
            OrderItemPO orderItem = searchOrderItemByItemId(cart.getOrderItemList(), item.getId());
            if (orderItem == null) {
                // 5.1.1 不存在，新增该明细到明细列表
                orderItem = itemToOrderItem(item, num);
                // 添加到明细列表
                cart.getOrderItemList().add(orderItem);
            } else {
                // 5.1.2 存在，在原有的数量上加上新增的数量，并更改金额
                // 判断操作后的购物车情况
                if (orderItem.getNum() + num < 1) {
                    // 数量小于1
                    // 移除该明细
                    cart.getOrderItemList().remove(orderItem);
                    if (cart.getOrderItemList().size() == 0) {
                        // 明细列表中无数据
                        // 移除该购物车
                        cartList.remove(cart);
                    }
                }
                // 修改数量
                orderItem.setNum(orderItem.getNum() + num);
                // 修改金额
                orderItem.setSum(orderItem.getPrice() * orderItem.getNum());
            }
        }

        return cartList;
    }

    /**
     * 根据itemId查询购物车明细列表
     *
     * @param orderItemList 购物车明细列表
     * @param itemId        商品ID
     */
    private OrderItemPO searchOrderItemByItemId(List<OrderItemPO> orderItemList, Integer itemId) {
        // 遍历购物车明细列表
        for (OrderItemPO orderItem : orderItemList) {
            if (orderItem.getSkuId().equals(itemId)) {
                return orderItem;
            }
        }
        return null;
    }

    /**
     * 将item对象转换为OrderItem对象
     *
     * @param item 商品对象
     * @param num  数量
     */
    private OrderItemPO itemToOrderItem(SkuPO item, Integer num) {
        OrderItemPO orderItem = new OrderItemPO();
        //orderItem.setOrderId();
        orderItem.setSkuId(item.getId());
        orderItem.setNum(num);
//        orderItem.set(item.getImage());
        orderItem.setPrice(item.getPrice());
        orderItem.setStoreId(item.getStoreId());
        orderItem.setTitle(item.getTitle());
        orderItem.setSum(item.getPrice() * num);

        return orderItem;
    }

    /**
     * 根据商家ID查询购物车列表
     *
     * @param cartList 购物车列表
     * @param sellerId 商家ID
     */
    private Cart searchCartBySellerId(List<Cart> cartList, Integer sellerId) {
        // 遍历购物车列表
        for (Cart cart : cartList) {
            if (cart.getSellerId().equals(sellerId)) {
                return cart;
            }
        }
        return null;
    }


}
 