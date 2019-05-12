package personal.bs.domain.vo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import personal.bs.domain.po.SkuPO;
import personal.bs.domain.po.SpuPO;
import personal.bs.domain.po.TbGoodsDesc;

import java.io.Serializable;
import java.util.List;

/**
 * 商品的组合实体类
 *
 * @author jt
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Goods implements Serializable {

    //商品SPU基本信息
    private SpuPO goods;

    //商品SPU扩展信息
    private GoodsDescDto goodsDesc;

    //SKU列表
    private List<SkuPO> skuPOList;


}
