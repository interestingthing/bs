package personal.bs.domain.vo;

import lombok.Data;
import personal.bs.domain.po.SkuPO;
import personal.bs.domain.po.SpuPO;
import personal.bs.domain.po.TbGoodsDesc;

import java.io.Serializable;
import java.util.List;

/**
 * 商品的组合实体类
 * @author jt
 *
 */
@Data
public class Goods implements Serializable{

	//商品SPU基本信息
	private SpuPO spuPO;

	//商品SPU扩展信息
	private TbGoodsDesc goodsDesc;

	//SKU列表
	private List<SkuPO> skuPOList;



}
