package personal.bs.dao.mapper;

import static org.mybatis.dynamic.sql.SqlBuilder.*;
import static personal.bs.dao.mapper.SpuSpecPODynamicSqlSupport.*;

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
import personal.bs.domain.po.SpuSpecPO;

@Mapper
public interface SpuSpecPOMapper {
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: spu_spec")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    long count(SelectStatementProvider selectStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: spu_spec")
    @DeleteProvider(type=SqlProviderAdapter.class, method="delete")
    int delete(DeleteStatementProvider deleteStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: spu_spec")
    @InsertProvider(type=SqlProviderAdapter.class, method="insert")
    @Options(useGeneratedKeys=true,keyProperty="record.id")
    int insert(InsertStatementProvider<SpuSpecPO> insertStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: spu_spec")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @ResultMap("SpuSpecPOResult")
    SpuSpecPO selectOne(SelectStatementProvider selectStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: spu_spec")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @Results(id="SpuSpecPOResult", value = {
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="spu_id", property="spuId", jdbcType=JdbcType.INTEGER),
        @Result(column="spec_id", property="specId", jdbcType=JdbcType.INTEGER),
        @Result(column="spec_value_id", property="specValueId", jdbcType=JdbcType.INTEGER)
    })
    List<SpuSpecPO> selectMany(SelectStatementProvider selectStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: spu_spec")
    @UpdateProvider(type=SqlProviderAdapter.class, method="update")
    int update(UpdateStatementProvider updateStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: spu_spec")
    default QueryExpressionDSL<MyBatis3SelectModelAdapter<Long>> countByExample() {
        return SelectDSL.selectWithMapper(this::count, SqlBuilder.count())
                .from(spuSpecPO);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: spu_spec")
    default DeleteDSL<MyBatis3DeleteModelAdapter<Integer>> deleteByExample() {
        return DeleteDSL.deleteFromWithMapper(this::delete, spuSpecPO);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: spu_spec")
    default int deleteByPrimaryKey(Integer id_) {
        return DeleteDSL.deleteFromWithMapper(this::delete, spuSpecPO)
                .where(id, isEqualTo(id_))
                .build()
                .execute();
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: spu_spec")
    default int insert(SpuSpecPO record) {
        return insert(SqlBuilder.insert(record)
                .into(spuSpecPO)
                .map(spuId).toProperty("spuId")
                .map(specId).toProperty("specId")
                .map(specValueId).toProperty("specValueId")
                .build()
                .render(RenderingStrategy.MYBATIS3));
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: spu_spec")
    default int insertSelective(SpuSpecPO record) {
        return insert(SqlBuilder.insert(record)
                .into(spuSpecPO)
                .map(spuId).toPropertyWhenPresent("spuId", record::getSpuId)
                .map(specId).toPropertyWhenPresent("specId", record::getSpecId)
                .map(specValueId).toPropertyWhenPresent("specValueId", record::getSpecValueId)
                .build()
                .render(RenderingStrategy.MYBATIS3));
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: spu_spec")
    default QueryExpressionDSL<MyBatis3SelectModelAdapter<List<SpuSpecPO>>> selectByExample() {
        return SelectDSL.selectWithMapper(this::selectMany, id, spuId, specId, specValueId)
                .from(spuSpecPO);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: spu_spec")
    default QueryExpressionDSL<MyBatis3SelectModelAdapter<List<SpuSpecPO>>> selectDistinctByExample() {
        return SelectDSL.selectDistinctWithMapper(this::selectMany, id, spuId, specId, specValueId)
                .from(spuSpecPO);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: spu_spec")
    default SpuSpecPO selectByPrimaryKey(Integer id_) {
        return SelectDSL.selectWithMapper(this::selectOne, id, spuId, specId, specValueId)
                .from(spuSpecPO)
                .where(id, isEqualTo(id_))
                .build()
                .execute();
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: spu_spec")
    default UpdateDSL<MyBatis3UpdateModelAdapter<Integer>> updateByExample(SpuSpecPO record) {
        return UpdateDSL.updateWithMapper(this::update, spuSpecPO)
                .set(spuId).equalTo(record::getSpuId)
                .set(specId).equalTo(record::getSpecId)
                .set(specValueId).equalTo(record::getSpecValueId);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: spu_spec")
    default UpdateDSL<MyBatis3UpdateModelAdapter<Integer>> updateByExampleSelective(SpuSpecPO record) {
        return UpdateDSL.updateWithMapper(this::update, spuSpecPO)
                .set(spuId).equalToWhenPresent(record::getSpuId)
                .set(specId).equalToWhenPresent(record::getSpecId)
                .set(specValueId).equalToWhenPresent(record::getSpecValueId);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: spu_spec")
    default int updateByPrimaryKey(SpuSpecPO record) {
        return UpdateDSL.updateWithMapper(this::update, spuSpecPO)
                .set(spuId).equalTo(record::getSpuId)
                .set(specId).equalTo(record::getSpecId)
                .set(specValueId).equalTo(record::getSpecValueId)
                .where(id, isEqualTo(record::getId))
                .build()
                .execute();
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: spu_spec")
    default int updateByPrimaryKeySelective(SpuSpecPO record) {
        return UpdateDSL.updateWithMapper(this::update, spuSpecPO)
                .set(spuId).equalToWhenPresent(record::getSpuId)
                .set(specId).equalToWhenPresent(record::getSpecId)
                .set(specValueId).equalToWhenPresent(record::getSpecValueId)
                .where(id, isEqualTo(record::getId))
                .build()
                .execute();
    }
}