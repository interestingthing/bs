package personal.bs.dao.mapper;

import static org.mybatis.dynamic.sql.SqlBuilder.*;
import static personal.bs.dao.mapper.TypePoDynamicSqlSupport.*;

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
import personal.bs.domain.po.TypePo;

@Mapper
public interface TypePoMapper {
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: type")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    long count(SelectStatementProvider selectStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: type")
    @DeleteProvider(type=SqlProviderAdapter.class, method="delete")
    int delete(DeleteStatementProvider deleteStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: type")
    @InsertProvider(type=SqlProviderAdapter.class, method="insert")
    @Options(useGeneratedKeys=true,keyProperty="record.id")
    int insert(InsertStatementProvider<TypePo> insertStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: type")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @ResultMap("TypePoResult")
    TypePo selectOne(SelectStatementProvider selectStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: type")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @Results(id="TypePoResult", value = {
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
        @Result(column="pid", property="pid", jdbcType=JdbcType.INTEGER)
    })
    List<TypePo> selectMany(SelectStatementProvider selectStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: type")
    @UpdateProvider(type=SqlProviderAdapter.class, method="update")
    int update(UpdateStatementProvider updateStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: type")
    default QueryExpressionDSL<MyBatis3SelectModelAdapter<Long>> countByExample() {
        return SelectDSL.selectWithMapper(this::count, SqlBuilder.count())
                .from(typePo);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: type")
    default DeleteDSL<MyBatis3DeleteModelAdapter<Integer>> deleteByExample() {
        return DeleteDSL.deleteFromWithMapper(this::delete, typePo);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: type")
    default int deleteByPrimaryKey(Integer id_) {
        return DeleteDSL.deleteFromWithMapper(this::delete, typePo)
                .where(id, isEqualTo(id_))
                .build()
                .execute();
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: type")
    default int insert(TypePo record) {
        return insert(SqlBuilder.insert(record)
                .into(typePo)
                .map(name).toProperty("name")
                .map(pid).toProperty("pid")
                .build()
                .render(RenderingStrategy.MYBATIS3));
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: type")
    default int insertSelective(TypePo record) {
        return insert(SqlBuilder.insert(record)
                .into(typePo)
                .map(name).toPropertyWhenPresent("name", record::getName)
                .map(pid).toPropertyWhenPresent("pid", record::getPid)
                .build()
                .render(RenderingStrategy.MYBATIS3));
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: type")
    default QueryExpressionDSL<MyBatis3SelectModelAdapter<List<TypePo>>> selectByExample() {
        return SelectDSL.selectWithMapper(this::selectMany, id, name, pid)
                .from(typePo);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: type")
    default QueryExpressionDSL<MyBatis3SelectModelAdapter<List<TypePo>>> selectDistinctByExample() {
        return SelectDSL.selectDistinctWithMapper(this::selectMany, id, name, pid)
                .from(typePo);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: type")
    default TypePo selectByPrimaryKey(Integer id_) {
        return SelectDSL.selectWithMapper(this::selectOne, id, name, pid)
                .from(typePo)
                .where(id, isEqualTo(id_))
                .build()
                .execute();
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: type")
    default UpdateDSL<MyBatis3UpdateModelAdapter<Integer>> updateByExample(TypePo record) {
        return UpdateDSL.updateWithMapper(this::update, typePo)
                .set(name).equalTo(record::getName)
                .set(pid).equalTo(record::getPid);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: type")
    default UpdateDSL<MyBatis3UpdateModelAdapter<Integer>> updateByExampleSelective(TypePo record) {
        return UpdateDSL.updateWithMapper(this::update, typePo)
                .set(name).equalToWhenPresent(record::getName)
                .set(pid).equalToWhenPresent(record::getPid);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: type")
    default int updateByPrimaryKey(TypePo record) {
        return UpdateDSL.updateWithMapper(this::update, typePo)
                .set(name).equalTo(record::getName)
                .set(pid).equalTo(record::getPid)
                .where(id, isEqualTo(record::getId))
                .build()
                .execute();
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: type")
    default int updateByPrimaryKeySelective(TypePo record) {
        return UpdateDSL.updateWithMapper(this::update, typePo)
                .set(name).equalToWhenPresent(record::getName)
                .set(pid).equalToWhenPresent(record::getPid)
                .where(id, isEqualTo(record::getId))
                .build()
                .execute();
    }
}