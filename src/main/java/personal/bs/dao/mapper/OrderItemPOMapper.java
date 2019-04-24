package personal.bs.dao.mapper;

import static org.mybatis.dynamic.sql.SqlBuilder.*;
import static personal.bs.dao.mapper.OrderItemPODynamicSqlSupport.*;

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
import personal.bs.domain.po.OrderItemPO;

@Mapper
public interface OrderItemPOMapper {
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: order_item")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    long count(SelectStatementProvider selectStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: order_item")
    @DeleteProvider(type=SqlProviderAdapter.class, method="delete")
    int delete(DeleteStatementProvider deleteStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: order_item")
    @InsertProvider(type=SqlProviderAdapter.class, method="insert")
    @Options(useGeneratedKeys=true,keyProperty="record.id")
    int insert(InsertStatementProvider<OrderItemPO> insertStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: order_item")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @ResultMap("OrderItemPOResult")
    OrderItemPO selectOne(SelectStatementProvider selectStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: order_item")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @Results(id="OrderItemPOResult", value = {
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="trade_num", property="tradeNum", jdbcType=JdbcType.INTEGER),
        @Result(column="sku_id", property="skuId", jdbcType=JdbcType.INTEGER),
        @Result(column="num", property="num", jdbcType=JdbcType.INTEGER)
    })
    List<OrderItemPO> selectMany(SelectStatementProvider selectStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: order_item")
    @UpdateProvider(type=SqlProviderAdapter.class, method="update")
    int update(UpdateStatementProvider updateStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: order_item")
    default QueryExpressionDSL<MyBatis3SelectModelAdapter<Long>> countByExample() {
        return SelectDSL.selectWithMapper(this::count, SqlBuilder.count())
                .from(orderItemPO);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: order_item")
    default DeleteDSL<MyBatis3DeleteModelAdapter<Integer>> deleteByExample() {
        return DeleteDSL.deleteFromWithMapper(this::delete, orderItemPO);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: order_item")
    default int deleteByPrimaryKey(Integer id_) {
        return DeleteDSL.deleteFromWithMapper(this::delete, orderItemPO)
                .where(id, isEqualTo(id_))
                .build()
                .execute();
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: order_item")
    default int insert(OrderItemPO record) {
        return insert(SqlBuilder.insert(record)
                .into(orderItemPO)
                .map(tradeNum).toProperty("tradeNum")
                .map(skuId).toProperty("skuId")
                .map(num).toProperty("num")
                .build()
                .render(RenderingStrategy.MYBATIS3));
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: order_item")
    default int insertSelective(OrderItemPO record) {
        return insert(SqlBuilder.insert(record)
                .into(orderItemPO)
                .map(tradeNum).toPropertyWhenPresent("tradeNum", record::getTradeNum)
                .map(skuId).toPropertyWhenPresent("skuId", record::getSkuId)
                .map(num).toPropertyWhenPresent("num", record::getNum)
                .build()
                .render(RenderingStrategy.MYBATIS3));
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: order_item")
    default QueryExpressionDSL<MyBatis3SelectModelAdapter<List<OrderItemPO>>> selectByExample() {
        return SelectDSL.selectWithMapper(this::selectMany, id, tradeNum, skuId, num)
                .from(orderItemPO);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: order_item")
    default QueryExpressionDSL<MyBatis3SelectModelAdapter<List<OrderItemPO>>> selectDistinctByExample() {
        return SelectDSL.selectDistinctWithMapper(this::selectMany, id, tradeNum, skuId, num)
                .from(orderItemPO);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: order_item")
    default OrderItemPO selectByPrimaryKey(Integer id_) {
        return SelectDSL.selectWithMapper(this::selectOne, id, tradeNum, skuId, num)
                .from(orderItemPO)
                .where(id, isEqualTo(id_))
                .build()
                .execute();
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: order_item")
    default UpdateDSL<MyBatis3UpdateModelAdapter<Integer>> updateByExample(OrderItemPO record) {
        return UpdateDSL.updateWithMapper(this::update, orderItemPO)
                .set(tradeNum).equalTo(record::getTradeNum)
                .set(skuId).equalTo(record::getSkuId)
                .set(num).equalTo(record::getNum);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: order_item")
    default UpdateDSL<MyBatis3UpdateModelAdapter<Integer>> updateByExampleSelective(OrderItemPO record) {
        return UpdateDSL.updateWithMapper(this::update, orderItemPO)
                .set(tradeNum).equalToWhenPresent(record::getTradeNum)
                .set(skuId).equalToWhenPresent(record::getSkuId)
                .set(num).equalToWhenPresent(record::getNum);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: order_item")
    default int updateByPrimaryKey(OrderItemPO record) {
        return UpdateDSL.updateWithMapper(this::update, orderItemPO)
                .set(tradeNum).equalTo(record::getTradeNum)
                .set(skuId).equalTo(record::getSkuId)
                .set(num).equalTo(record::getNum)
                .where(id, isEqualTo(record::getId))
                .build()
                .execute();
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: order_item")
    default int updateByPrimaryKeySelective(OrderItemPO record) {
        return UpdateDSL.updateWithMapper(this::update, orderItemPO)
                .set(tradeNum).equalToWhenPresent(record::getTradeNum)
                .set(skuId).equalToWhenPresent(record::getSkuId)
                .set(num).equalToWhenPresent(record::getNum)
                .where(id, isEqualTo(record::getId))
                .build()
                .execute();
    }
}