package personal.bs.dao.mapper;

import static org.mybatis.dynamic.sql.SqlBuilder.*;
import static personal.bs.dao.mapper.OrderPODynamicSqlSupport.*;

import java.util.List;
import javax.annotation.Generated;
import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.type.JdbcType;
import org.mybatis.dynamic.sql.SqlBuilder;
import org.mybatis.dynamic.sql.delete.DeleteDSL;
import org.mybatis.dynamic.sql.delete.MyBatis3DeleteModelAdapter;
import org.mybatis.dynamic.sql.delete.render.DeleteStatementProvider;
import org.mybatis.dynamic.sql.insert.render.InsertStatementProvider;
import org.mybatis.dynamic.sql.render.RenderingStrategy;
import org.mybatis.dynamic.sql.select.MyBatis3SelectModelAdapter;
import org.mybatis.dynamic.sql.select.QueryExpressionDSL;
import org.mybatis.dynamic.sql.select.SelectDSL;
import org.mybatis.dynamic.sql.select.render.SelectStatementProvider;
import org.mybatis.dynamic.sql.update.MyBatis3UpdateModelAdapter;
import org.mybatis.dynamic.sql.update.UpdateDSL;
import org.mybatis.dynamic.sql.update.render.UpdateStatementProvider;
import org.mybatis.dynamic.sql.util.SqlProviderAdapter;
import personal.bs.domain.po.OrderPO;

