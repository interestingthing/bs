package personal.bs.service;


import personal.bs.domain.vo.Cart;

import java.util.List;

/**
 * 购物车服务接口
 * @author Administrator
 *
 */
public interface CartService {

	/**
	 * 添加商品到购物车
	 * @param itemId
	 * @param num
	 * @return
	 */
	public List<Cart> addGoodsToCartList(List<Cart> cartList, Integer itemId, Integer num);
	
	/**
	 * 从redis中提取购物车列表
	 * @return
	 */
	public List<Cart> findCartListFromRedis(Integer id);
	
	/**
	 * 将购物车列表存入redis
	 * @param cartList
	 */
	public void saveCartListToRedis(Integer id,List<Cart> cartList);
	
	/**
	 * 合并购物车
	 * @param cartList1
	 * @param cartList2
	 * @return
	 */
	public List<Cart> mergeCartList(List<Cart> cartList1,List<Cart> cartList2);
	 
}
