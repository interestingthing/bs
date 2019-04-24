package personal.bs.dao.mapper;

import java.sql.JDBCType;
import java.util.Date;
import javax.annotation.Generated;
import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

public final class SpuPODynamicSqlSupport {
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: spu")
    public static final SpuPO spuPO = new SpuPO();

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source field: spu.id")
    public static final SqlColumn<Integer> id = spuPO.id;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source field: spu.name")
    public static final SqlColumn<String> name = spuPO.name;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source field: spu.type_id")
    public static final SqlColumn<Integer> typeId = spuPO.typeId;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source field: spu.description")
    public static final SqlColumn<String> description = spuPO.description;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source field: spu.store_id")
    public static final SqlColumn<Integer> storeId = spuPO.storeId;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source field: spu.sale_num")
    public static final SqlColumn<Integer> saleNum = spuPO.saleNum;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source field: spu.upload_date")
    public static final SqlColumn<Date> uploadDate = spuPO.uploadDate;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source field: spu.status")
    public static final SqlColumn<String> status = spuPO.status;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: spu")
    public static final class SpuPO extends SqlTable {
        public final SqlColumn<Integer> id = column("id", JDBCType.INTEGER);

        public final SqlColumn<String> name = column("name", JDBCType.VARCHAR);

        public final SqlColumn<Integer> typeId = column("type_id", JDBCType.INTEGER);

        public final SqlColumn<String> description = column("description", JDBCType.VARCHAR);

        public final SqlColumn<Integer> storeId = column("store_id", JDBCType.INTEGER);

        public final SqlColumn<Integer> saleNum = column("sale_num", JDBCType.INTEGER);

        public final SqlColumn<Date> uploadDate = column("upload_date", JDBCType.TIMESTAMP);

        public final SqlColumn<String> status = column("status", JDBCType.VARCHAR);

        public SpuPO() {
            super("spu");
        }
    }
}