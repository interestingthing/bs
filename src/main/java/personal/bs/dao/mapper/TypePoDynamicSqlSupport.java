package personal.bs.dao.mapper;

import java.sql.JDBCType;
import javax.annotation.Generated;
import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

public final class TypePoDynamicSqlSupport {
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: type")
    public static final TypePo typePo = new TypePo();

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source field: type.id")
    public static final SqlColumn<Integer> id = typePo.id;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source field: type.name")
    public static final SqlColumn<String> name = typePo.name;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source field: type.pid")
    public static final SqlColumn<Integer> pid = typePo.pid;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: type")
    public static final class TypePo extends SqlTable {
        public final SqlColumn<Integer> id = column("id", JDBCType.INTEGER);

        public final SqlColumn<String> name = column("name", JDBCType.VARCHAR);

        public final SqlColumn<Integer> pid = column("pid", JDBCType.INTEGER);

        public TypePo() {
            super("type");
        }
    }
}