package personal.bs.dao.mapper;

import static org.mybatis.dynamic.sql.SqlBuilder.*;
import static personal.bs.dao.mapper.SpuPODynamicSqlSupport.*;

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
import personal.bs.domain.po.SpuPO;

@Mapper
public interface SpuPOMapper {
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: spu")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    long count(SelectStatementProvider selectStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: spu")
    @DeleteProvider(type=SqlProviderAdapter.class, method="delete")
    int delete(DeleteStatementProvider deleteStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: spu")
    @InsertProvider(type=SqlProviderAdapter.class, method="insert")
    @Options(useGeneratedKeys=true,keyProperty="record.id")
    int insert(InsertStatementProvider<SpuPO> insertStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: spu")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @ResultMap("SpuPOResult")
    SpuPO selectOne(SelectStatementProvider selectStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: spu")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @Results(id="SpuPOResult", value = {
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
        @Result(column="type_id", property="typeId", jdbcType=JdbcType.INTEGER),
        @Result(column="description", property="description", jdbcType=JdbcType.VARCHAR),
        @Result(column="store_id", property="storeId", jdbcType=JdbcType.INTEGER),
        @Result(column="sale_num", property="saleNum", jdbcType=JdbcType.INTEGER),
        @Result(column="upload_date", property="uploadDate", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="status", property="status", jdbcType=JdbcType.VARCHAR)
    })
    List<SpuPO> selectMany(SelectStatementProvider selectStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: spu")
    @UpdateProvider(type=SqlProviderAdapter.class, method="update")
    int update(UpdateStatementProvider updateStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: spu")
    default QueryExpressionDSL<MyBatis3SelectModelAdapter<Long>> countByExample() {
        return SelectDSL.selectWithMapper(this::count, SqlBuilder.count())
                .from(spuPO);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: spu")
    default DeleteDSL<MyBatis3DeleteModelAdapter<Integer>> deleteByExample() {
        return DeleteDSL.deleteFromWithMapper(this::delete, spuPO);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: spu")
    default int deleteByPrimaryKey(Integer id_) {
        return DeleteDSL.deleteFromWithMapper(this::delete, spuPO)
                .where(id, isEqualTo(id_))
                .build()
                .execute();
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: spu")
    default int insert(SpuPO record) {
        return insert(SqlBuilder.insert(record)
                .into(spuPO)
                .map(name).toProperty("name")
                .map(typeId).toProperty("typeId")
                .map(description).toProperty("description")
                .map(storeId).toProperty("storeId")
                .map(saleNum).toProperty("saleNum")
                .map(uploadDate).toProperty("uploadDate")
                .map(status).toProperty("status")
                .build()
                .render(RenderingStrategy.MYBATIS3));
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: spu")
    default int insertSelective(SpuPO record) {
        return insert(SqlBuilder.insert(record)
                .into(spuPO)
                .map(name).toPropertyWhenPresent("name", record::getName)
                .map(typeId).toPropertyWhenPresent("typeId", record::getTypeId)
                .map(description).toPropertyWhenPresent("description", record::getDescription)
                .map(storeId).toPropertyWhenPresent("storeId", record::getStoreId)
                .map(saleNum).toPropertyWhenPresent("saleNum", record::getSaleNum)
                .map(uploadDate).toPropertyWhenPresent("uploadDate", record::getUploadDate)
                .map(status).toPropertyWhenPresent("status", record::getStatus)
                .build()
                .render(RenderingStrategy.MYBATIS3));
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: spu")
    default QueryExpressionDSL<MyBatis3SelectModelAdapter<List<SpuPO>>> selectByExample() {
        return SelectDSL.selectWithMapper(this::selectMany, id, name, typeId, description, storeId, saleNum, uploadDate, status)
                .from(spuPO);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: spu")
    default QueryExpressionDSL<MyBatis3SelectModelAdapter<List<SpuPO>>> selectDistinctByExample() {
        return SelectDSL.selectDistinctWithMapper(this::selectMany, id, name, typeId, description, storeId, saleNum, uploadDate, status)
                .from(spuPO);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: spu")
    default SpuPO selectByPrimaryKey(Integer id_) {
        return SelectDSL.selectWithMapper(this::selectOne, id, name, typeId, description, storeId, saleNum, uploadDate, status)
                .from(spuPO)
                .where(id, isEqualTo(id_))
                .build()
                .execute();
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: spu")
    default UpdateDSL<MyBatis3UpdateModelAdapter<Integer>> updateByExample(SpuPO record) {
        return UpdateDSL.updateWithMapper(this::update, spuPO)
                .set(name).equalTo(record::getName)
                .set(typeId).equalTo(record::getTypeId)
                .set(description).equalTo(record::getDescription)
                .set(storeId).equalTo(record::getStoreId)
                .set(saleNum).equalTo(record::getSaleNum)
                .set(uploadDate).equalTo(record::getUploadDate)
                .set(status).equalTo(record::getStatus);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: spu")
    default UpdateDSL<MyBatis3UpdateModelAdapter<Integer>> updateByExampleSelective(SpuPO record) {
        return UpdateDSL.updateWithMapper(this::update, spuPO)
                .set(name).equalToWhenPresent(record::getName)
                .set(typeId).equalToWhenPresent(record::getTypeId)
                .set(description).equalToWhenPresent(record::getDescription)
                .set(storeId).equalToWhenPresent(record::getStoreId)
                .set(saleNum).equalToWhenPresent(record::getSaleNum)
                .set(uploadDate).equalToWhenPresent(record::getUploadDate)
                .set(status).equalToWhenPresent(record::getStatus);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: spu")
    default int updateByPrimaryKey(SpuPO record) {
        return UpdateDSL.updateWithMapper(this::update, spuPO)
                .set(name).equalTo(record::getName)
                .set(typeId).equalTo(record::getTypeId)
                .set(description).equalTo(record::getDescription)
                .set(storeId).equalTo(record::getStoreId)
                .set(saleNum).equalTo(record::getSaleNum)
                .set(uploadDate).equalTo(record::getUploadDate)
                .set(status).equalTo(record::getStatus)
                .where(id, isEqualTo(record::getId))
                .build()
                .execute();
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: spu")
    default int updateByPrimaryKeySelective(SpuPO record) {
        return UpdateDSL.updateWithMapper(this::update, spuPO)
                .set(name).equalToWhenPresent(record::getName)
                .set(typeId).equalToWhenPresent(record::getTypeId)
                .set(description).equalToWhenPresent(record::getDescription)
                .set(storeId).equalToWhenPresent(record::getStoreId)
                .set(saleNum).equalToWhenPresent(record::getSaleNum)
                .set(uploadDate).equalToWhenPresent(record::getUploadDate)
                .set(status).equalToWhenPresent(record::getStatus)
                .where(id, isEqualTo(record::getId))
                .build()
                .execute();
    }
}