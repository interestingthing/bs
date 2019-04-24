package personal.bs.dao.mapper;

import static org.mybatis.dynamic.sql.SqlBuilder.*;
import static personal.bs.dao.mapper.SpecPODynamicSqlSupport.*;

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
import personal.bs.domain.po.SpecPO;

@Mapper
public interface SpecPOMapper {
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: spec")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    long count(SelectStatementProvider selectStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: spec")
    @DeleteProvider(type=SqlProviderAdapter.class, method="delete")
    int delete(DeleteStatementProvider deleteStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: spec")
    @InsertProvider(type=SqlProviderAdapter.class, method="insert")
    @Options(useGeneratedKeys=true,keyProperty="record.id")
    int insert(InsertStatementProvider<SpecPO> insertStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: spec")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @ResultMap("SpecPOResult")
    SpecPO selectOne(SelectStatementProvider selectStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: spec")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @Results(id="SpecPOResult", value = {
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
        @Result(column="type_id", property="typeId", jdbcType=JdbcType.INTEGER)
    })
    List<SpecPO> selectMany(SelectStatementProvider selectStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: spec")
    @UpdateProvider(type=SqlProviderAdapter.class, method="update")
    int update(UpdateStatementProvider updateStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: spec")
    default QueryExpressionDSL<MyBatis3SelectModelAdapter<Long>> countByExample() {
        return SelectDSL.selectWithMapper(this::count, SqlBuilder.count())
                .from(specPO);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: spec")
    default DeleteDSL<MyBatis3DeleteModelAdapter<Integer>> deleteByExample() {
        return DeleteDSL.deleteFromWithMapper(this::delete, specPO);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: spec")
    default int deleteByPrimaryKey(Integer id_) {
        return DeleteDSL.deleteFromWithMapper(this::delete, specPO)
                .where(id, isEqualTo(id_))
                .build()
                .execute();
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: spec")
    default int insert(SpecPO record) {
        return insert(SqlBuilder.insert(record)
                .into(specPO)
                .map(name).toProperty("name")
                .map(typeId).toProperty("typeId")
                .build()
                .render(RenderingStrategy.MYBATIS3));
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: spec")
    default int insertSelective(SpecPO record) {
        return insert(SqlBuilder.insert(record)
                .into(specPO)
                .map(name).toPropertyWhenPresent("name", record::getName)
                .map(typeId).toPropertyWhenPresent("typeId", record::getTypeId)
                .build()
                .render(RenderingStrategy.MYBATIS3));
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: spec")
    default QueryExpressionDSL<MyBatis3SelectModelAdapter<List<SpecPO>>> selectByExample() {
        return SelectDSL.selectWithMapper(this::selectMany, id, name, typeId)
                .from(specPO);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: spec")
    default QueryExpressionDSL<MyBatis3SelectModelAdapter<List<SpecPO>>> selectDistinctByExample() {
        return SelectDSL.selectDistinctWithMapper(this::selectMany, id, name, typeId)
                .from(specPO);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: spec")
    default SpecPO selectByPrimaryKey(Integer id_) {
        return SelectDSL.selectWithMapper(this::selectOne, id, name, typeId)
                .from(specPO)
                .where(id, isEqualTo(id_))
                .build()
                .execute();
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: spec")
    default UpdateDSL<MyBatis3UpdateModelAdapter<Integer>> updateByExample(SpecPO record) {
        return UpdateDSL.updateWithMapper(this::update, specPO)
                .set(name).equalTo(record::getName)
                .set(typeId).equalTo(record::getTypeId);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: spec")
    default UpdateDSL<MyBatis3UpdateModelAdapter<Integer>> updateByExampleSelective(SpecPO record) {
        return UpdateDSL.updateWithMapper(this::update, specPO)
                .set(name).equalToWhenPresent(record::getName)
                .set(typeId).equalToWhenPresent(record::getTypeId);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: spec")
    default int updateByPrimaryKey(SpecPO record) {
        return UpdateDSL.updateWithMapper(this::update, specPO)
                .set(name).equalTo(record::getName)
                .set(typeId).equalTo(record::getTypeId)
                .where(id, isEqualTo(record::getId))
                .build()
                .execute();
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: spec")
    default int updateByPrimaryKeySelective(SpecPO record) {
        return UpdateDSL.updateWithMapper(this::update, specPO)
                .set(name).equalToWhenPresent(record::getName)
                .set(typeId).equalToWhenPresent(record::getTypeId)
                .where(id, isEqualTo(record::getId))
                .build()
                .execute();
    }
}