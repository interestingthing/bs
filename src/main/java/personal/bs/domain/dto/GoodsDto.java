package personal.bs.domain.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import personal.bs.domain.po.SpuPO;

import java.io.Serializable;
import java.util.List;

/**
 * 商品的组合实体类
 *
 * @author jt
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class GoodsDto implements Serializable {

    //商品SPU基本信息
    private SpuPO goods;

    //商品SPU扩展信息
    private GoodsDescDto goodsDesc;

    //SKU列表
    @JsonProperty("itemList")
    private List<SkuPODto> skuPOList;


}
