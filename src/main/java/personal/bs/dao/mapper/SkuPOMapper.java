package personal.bs.dao.mapper;

import static org.mybatis.dynamic.sql.SqlBuilder.*;
import static personal.bs.dao.mapper.SkuPODynamicSqlSupport.*;

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
import personal.bs.domain.po.SkuPO;

@Mapper
public interface SkuPOMapper {
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: sku")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    long count(SelectStatementProvider selectStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: sku")
    @DeleteProvider(type=SqlProviderAdapter.class, method="delete")
    int delete(DeleteStatementProvider deleteStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: sku")
    @InsertProvider(type=SqlProviderAdapter.class, method="insert")
    @Options(useGeneratedKeys=true,keyProperty="record.id")
    int insert(InsertStatementProvider<SkuPO> insertStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: sku")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @ResultMap("SkuPOResult")
    SkuPO selectOne(SelectStatementProvider selectStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: sku")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @Results(id="SkuPOResult", value = {
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="spu_id", property="spuId", jdbcType=JdbcType.INTEGER),
        @Result(column="img_url", property="imgUrl", jdbcType=JdbcType.VARCHAR),
        @Result(column="price", property="price", jdbcType=JdbcType.DECIMAL),
        @Result(column="repertory", property="repertory", jdbcType=JdbcType.VARCHAR),
        @Result(column="spu_spec_ids", property="spuSpecIds", jdbcType=JdbcType.VARCHAR)
    })
    List<SkuPO> selectMany(SelectStatementProvider selectStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: sku")
    @UpdateProvider(type=SqlProviderAdapter.class, method="update")
    int update(UpdateStatementProvider updateStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: sku")
    default QueryExpressionDSL<MyBatis3SelectModelAdapter<Long>> countByExample() {
        return SelectDSL.selectWithMapper(this::count, SqlBuilder.count())
                .from(skuPO);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: sku")
    default DeleteDSL<MyBatis3DeleteModelAdapter<Integer>> deleteByExample() {
        return DeleteDSL.deleteFromWithMapper(this::delete, skuPO);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: sku")
    default int deleteByPrimaryKey(Integer id_) {
        return DeleteDSL.deleteFromWithMapper(this::delete, skuPO)
                .where(id, isEqualTo(id_))
                .build()
                .execute();
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: sku")
    default int insert(SkuPO record) {
        return insert(SqlBuilder.insert(record)
                .into(skuPO)
                .map(spuId).toProperty("spuId")
                .map(imgUrl).toProperty("imgUrl")
                .map(price).toProperty("price")
                .map(repertory).toProperty("repertory")
                .map(spuSpecIds).toProperty("spuSpecIds")
                .build()
                .render(RenderingStrategy.MYBATIS3));
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: sku")
    default int insertSelective(SkuPO record) {
        return insert(SqlBuilder.insert(record)
                .into(skuPO)
                .map(spuId).toPropertyWhenPresent("spuId", record::getSpuId)
                .map(imgUrl).toPropertyWhenPresent("imgUrl", record::getImgUrl)
                .map(price).toPropertyWhenPresent("price", record::getPrice)
                .map(repertory).toPropertyWhenPresent("repertory", record::getRepertory)
                .map(spuSpecIds).toPropertyWhenPresent("spuSpecIds", record::getSpuSpecIds)
                .build()
                .render(RenderingStrategy.MYBATIS3));
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: sku")
    default QueryExpressionDSL<MyBatis3SelectModelAdapter<List<SkuPO>>> selectByExample() {
        return SelectDSL.selectWithMapper(this::selectMany, id, spuId, imgUrl, price, repertory, spuSpecIds)
                .from(skuPO);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: sku")
    default QueryExpressionDSL<MyBatis3SelectModelAdapter<List<SkuPO>>> selectDistinctByExample() {
        return SelectDSL.selectDistinctWithMapper(this::selectMany, id, spuId, imgUrl, price, repertory, spuSpecIds)
                .from(skuPO);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: sku")
    default SkuPO selectByPrimaryKey(Integer id_) {
        return SelectDSL.selectWithMapper(this::selectOne, id, spuId, imgUrl, price, repertory, spuSpecIds)
                .from(skuPO)
                .where(id, isEqualTo(id_))
                .build()
                .execute();
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: sku")
    default UpdateDSL<MyBatis3UpdateModelAdapter<Integer>> updateByExample(SkuPO record) {
        return UpdateDSL.updateWithMapper(this::update, skuPO)
                .set(spuId).equalTo(record::getSpuId)
                .set(imgUrl).equalTo(record::getImgUrl)
                .set(price).equalTo(record::getPrice)
                .set(repertory).equalTo(record::getRepertory)
                .set(spuSpecIds).equalTo(record::getSpuSpecIds);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: sku")
    default UpdateDSL<MyBatis3UpdateModelAdapter<Integer>> updateByExampleSelective(SkuPO record) {
        return UpdateDSL.updateWithMapper(this::update, skuPO)
                .set(spuId).equalToWhenPresent(record::getSpuId)
                .set(imgUrl).equalToWhenPresent(record::getImgUrl)
                .set(price).equalToWhenPresent(record::getPrice)
                .set(repertory).equalToWhenPresent(record::getRepertory)
                .set(spuSpecIds).equalToWhenPresent(record::getSpuSpecIds);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: sku")
    default int updateByPrimaryKey(SkuPO record) {
        return UpdateDSL.updateWithMapper(this::update, skuPO)
                .set(spuId).equalTo(record::getSpuId)
                .set(imgUrl).equalTo(record::getImgUrl)
                .set(price).equalTo(record::getPrice)
                .set(repertory).equalTo(record::getRepertory)
                .set(spuSpecIds).equalTo(record::getSpuSpecIds)
                .where(id, isEqualTo(record::getId))
                .build()
                .execute();
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: sku")
    default int updateByPrimaryKeySelective(SkuPO record) {
        return UpdateDSL.updateWithMapper(this::update, skuPO)
                .set(spuId).equalToWhenPresent(record::getSpuId)
                .set(imgUrl).equalToWhenPresent(record::getImgUrl)
                .set(price).equalToWhenPresent(record::getPrice)
                .set(repertory).equalToWhenPresent(record::getRepertory)
                .set(spuSpecIds).equalToWhenPresent(record::getSpuSpecIds)
                .where(id, isEqualTo(record::getId))
                .build()
                .execute();
    }
}