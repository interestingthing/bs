package personal.bs.dao.mapper;

import java.sql.JDBCType;
import javax.annotation.Generated;
import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

public final class SpuSpecPODynamicSqlSupport {
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: spu_spec")
    public static final SpuSpecPO spuSpecPO = new SpuSpecPO();

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source field: spu_spec.id")
    public static final SqlColumn<Integer> id = spuSpecPO.id;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source field: spu_spec.spu_id")
    public static final SqlColumn<Integer> spuId = spuSpecPO.spuId;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source field: spu_spec.spec_id")
    public static final SqlColumn<Integer> specId = spuSpecPO.specId;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source field: spu_spec.spec_value_id")
    public static final SqlColumn<Integer> specValueId = spuSpecPO.specValueId;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: spu_spec")
    public static final class SpuSpecPO extends SqlTable {
        public final SqlColumn<Integer> id = column("id", JDBCType.INTEGER);

        public final SqlColumn<Integer> spuId = column("spu_id", JDBCType.INTEGER);

        public final SqlColumn<Integer> specId = column("spec_id", JDBCType.INTEGER);

        public final SqlColumn<Integer> specValueId = column("spec_value_id", JDBCType.INTEGER);

        public SpuSpecPO() {
            super("spu_spec");
        }
    }
}