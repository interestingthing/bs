package personal.bs.dao.mapper;

import java.sql.JDBCType;
import javax.annotation.Generated;
import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

public final class SpecPODynamicSqlSupport {
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: spec")
    public static final SpecPO specPO = new SpecPO();

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source field: spec.id")
    public static final SqlColumn<Integer> id = specPO.id;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source field: spec.name")
    public static final SqlColumn<String> name = specPO.name;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source field: spec.type_id")
    public static final SqlColumn<Integer> typeId = specPO.typeId;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: spec")
    public static final class SpecPO extends SqlTable {
        public final SqlColumn<Integer> id = column("id", JDBCType.INTEGER);

        public final SqlColumn<String> name = column("name", JDBCType.VARCHAR);

        public final SqlColumn<Integer> typeId = column("type_id", JDBCType.INTEGER);

        public SpecPO() {
            super("spec");
        }
    }
}