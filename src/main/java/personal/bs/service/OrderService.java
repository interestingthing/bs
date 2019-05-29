package personal.bs.service;

import personal.bs.domain.po.OrderPO;
import personal.bs.domain.vo.PageResult;

import java.util.List;

/**
 * 服务层接口
 *
 */
public interface OrderService {

	/**
	 * 返回全部列表
	 * @return
	 */
	public List<OrderPO> findAll();


	/**
	 * 返回分页列表
	 * @return
	 */
	public PageResult findPage(int pageNum, int pageSize);


	/**
	 * 增加
	 */
	public void add(OrderPO order);


	/**
	 * 修改
	 */
	public void update(OrderPO order);


	/**
	 * 根据ID获取实体
	 * @param id
	 * @return
	 */
	public OrderPO findOne(Integer id);


	/**
	 * 批量删除
	 * @param ids
	 */
	public void delete(Integer[] ids);

	/**
	 * 分页
	 * @param pageNum 当前页 码
	 * @param pageSize 每页记录数
	 * @return
	 */
	public PageResult findPage(OrderPO order, int pageNum,int pageSize);



	/**
	 * 支付成功修改状态
	 * @param out_trade_no
	 * @param transaction_id
	 */
	public void updateOrderStatus(String out_trade_no,String transaction_id);

}
