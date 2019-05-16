package personal.bs.domain.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
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
public class GoodsVO implements Serializable {

    private SpuPO goods; // 商品信息
    private TbGoodsDesc goodsDesc; // 商品扩展信息
    @JsonProperty("itemList")
    private List<SkuPO> skuPOList; // SKU的列表信息
}
