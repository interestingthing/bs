package personal.bs.domain.vo;

import lombok.Data;
import personal.bs.domain.po.OrderItemPO;

import java.io.Serializable;
import java.util.List;

/**
 * 购物车对象
 */
@Data
public class Cart implements Serializable {
    //商家ID
    private Integer sellerId;
    //商家名称
    private String sellerName;

    private List<OrderItemPO> orderItemList;
    //购物车明细集合


}
