package personal.bs.service.impl;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import personal.bs.dao.mapper.OrderItemPOMapper;
import personal.bs.dao.mapper.OrderPOMapper;
import personal.bs.domain.po.OrderItemPO;
import personal.bs.domain.po.OrderPO;
import personal.bs.domain.vo.Cart;
import personal.bs.service.OrderService;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * 服务实现层
 *
 * @author Administrator
 */
@Service
@Transactional
public class OrderServiceImpl implements OrderService {

    @Resource
    private OrderPOMapper orderMapper;

    @Resource
    RedisTemplate redisTemplate;

    @Resource
    private OrderItemPOMapper orderItemMapper;

    /**
     * 增加
     */
    @Override
    public void add(OrderPO order) {

        //1.从redis中提取购物车列表
        List<Cart> cartList = (List<Cart>) redisTemplate.boundHashOps("cartList").get(order.getUserId());

        //订单ID集合
        List<String> orderIdList = new ArrayList();

        //总金额
        double total_money = 0;
        //2.循环购物车列表添加订单
        for (Cart cart : cartList) {
            OrderPO tbOrder = new OrderPO();
            String id = UUID.randomUUID().toString().replace("-", "");
            //获取ID
            tbOrder.setOrderId(id);
            tbOrder.setStatus("1");
            //未付款
            tbOrder.setCreateTime(new Date());
            //下单时间
            //tbOrder.setUpdateTime(new Date());//更新时间
            tbOrder.setUserId(order.getUserId());//当前用户
//            tbOrder.setReceiverAreaName(order.getReceiverAreaName());//收货人地址
//            tbOrder.setReceiverMobile(order.getReceiverMobile());//收货人电话
//            tbOrder.setReceiver(order.getReceiver());//收货人
//            tbOrder.setSourceType(order.getSourceType());//订单来源
            tbOrder.setStoreId(order.getStoreId());//商家ID

            double money = 0;//合计数
            //循环购物车中每条明细记录
            for (OrderItemPO orderItem : cart.getOrderItemList()) {
                orderItem.setOrderId(id);
                //订单编号

                orderItem.setStoreId(cart.getSellerId());
                //商家ID
                orderItemMapper.insert(orderItem);
                money += orderItem.getNum().doubleValue();
            }

            tbOrder.setSumMoney(money);
            //合计


            orderMapper.insert(tbOrder);

            orderIdList.add(id + "");
            total_money += money;
        }


        //TODO 清除redis中的购物车已购买的商品
        redisTemplate.boundHashOps("cartList").delete(order.getUserId());
    }


}
