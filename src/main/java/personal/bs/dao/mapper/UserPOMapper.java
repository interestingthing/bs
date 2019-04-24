package personal.bs.dao.mapper;

import static org.mybatis.dynamic.sql.SqlBuilder.*;
import static personal.bs.dao.mapper.UserPODynamicSqlSupport.*;

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
import personal.bs.domain.po.UserPO;

@Mapper
public interface UserPOMapper {
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: user")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    long count(SelectStatementProvider selectStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: user")
    @DeleteProvider(type=SqlProviderAdapter.class, method="delete")
    int delete(DeleteStatementProvider deleteStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: user")
    @InsertProvider(type=SqlProviderAdapter.class, method="insert")
    @Options(useGeneratedKeys=true,keyProperty="record.id")
    int insert(InsertStatementProvider<UserPO> insertStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: user")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @ResultMap("UserPOResult")
    UserPO selectOne(SelectStatementProvider selectStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: user")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @Results(id="UserPOResult", value = {
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="username", property="username", jdbcType=JdbcType.VARCHAR),
        @Result(column="password", property="password", jdbcType=JdbcType.VARCHAR),
        @Result(column="nickname", property="nickname", jdbcType=JdbcType.VARCHAR),
        @Result(column="phone", property="phone", jdbcType=JdbcType.VARCHAR),
        @Result(column="email", property="email", jdbcType=JdbcType.VARCHAR)
    })
    List<UserPO> selectMany(SelectStatementProvider selectStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: user")
    @UpdateProvider(type=SqlProviderAdapter.class, method="update")
    int update(UpdateStatementProvider updateStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: user")
    default QueryExpressionDSL<MyBatis3SelectModelAdapter<Long>> countByExample() {
        return SelectDSL.selectWithMapper(this::count, SqlBuilder.count())
                .from(userPO);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: user")
    default DeleteDSL<MyBatis3DeleteModelAdapter<Integer>> deleteByExample() {
        return DeleteDSL.deleteFromWithMapper(this::delete, userPO);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: user")
    default int deleteByPrimaryKey(Integer id_) {
        return DeleteDSL.deleteFromWithMapper(this::delete, userPO)
                .where(id, isEqualTo(id_))
                .build()
                .execute();
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: user")
    default int insert(UserPO record) {
        return insert(SqlBuilder.insert(record)
                .into(userPO)
                .map(username).toProperty("username")
                .map(password).toProperty("password")
                .map(nickname).toProperty("nickname")
                .map(phone).toProperty("phone")
                .map(email).toProperty("email")
                .build()
                .render(RenderingStrategy.MYBATIS3));
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: user")
    default int insertSelective(UserPO record) {
        return insert(SqlBuilder.insert(record)
                .into(userPO)
                .map(username).toPropertyWhenPresent("username", record::getUsername)
                .map(password).toPropertyWhenPresent("password", record::getPassword)
                .map(nickname).toPropertyWhenPresent("nickname", record::getNickname)
                .map(phone).toPropertyWhenPresent("phone", record::getPhone)
                .map(email).toPropertyWhenPresent("email", record::getEmail)
                .build()
                .render(RenderingStrategy.MYBATIS3));
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: user")
    default QueryExpressionDSL<MyBatis3SelectModelAdapter<List<UserPO>>> selectByExample() {
        return SelectDSL.selectWithMapper(this::selectMany, id, username, password, nickname, phone, email)
                .from(userPO);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: user")
    default QueryExpressionDSL<MyBatis3SelectModelAdapter<List<UserPO>>> selectDistinctByExample() {
        return SelectDSL.selectDistinctWithMapper(this::selectMany, id, username, password, nickname, phone, email)
                .from(userPO);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: user")
    default UserPO selectByPrimaryKey(Integer id_) {
        return SelectDSL.selectWithMapper(this::selectOne, id, username, password, nickname, phone, email)
                .from(userPO)
                .where(id, isEqualTo(id_))
                .build()
                .execute();
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: user")
    default UpdateDSL<MyBatis3UpdateModelAdapter<Integer>> updateByExample(UserPO record) {
        return UpdateDSL.updateWithMapper(this::update, userPO)
                .set(username).equalTo(record::getUsername)
                .set(password).equalTo(record::getPassword)
                .set(nickname).equalTo(record::getNickname)
                .set(phone).equalTo(record::getPhone)
                .set(email).equalTo(record::getEmail);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: user")
    default UpdateDSL<MyBatis3UpdateModelAdapter<Integer>> updateByExampleSelective(UserPO record) {
        return UpdateDSL.updateWithMapper(this::update, userPO)
                .set(username).equalToWhenPresent(record::getUsername)
                .set(password).equalToWhenPresent(record::getPassword)
                .set(nickname).equalToWhenPresent(record::getNickname)
                .set(phone).equalToWhenPresent(record::getPhone)
                .set(email).equalToWhenPresent(record::getEmail);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: user")
    default int updateByPrimaryKey(UserPO record) {
        return UpdateDSL.updateWithMapper(this::update, userPO)
                .set(username).equalTo(record::getUsername)
                .set(password).equalTo(record::getPassword)
                .set(nickname).equalTo(record::getNickname)
                .set(phone).equalTo(record::getPhone)
                .set(email).equalTo(record::getEmail)
                .where(id, isEqualTo(record::getId))
                .build()
                .execute();
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: user")
    default int updateByPrimaryKeySelective(UserPO record) {
        return UpdateDSL.updateWithMapper(this::update, userPO)
                .set(username).equalToWhenPresent(record::getUsername)
                .set(password).equalToWhenPresent(record::getPassword)
                .set(nickname).equalToWhenPresent(record::getNickname)
                .set(phone).equalToWhenPresent(record::getPhone)
                .set(email).equalToWhenPresent(record::getEmail)
                .where(id, isEqualTo(record::getId))
                .build()
                .execute();
    }
}