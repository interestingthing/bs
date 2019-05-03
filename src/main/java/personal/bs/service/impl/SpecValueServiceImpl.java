package personal.bs.service.impl;


import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import personal.bs.dao.mapper.SpecValuePOMapper;
import personal.bs.domain.po.SpecValuePO;
import personal.bs.domain.po.SpecValuePOExample;
import personal.bs.domain.vo.PageResult;
import personal.bs.service.SpecValueService;

import javax.annotation.Resource;
import java.util.List;

/**
 * 服务实现层
 * @author Administrator
 *
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class SpecValueServiceImpl implements SpecValueService {

	@Resource
	private SpecValuePOMapper specValuePOMapper;

	/**
	 * 查询全部
	 */
	@Override
	public List<SpecValuePO> findAll() {
		return specValuePOMapper.selectByExample(null);
	}

	/**
	 * 按分页查询
	 */
	@Override
	public PageResult findPage(int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		Page<SpecValuePO> page=   (Page<SpecValuePO>) specValuePOMapper.selectByExample(null);
		return new PageResult(page.getTotal(), page.getResult());
	}

	/**
	 * 增加
	 */
	@Override
	public void add(SpecValuePO specValuePO) {
		specValuePOMapper.insert(specValuePO);
	}


	/**
	 * 修改
	 */
	@Override
	public void update(SpecValuePO specValuePO){
		specValuePOMapper.updateByPrimaryKey(specValuePO);
	}

	/**
	 * 根据ID获取实体
	 * @param id
	 * @return
	 */
	@Override
	public SpecValuePO findOne(Integer id){
		return specValuePOMapper.selectByPrimaryKey(id);
	}

	/**
	 * 批量删除
	 */
	@Override
	public void delete(Integer[] ids) {
		for(Integer id:ids){
			specValuePOMapper.deleteByPrimaryKey(id);
		}
	}


		@Override
	public PageResult findPage(SpecValuePO specValuePO, int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);

		SpecValuePOExample example=new SpecValuePOExample();
		SpecValuePOExample.Criteria criteria = example.createCriteria();

		if(specValuePO!=null){
						if(specValuePO.getValue()!=null && specValuePO.getValue().length()>0){
				criteria.andValueLike("%"+specValuePO.getValue()+"%");
			}

		}

		Page<SpecValuePO> page= (Page<SpecValuePO>)specValuePOMapper.selectByExample(example);
		return new PageResult(page.getTotal(), page.getResult());
	}

}