@Mapper
public interface OrderPOMapper {
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: order")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    long count(SelectStatementProvider selectStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: order")
    @DeleteProvider(type=SqlProviderAdapter.class, method="delete")
    int delete(DeleteStatementProvider deleteStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: order")
    @InsertProvider(type=SqlProviderAdapter.class, method="insert")
    @Options(useGeneratedKeys=true,keyProperty="record.id")
    int insert(InsertStatementProvider<OrderPO> insertStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: order")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @ResultMap("OrderPOResult")
    OrderPO selectOne(SelectStatementProvider selectStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: order")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @Results(id="OrderPOResult", value = {
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="address_id", property="addressId", jdbcType=JdbcType.INTEGER),
        @Result(column="user_id", property="userId", jdbcType=JdbcType.INTEGER),
        @Result(column="order_status", property="orderStatus", jdbcType=JdbcType.VARCHAR),
        @Result(column="order_num", property="orderNum", jdbcType=JdbcType.INTEGER),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="pay_time", property="payTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="pay_type", property="payType", jdbcType=JdbcType.VARCHAR),
        @Result(column="trade_num", property="tradeNum", jdbcType=JdbcType.INTEGER),
        @Result(column="delivery_id", property="deliveryId", jdbcType=JdbcType.INTEGER),
        @Result(column="invoice_id", property="invoiceId", jdbcType=JdbcType.INTEGER),
        @Result(column="pay_status", property="payStatus", jdbcType=JdbcType.VARCHAR),
        @Result(column="order_money", property="orderMoney", jdbcType=JdbcType.VARCHAR),
        @Result(column="pay_money", property="payMoney", jdbcType=JdbcType.VARCHAR)
    })
    List<OrderPO> selectMany(SelectStatementProvider selectStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: order")
    @UpdateProvider(type=SqlProviderAdapter.class, method="update")
    int update(UpdateStatementProvider updateStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: order")
    default QueryExpressionDSL<MyBatis3SelectModelAdapter<Long>> countByExample() {
        return SelectDSL.selectWithMapper(this::count, SqlBuilder.count())
                .from(orderPO);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: order")
    default DeleteDSL<MyBatis3DeleteModelAdapter<Integer>> deleteByExample() {
        return DeleteDSL.deleteFromWithMapper(this::delete, orderPO);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: order")
    default int deleteByPrimaryKey(Integer id_) {
        return DeleteDSL.deleteFromWithMapper(this::delete, orderPO)
                .where(id, isEqualTo(id_))
                .build()
                .execute();
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: order")
    default int insert(OrderPO record) {
        return insert(SqlBuilder.insert(record)
                .into(orderPO)
                .map(addressId).toProperty("addressId")
                .map(userId).toProperty("userId")
                .map(orderStatus).toProperty("orderStatus")
                .map(orderNum).toProperty("orderNum")
                .map(createTime).toProperty("createTime")
                .map(payTime).toProperty("payTime")
                .map(payType).toProperty("payType")
                .map(tradeNum).toProperty("tradeNum")
                .map(deliveryId).toProperty("deliveryId")
                .map(invoiceId).toProperty("invoiceId")
                .map(payStatus).toProperty("payStatus")
                .map(orderMoney).toProperty("orderMoney")
                .map(payMoney).toProperty("payMoney")
                .build()
                .render(RenderingStrategy.MYBATIS3));
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: order")
    default int insertSelective(OrderPO record) {
        return insert(SqlBuilder.insert(record)
                .into(orderPO)
                .map(addressId).toPropertyWhenPresent("addressId", record::getAddressId)
                .map(userId).toPropertyWhenPresent("userId", record::getUserId)
                .map(orderStatus).toPropertyWhenPresent("orderStatus", record::getOrderStatus)
                .map(orderNum).toPropertyWhenPresent("orderNum", record::getOrderNum)
                .map(createTime).toPropertyWhenPresent("createTime", record::getCreateTime)
                .map(payTime).toPropertyWhenPresent("payTime", record::getPayTime)
                .map(payType).toPropertyWhenPresent("payType", record::getPayType)
                .map(tradeNum).toPropertyWhenPresent("tradeNum", record::getTradeNum)
                .map(deliveryId).toPropertyWhenPresent("deliveryId", record::getDeliveryId)
                .map(invoiceId).toPropertyWhenPresent("invoiceId", record::getInvoiceId)
                .map(payStatus).toPropertyWhenPresent("payStatus", record::getPayStatus)
                .map(orderMoney).toPropertyWhenPresent("orderMoney", record::getOrderMoney)
                .map(payMoney).toPropertyWhenPresent("payMoney", record::getPayMoney)
                .build()
                .render(RenderingStrategy.MYBATIS3));
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: order")
    default QueryExpressionDSL<MyBatis3SelectModelAdapter<List<OrderPO>>> selectByExample() {
        return SelectDSL.selectWithMapper(this::selectMany, id, addressId, userId, orderStatus, orderNum, createTime, payTime, payType, tradeNum, deliveryId, invoiceId, payStatus, orderMoney, payMoney)
                .from(orderPO);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: order")
    default QueryExpressionDSL<MyBatis3SelectModelAdapter<List<OrderPO>>> selectDistinctByExample() {
        return SelectDSL.selectDistinctWithMapper(this::selectMany, id, addressId, userId, orderStatus, orderNum, createTime, payTime, payType, tradeNum, deliveryId, invoiceId, payStatus, orderMoney, payMoney)
                .from(orderPO);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: order")
    default OrderPO selectByPrimaryKey(Integer id_) {
        return SelectDSL.selectWithMapper(this::selectOne, id, addressId, userId, orderStatus, orderNum, createTime, payTime, payType, tradeNum, deliveryId, invoiceId, payStatus, orderMoney, payMoney)
                .from(orderPO)
                .where(id, isEqualTo(id_))
                .build()
                .execute();
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: order")
    default UpdateDSL<MyBatis3UpdateModelAdapter<Integer>> updateByExample(OrderPO record) {
        return UpdateDSL.updateWithMapper(this::update, orderPO)
                .set(addressId).equalTo(record::getAddressId)
                .set(userId).equalTo(record::getUserId)
                .set(orderStatus).equalTo(record::getOrderStatus)
                .set(orderNum).equalTo(record::getOrderNum)
                .set(createTime).equalTo(record::getCreateTime)
                .set(payTime).equalTo(record::getPayTime)
                .set(payType).equalTo(record::getPayType)
                .set(tradeNum).equalTo(record::getTradeNum)
                .set(deliveryId).equalTo(record::getDeliveryId)
                .set(invoiceId).equalTo(record::getInvoiceId)
                .set(payStatus).equalTo(record::getPayStatus)
                .set(orderMoney).equalTo(record::getOrderMoney)
                .set(payMoney).equalTo(record::getPayMoney);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: order")
    default UpdateDSL<MyBatis3UpdateModelAdapter<Integer>> updateByExampleSelective(OrderPO record) {
        return UpdateDSL.updateWithMapper(this::update, orderPO)
                .set(addressId).equalToWhenPresent(record::getAddressId)
                .set(userId).equalToWhenPresent(record::getUserId)
                .set(orderStatus).equalToWhenPresent(record::getOrderStatus)
                .set(orderNum).equalToWhenPresent(record::getOrderNum)
                .set(createTime).equalToWhenPresent(record::getCreateTime)
                .set(payTime).equalToWhenPresent(record::getPayTime)
                .set(payType).equalToWhenPresent(record::getPayType)
                .set(tradeNum).equalToWhenPresent(record::getTradeNum)
                .set(deliveryId).equalToWhenPresent(record::getDeliveryId)
                .set(invoiceId).equalToWhenPresent(record::getInvoiceId)
                .set(payStatus).equalToWhenPresent(record::getPayStatus)
                .set(orderMoney).equalToWhenPresent(record::getOrderMoney)
                .set(payMoney).equalToWhenPresent(record::getPayMoney);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: order")
    default int updateByPrimaryKey(OrderPO record) {
        return UpdateDSL.updateWithMapper(this::update, orderPO)
                .set(addressId).equalTo(record::getAddressId)
                .set(userId).equalTo(record::getUserId)
                .set(orderStatus).equalTo(record::getOrderStatus)
                .set(orderNum).equalTo(record::getOrderNum)
                .set(createTime).equalTo(record::getCreateTime)
                .set(payTime).equalTo(record::getPayTime)
                .set(payType).equalTo(record::getPayType)
                .set(tradeNum).equalTo(record::getTradeNum)
                .set(deliveryId).equalTo(record::getDeliveryId)
                .set(invoiceId).equalTo(record::getInvoiceId)
                .set(payStatus).equalTo(record::getPayStatus)
                .set(orderMoney).equalTo(record::getOrderMoney)
                .set(payMoney).equalTo(record::getPayMoney)
                .where(id, isEqualTo(record::getId))
                .build()
                .execute();
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: order")
    default int updateByPrimaryKeySelective(OrderPO record) {
        return UpdateDSL.updateWithMapper(this::update, orderPO)
                .set(addressId).equalToWhenPresent(record::getAddressId)
                .set(userId).equalToWhenPresent(record::getUserId)
                .set(orderStatus).equalToWhenPresent(record::getOrderStatus)
                .set(orderNum).equalToWhenPresent(record::getOrderNum)
                .set(createTime).equalToWhenPresent(record::getCreateTime)
                .set(payTime).equalToWhenPresent(record::getPayTime)
                .set(payType).equalToWhenPresent(record::getPayType)
                .set(tradeNum).equalToWhenPresent(record::getTradeNum)
                .set(deliveryId).equalToWhenPresent(record::getDeliveryId)
                .set(invoiceId).equalToWhenPresent(record::getInvoiceId)
                .set(payStatus).equalToWhenPresent(record::getPayStatus)
                .set(orderMoney).equalToWhenPresent(record::getOrderMoney)
                .set(payMoney).equalToWhenPresent(record::getPayMoney)
                .where(id, isEqualTo(record::getId))
                .build()
                .execute();
    }
}