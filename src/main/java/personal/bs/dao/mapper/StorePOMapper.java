package personal.bs.dao.mapper;

import static org.mybatis.dynamic.sql.SqlBuilder.*;
import static personal.bs.dao.mapper.StorePODynamicSqlSupport.*;

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
import personal.bs.domain.po.StorePO;

@Mapper
public interface StorePOMapper {
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: store")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    long count(SelectStatementProvider selectStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: store")
    @DeleteProvider(type=SqlProviderAdapter.class, method="delete")
    int delete(DeleteStatementProvider deleteStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: store")
    @InsertProvider(type=SqlProviderAdapter.class, method="insert")
    @Options(useGeneratedKeys=true,keyProperty="record.id")
    int insert(InsertStatementProvider<StorePO> insertStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: store")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @ResultMap("StorePOResult")
    StorePO selectOne(SelectStatementProvider selectStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: store")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @Results(id="StorePOResult", value = {
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
        @Result(column="user_id", property="userId", jdbcType=JdbcType.INTEGER)
    })
    List<StorePO> selectMany(SelectStatementProvider selectStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: store")
    @UpdateProvider(type=SqlProviderAdapter.class, method="update")
    int update(UpdateStatementProvider updateStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: store")
    default QueryExpressionDSL<MyBatis3SelectModelAdapter<Long>> countByExample() {
        return SelectDSL.selectWithMapper(this::count, SqlBuilder.count())
                .from(storePO);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: store")
    default DeleteDSL<MyBatis3DeleteModelAdapter<Integer>> deleteByExample() {
        return DeleteDSL.deleteFromWithMapper(this::delete, storePO);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: store")
    default int deleteByPrimaryKey(Integer id_) {
        return DeleteDSL.deleteFromWithMapper(this::delete, storePO)
                .where(id, isEqualTo(id_))
                .build()
                .execute();
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: store")
    default int insert(StorePO record) {
        return insert(SqlBuilder.insert(record)
                .into(storePO)
                .map(name).toProperty("name")
                .map(userId).toProperty("userId")
                .build()
                .render(RenderingStrategy.MYBATIS3));
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: store")
    default int insertSelective(StorePO record) {
        return insert(SqlBuilder.insert(record)
                .into(storePO)
                .map(name).toPropertyWhenPresent("name", record::getName)
                .map(userId).toPropertyWhenPresent("userId", record::getUserId)
                .build()
                .render(RenderingStrategy.MYBATIS3));
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: store")
    default QueryExpressionDSL<MyBatis3SelectModelAdapter<List<StorePO>>> selectByExample() {
        return SelectDSL.selectWithMapper(this::selectMany, id, name, userId)
                .from(storePO);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: store")
    default QueryExpressionDSL<MyBatis3SelectModelAdapter<List<StorePO>>> selectDistinctByExample() {
        return SelectDSL.selectDistinctWithMapper(this::selectMany, id, name, userId)
                .from(storePO);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: store")
    default StorePO selectByPrimaryKey(Integer id_) {
        return SelectDSL.selectWithMapper(this::selectOne, id, name, userId)
                .from(storePO)
                .where(id, isEqualTo(id_))
                .build()
                .execute();
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: store")
    default UpdateDSL<MyBatis3UpdateModelAdapter<Integer>> updateByExample(StorePO record) {
        return UpdateDSL.updateWithMapper(this::update, storePO)
                .set(name).equalTo(record::getName)
                .set(userId).equalTo(record::getUserId);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: store")
    default UpdateDSL<MyBatis3UpdateModelAdapter<Integer>> updateByExampleSelective(StorePO record) {
        return UpdateDSL.updateWithMapper(this::update, storePO)
                .set(name).equalToWhenPresent(record::getName)
                .set(userId).equalToWhenPresent(record::getUserId);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: store")
    default int updateByPrimaryKey(StorePO record) {
        return UpdateDSL.updateWithMapper(this::update, storePO)
                .set(name).equalTo(record::getName)
                .set(userId).equalTo(record::getUserId)
                .where(id, isEqualTo(record::getId))
                .build()
                .execute();
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: store")
    default int updateByPrimaryKeySelective(StorePO record) {
        return UpdateDSL.updateWithMapper(this::update, storePO)
                .set(name).equalToWhenPresent(record::getName)
                .set(userId).equalToWhenPresent(record::getUserId)
                .where(id, isEqualTo(record::getId))
                .build()
                .execute();
    }
}