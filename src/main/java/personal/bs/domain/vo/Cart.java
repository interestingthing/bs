package personal.bs.domain.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * 购物车对象
 * @author Administrator
 *
 */
@Data
public class Cart implements Serializable{

	private String sellerId;//商家ID
	private String sellerName;//商家名称

	//private List<TbOrderItem> orderItemList;//购物车明细集合
	
	
}
