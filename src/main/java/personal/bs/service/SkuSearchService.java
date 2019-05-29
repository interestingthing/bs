package personal.bs.service;

import personal.bs.domain.po.SkuPO;

import java.util.List;
import java.util.Map;

public interface SkuSearchService {

	
	/**
	 * 搜索方法
	 * @param searchMap
	 * @return
	 */
	public Map search(Map searchMap);
	
	/**
	 * 导入列表
	 */
	public void importToSolr(List<SkuPO> list);
	
	/**
	 * 删除商品列表
	 * @param goodsIds  (SPU)
	 */
	public void deleteByGoodsIds(List goodsIds);
	
}
