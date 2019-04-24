package personal.bs.dao.mapper;

import java.sql.JDBCType;
import java.util.Date;
import javax.annotation.Generated;
import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

public final class OrderPODynamicSqlSupport {
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: order")
    public static final OrderPO orderPO = new OrderPO();

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source field: order.id")
    public static final SqlColumn<Integer> id = orderPO.id;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source field: order.address_id")
    public static final SqlColumn<Integer> addressId = orderPO.addressId;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source field: order.user_id")
    public static final SqlColumn<Integer> userId = orderPO.userId;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source field: order.order_status")
    public static final SqlColumn<String> orderStatus = orderPO.orderStatus;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source field: order.order_num")
    public static final SqlColumn<Integer> orderNum = orderPO.orderNum;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source field: order.create_time")
    public static final SqlColumn<Date> createTime = orderPO.createTime;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source field: order.pay_time")
    public static final SqlColumn<Date> payTime = orderPO.payTime;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source field: order.pay_type")
    public static final SqlColumn<String> payType = orderPO.payType;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source field: order.trade_num")
    public static final SqlColumn<Integer> tradeNum = orderPO.tradeNum;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source field: order.delivery_id")
    public static final SqlColumn<Integer> deliveryId = orderPO.deliveryId;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source field: order.invoice_id")
    public static final SqlColumn<Integer> invoiceId = orderPO.invoiceId;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source field: order.pay_status")
    public static final SqlColumn<String> payStatus = orderPO.payStatus;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source field: order.order_money")
    public static final SqlColumn<String> orderMoney = orderPO.orderMoney;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source field: order.pay_money")
    public static final SqlColumn<String> payMoney = orderPO.payMoney;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: order")
    public static final class OrderPO extends SqlTable {
        public final SqlColumn<Integer> id = column("id", JDBCType.INTEGER);

        public final SqlColumn<Integer> addressId = column("address_id", JDBCType.INTEGER);

        public final SqlColumn<Integer> userId = column("user_id", JDBCType.INTEGER);

        public final SqlColumn<String> orderStatus = column("order_status", JDBCType.VARCHAR);

        public final SqlColumn<Integer> orderNum = column("order_num", JDBCType.INTEGER);

        public final SqlColumn<Date> createTime = column("create_time", JDBCType.TIMESTAMP);

        public final SqlColumn<Date> payTime = column("pay_time", JDBCType.TIMESTAMP);

        public final SqlColumn<String> payType = column("pay_type", JDBCType.VARCHAR);

        public final SqlColumn<Integer> tradeNum = column("trade_num", JDBCType.INTEGER);

        public final SqlColumn<Integer> deliveryId = column("delivery_id", JDBCType.INTEGER);

        public final SqlColumn<Integer> invoiceId = column("invoice_id", JDBCType.INTEGER);

        public final SqlColumn<String> payStatus = column("pay_status", JDBCType.VARCHAR);

        public final SqlColumn<String> orderMoney = column("order_money", JDBCType.VARCHAR);

        public final SqlColumn<String> payMoney = column("pay_money", JDBCType.VARCHAR);

        public OrderPO() {
            super("order");
        }
    }
}