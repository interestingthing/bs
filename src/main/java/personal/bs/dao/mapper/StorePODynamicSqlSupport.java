package personal.bs.dao.mapper;

import java.sql.JDBCType;
import javax.annotation.Generated;
import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

public final class StorePODynamicSqlSupport {
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: store")
    public static final StorePO storePO = new StorePO();

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source field: store.id")
    public static final SqlColumn<Integer> id = storePO.id;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source field: store.name")
    public static final SqlColumn<String> name = storePO.name;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source field: store.user_id")
    public static final SqlColumn<Integer> userId = storePO.userId;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: store")
    public static final class StorePO extends SqlTable {
        public final SqlColumn<Integer> id = column("id", JDBCType.INTEGER);

        public final SqlColumn<String> name = column("name", JDBCType.VARCHAR);

        public final SqlColumn<Integer> userId = column("user_id", JDBCType.INTEGER);

        public StorePO() {
            super("store");
        }
    }
}