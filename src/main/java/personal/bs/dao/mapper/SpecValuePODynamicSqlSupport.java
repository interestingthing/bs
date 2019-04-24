package personal.bs.dao.mapper;

import java.sql.JDBCType;
import javax.annotation.Generated;
import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

public final class SpecValuePODynamicSqlSupport {
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: spec_value")
    public static final SpecValuePO specValuePO = new SpecValuePO();

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source field: spec_value.id")
    public static final SqlColumn<Integer> id = specValuePO.id;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source field: spec_value.spec_id")
    public static final SqlColumn<Integer> specId = specValuePO.specId;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source field: spec_value.value")
    public static final SqlColumn<String> value = specValuePO.value;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: spec_value")
    public static final class SpecValuePO extends SqlTable {
        public final SqlColumn<Integer> id = column("id", JDBCType.INTEGER);

        public final SqlColumn<Integer> specId = column("spec_id", JDBCType.INTEGER);

        public final SqlColumn<String> value = column("value", JDBCType.VARCHAR);

        public SpecValuePO() {
            super("spec_value");
        }
    }
}