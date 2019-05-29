package personal.bs.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import personal.bs.dao.mapper.OrderItemPOMapper;
import personal.bs.dao.mapper.OrderPOMapper;
import personal.bs.domain.po.OrderItemPO;
import personal.bs.domain.po.OrderPO;
import personal.bs.domain.po.OrderPOExample;
import personal.bs.domain.po.UserPO;
import personal.bs.domain.vo.Cart;
import personal.bs.domain.vo.PageResult;
import personal.bs.domain.vo.Result;
import personal.bs.service.OrderService;
import personal.bs.service.UserService;

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
    UserService userService;

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
            //未付款
            tbOrder.setStatus("1");
            //下单时间
            tbOrder.setCreateTime(new Date());

//            tbOrder.set(new Date());//更新时间
            //当前用户
            tbOrder.setUserId(order.getUserId());
//            tbOrder.setReceiverAreaName(order.getReceiverAreaName());//收货人地址
//            tbOrder.setReceiverMobile(order.getReceiverMobile());//收货人电话
//            tbOrder.setReceiver(order.getReceiver());//收货人
//            tbOrder.setSourceType(order.getSourceType());//订单来源
            //商家ID
            tbOrder.setStoreId(cart.getSellerId());

            //合计数
            double money = 0;
            //循环购物车中每条明细记录
            for (OrderItemPO orderItem : cart.getOrderItemList()) {
                //订单编号
                orderItem.setOrderId(id);
                //商家ID
                orderItem.setStoreId(cart.getSellerId());
                orderItemMapper.insert(orderItem);
                money += orderItem.getSum();
            }
            //合计
            tbOrder.setSumMoney(money);

            UserPO user = userService.findOne(order.getUserId());
            tbOrder.setUsernick(user.getNickname());


            orderMapper.insert(tbOrder);

            orderIdList.add(id + "");
            total_money += money;
        }
        //TODO 清除redis中的购物车已购买的商品
        redisTemplate.boundHashOps("cartList").delete(order.getUserId());
    }

    /**
     * 查询全部
     */
    @Override
    public List<OrderPO> findAll() {
        return orderMapper.selectByExample(null);
    }

    /**
     * 按分页查询
     */
    @Override
    public PageResult findPage(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        Page<OrderPO> page=   (Page<OrderPO>) orderMapper.selectByExample(null);
        return new PageResult(page.getTotal(), page.getResult());
    }

    /**
     * 修改
     */
    @Override
    public void update(OrderPO order){
        orderMapper.updateByPrimaryKey(order);
    }

    /**
     * 根据ID获取实体
     * @param id
     * @return
     */
    @Override
    public OrderPO findOne(Integer id){
        return orderMapper.selectByPrimaryKey(id);
    }

    /**
     * 批量删除
     */
    @Override
    public void delete(Integer[] ids) {
        for(Integer id:ids){
            orderMapper.deleteByPrimaryKey(id);
        }
    }


    @Override
    public PageResult findPage(OrderPO order, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);

        OrderPOExample example=new OrderPOExample();
        OrderPOExample.Criteria criteria = example.createCriteria();

        if(order!=null){

            if(order.getUserId()!=null){
                criteria.andUserIdEqualTo(order.getUserId());
            }
        }

        Page<OrderPO> page= (Page<OrderPO>)orderMapper.selectByExample(example);
        return new PageResult(page.getTotal(), page.getResult());
    }


    @Override
    public void updateOrderStatus(String out_trade_no, String transaction_id) {
        //1.修改支付日志的状态及相关字段
//        TbPayLog payLog = payLogMapper.selectByPrimaryKey(out_trade_no);
//        payLog.setPayTime(new Date());//支付时间
//        payLog.setTradeState("1");//交易成功
//        payLog.setTransactionId(transaction_id);//微信的交易流水号
//
//        payLogMapper.updateByPrimaryKey(payLog);//修改
//        //2.修改订单表的状态
//        String orderList = payLog.getOrderList();// 订单ID 串
//        String[] orderIds = orderList.split(",");
//
//        for(String orderId:orderIds){
//            OrderPO order = orderMapper.selectByPrimaryKey(Long.valueOf(orderId));
//            order.setStatus("2");//已付款状态
//            order.setPaymentTime(new Date());//支付时间
//            orderMapper.updateByPrimaryKey(order);
//        }

        //3.清除缓存中的payLog
        //redisTemplate.boundHashOps("payLog").delete(payLog.getUserId());

    }
}
