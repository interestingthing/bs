package personal.bs.dao.mapper;

import java.sql.JDBCType;
import javax.annotation.Generated;
import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

public final class UserPODynamicSqlSupport {
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: user")
    public static final UserPO userPO = new UserPO();

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source field: user.id")
    public static final SqlColumn<Integer> id = userPO.id;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source field: user.username")
    public static final SqlColumn<String> username = userPO.username;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source field: user.password")
    public static final SqlColumn<String> password = userPO.password;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source field: user.nickname")
    public static final SqlColumn<String> nickname = userPO.nickname;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source field: user.phone")
    public static final SqlColumn<String> phone = userPO.phone;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source field: user.email")
    public static final SqlColumn<String> email = userPO.email;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: user")
    public static final class UserPO extends SqlTable {
        public final SqlColumn<Integer> id = column("id", JDBCType.INTEGER);

        public final SqlColumn<String> username = column("username", JDBCType.VARCHAR);

        public final SqlColumn<String> password = column("password", JDBCType.VARCHAR);

        public final SqlColumn<String> nickname = column("nickname", JDBCType.VARCHAR);

        public final SqlColumn<String> phone = column("phone", JDBCType.VARCHAR);

        public final SqlColumn<String> email = column("email", JDBCType.VARCHAR);

        public UserPO() {
            super("user");
        }
    }
}