package personal.bs.dao.mapper;

import static org.mybatis.dynamic.sql.SqlBuilder.*;
import static personal.bs.dao.mapper.SpecValuePODynamicSqlSupport.*;

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
import personal.bs.domain.po.SpecValuePO;

@Mapper
public interface SpecValuePOMapper {
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: spec_value")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    long count(SelectStatementProvider selectStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: spec_value")
    @DeleteProvider(type=SqlProviderAdapter.class, method="delete")
    int delete(DeleteStatementProvider deleteStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: spec_value")
    @InsertProvider(type=SqlProviderAdapter.class, method="insert")
    @Options(useGeneratedKeys=true,keyProperty="record.id")
    int insert(InsertStatementProvider<SpecValuePO> insertStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: spec_value")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @ResultMap("SpecValuePOResult")
    SpecValuePO selectOne(SelectStatementProvider selectStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: spec_value")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @Results(id="SpecValuePOResult", value = {
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="spec_id", property="specId", jdbcType=JdbcType.INTEGER),
        @Result(column="value", property="value", jdbcType=JdbcType.VARCHAR)
    })
    List<SpecValuePO> selectMany(SelectStatementProvider selectStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: spec_value")
    @UpdateProvider(type=SqlProviderAdapter.class, method="update")
    int update(UpdateStatementProvider updateStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: spec_value")
    default QueryExpressionDSL<MyBatis3SelectModelAdapter<Long>> countByExample() {
        return SelectDSL.selectWithMapper(this::count, SqlBuilder.count())
                .from(specValuePO);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: spec_value")
    default DeleteDSL<MyBatis3DeleteModelAdapter<Integer>> deleteByExample() {
        return DeleteDSL.deleteFromWithMapper(this::delete, specValuePO);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: spec_value")
    default int deleteByPrimaryKey(Integer id_) {
        return DeleteDSL.deleteFromWithMapper(this::delete, specValuePO)
                .where(id, isEqualTo(id_))
                .build()
                .execute();
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: spec_value")
    default int insert(SpecValuePO record) {
        return insert(SqlBuilder.insert(record)
                .into(specValuePO)
                .map(specId).toProperty("specId")
                .map(value).toProperty("value")
                .build()
                .render(RenderingStrategy.MYBATIS3));
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: spec_value")
    default int insertSelective(SpecValuePO record) {
        return insert(SqlBuilder.insert(record)
                .into(specValuePO)
                .map(specId).toPropertyWhenPresent("specId", record::getSpecId)
                .map(value).toPropertyWhenPresent("value", record::getValue)
                .build()
                .render(RenderingStrategy.MYBATIS3));
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: spec_value")
    default QueryExpressionDSL<MyBatis3SelectModelAdapter<List<SpecValuePO>>> selectByExample() {
        return SelectDSL.selectWithMapper(this::selectMany, id, specId, value)
                .from(specValuePO);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: spec_value")
    default QueryExpressionDSL<MyBatis3SelectModelAdapter<List<SpecValuePO>>> selectDistinctByExample() {
        return SelectDSL.selectDistinctWithMapper(this::selectMany, id, specId, value)
                .from(specValuePO);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: spec_value")
    default SpecValuePO selectByPrimaryKey(Integer id_) {
        return SelectDSL.selectWithMapper(this::selectOne, id, specId, value)
                .from(specValuePO)
                .where(id, isEqualTo(id_))
                .build()
                .execute();
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: spec_value")
    default UpdateDSL<MyBatis3UpdateModelAdapter<Integer>> updateByExample(SpecValuePO record) {
        return UpdateDSL.updateWithMapper(this::update, specValuePO)
                .set(specId).equalTo(record::getSpecId)
                .set(value).equalTo(record::getValue);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: spec_value")
    default UpdateDSL<MyBatis3UpdateModelAdapter<Integer>> updateByExampleSelective(SpecValuePO record) {
        return UpdateDSL.updateWithMapper(this::update, specValuePO)
                .set(specId).equalToWhenPresent(record::getSpecId)
                .set(value).equalToWhenPresent(record::getValue);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: spec_value")
    default int updateByPrimaryKey(SpecValuePO record) {
        return UpdateDSL.updateWithMapper(this::update, specValuePO)
                .set(specId).equalTo(record::getSpecId)
                .set(value).equalTo(record::getValue)
                .where(id, isEqualTo(record::getId))
                .build()
                .execute();
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: spec_value")
    default int updateByPrimaryKeySelective(SpecValuePO record) {
        return UpdateDSL.updateWithMapper(this::update, specValuePO)
                .set(specId).equalToWhenPresent(record::getSpecId)
                .set(value).equalToWhenPresent(record::getValue)
                .where(id, isEqualTo(record::getId))
                .build()
                .execute();
    }
}