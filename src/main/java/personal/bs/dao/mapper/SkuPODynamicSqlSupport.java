package personal.bs.dao.mapper;

import java.math.BigDecimal;
import java.sql.JDBCType;
import javax.annotation.Generated;
import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

public final class SkuPODynamicSqlSupport {
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: sku")
    public static final SkuPO skuPO = new SkuPO();

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source field: sku.id")
    public static final SqlColumn<Integer> id = skuPO.id;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source field: sku.spu_id")
    public static final SqlColumn<Integer> spuId = skuPO.spuId;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source field: sku.img_url")
    public static final SqlColumn<String> imgUrl = skuPO.imgUrl;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source field: sku.price")
    public static final SqlColumn<BigDecimal> price = skuPO.price;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source field: sku.repertory")
    public static final SqlColumn<String> repertory = skuPO.repertory;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source field: sku.spu_spec_ids")
    public static final SqlColumn<String> spuSpecIds = skuPO.spuSpecIds;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: sku")
    public static final class SkuPO extends SqlTable {
        public final SqlColumn<Integer> id = column("id", JDBCType.INTEGER);

        public final SqlColumn<Integer> spuId = column("spu_id", JDBCType.INTEGER);

        public final SqlColumn<String> imgUrl = column("img_url", JDBCType.VARCHAR);

        public final SqlColumn<BigDecimal> price = column("price", JDBCType.DECIMAL);

        public final SqlColumn<String> repertory = column("repertory", JDBCType.VARCHAR);

        public final SqlColumn<String> spuSpecIds = column("spu_spec_ids", JDBCType.VARCHAR);

        public SkuPO() {
            super("sku");
        }
    }
}