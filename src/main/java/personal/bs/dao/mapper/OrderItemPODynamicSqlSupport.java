package personal.bs.dao.mapper;

import java.sql.JDBCType;
import javax.annotation.Generated;
import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

public final class OrderItemPODynamicSqlSupport {
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: order_item")
    public static final OrderItemPO orderItemPO = new OrderItemPO();

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source field: order_item.id")
    public static final SqlColumn<Integer> id = orderItemPO.id;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source field: order_item.trade_num")
    public static final SqlColumn<Integer> tradeNum = orderItemPO.tradeNum;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source field: order_item.sku_id")
    public static final SqlColumn<Integer> skuId = orderItemPO.skuId;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source field: order_item.num")
    public static final SqlColumn<Integer> num = orderItemPO.num;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: order_item")
    public static final class OrderItemPO extends SqlTable {
        public final SqlColumn<Integer> id = column("id", JDBCType.INTEGER);

        public final SqlColumn<Integer> tradeNum = column("trade_num", JDBCType.INTEGER);

        public final SqlColumn<Integer> skuId = column("sku_id", JDBCType.INTEGER);

        public final SqlColumn<Integer> num = column("num", JDBCType.INTEGER);

        public OrderItemPO() {
            super("order_item");
        }
    }
}