package personal.bs.service.impl;


import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import personal.bs.dao.mapper.SpecPOMapper;
import personal.bs.dao.mapper.SpecValuePOMapper;
import personal.bs.domain.po.SpecPO;
import personal.bs.domain.po.SpecPOExample;
import personal.bs.domain.po.SpecValuePO;
import personal.bs.domain.po.SpecValuePOExample;
import personal.bs.domain.vo.PageResult;
import personal.bs.domain.vo.Specification;
import personal.bs.service.SpecService;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * 服务实现层
 *
 * @author Administrator
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class SpecServiceImpl implements SpecService {

    @Resource
    private SpecPOMapper specPOMapper;

    @Resource
    private SpecValuePOMapper specValuePOMapper;

    /**
     * 查询全部
     */
    @Override
    public List<SpecPO> findAll() {
        return specPOMapper.selectByExample(null);
    }

    /**
     * 按分页查询
     */
    @Override
    public PageResult findPage(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        Page<SpecPO> page = (Page<SpecPO>) specPOMapper.selectByExample(null);
        return new PageResult(page.getTotal(), page.getResult());
    }


    /**
     * 增加
     */
    @Override
    public void add(Specification specification) {
        // 保存规格
        specPOMapper.insert(specification.getSpecPO());
        System.out.println();
        // 保存规格选项
        for (SpecValuePO specValuePO : specification.getSpecValuePOList()) {
            // 设置规格的ID:
            specValuePO.setSpecId(specification.getSpecPO().getId());

            specValuePOMapper.insert(specValuePO);
        }
    }


    /**
     * 修改
     */
@Override
public void update(Specification specification) {
    // 修改规格
    specPOMapper.updateByPrimaryKey(specification.getSpecPO());
    // 先删除规格选项，再添加规格选项
    SpecValuePOExample example = new SpecValuePOExample();
    SpecValuePOExample.Criteria criteria = example.createCriteria();
    criteria.andSpecIdEqualTo(specification.getSpecPO().getId());
    this.specValuePOMapper.deleteByExample(example);
    // 保存规格选项
    for (SpecValuePO specValuePO : specification.getSpecValuePOList()) {
        // 设置规格的ID:
        specValuePO.setSpecId(specification.getSpecPO().getId());
        this.specValuePOMapper.insert(specValuePO);
    }
}

    /**
     * 根据ID获取实体
     *
     * @param id
     * @return
     */
    @Override
    public Specification findOne(Integer id) {
        Specification specification = new Specification();
        // 根据规格ID查询规格对象
        SpecPO specPO = specPOMapper.selectByPrimaryKey(id);
        specification.setSpecPO(specPO);

        // 根据规格的ID查询规格选项
        SpecValuePOExample example = new SpecValuePOExample();
        SpecValuePOExample.Criteria criteria = example.createCriteria();
        criteria.andSpecIdEqualTo(id);
        List<SpecValuePO> list = specValuePOMapper.selectByExample(example);
        specification.setSpecValuePOList(list);

        return specification;
    }

    /**
     * 批量删除
     */
    @Override
    public void delete(Integer[] ids) {
        for (Integer id : ids) {
            // 删除规格
            specPOMapper.deleteByPrimaryKey(id);

            // 删除规格选项:
            SpecValuePOExample example = new SpecValuePOExample();
            SpecValuePOExample.Criteria criteria = example.createCriteria();
            criteria.andSpecIdEqualTo(id);
            specValuePOMapper.deleteByExample(example);
        }
    }


@Override
public PageResult findPage(SpecPO spec, int pageNum, int pageSize) {
    PageHelper.startPage(pageNum, pageSize);
    SpecPOExample example = new SpecPOExample();
    SpecPOExample.Criteria criteria = example.createCriteria();
    if (spec != null) {
        if (spec.getName() != null && spec.getName().length() > 0) {
            criteria.andNameLike("%" + spec.getName() + "%");
        }

    }
    Page<SpecPO> page = (Page<SpecPO>) specPOMapper.selectByExample(example);
    return new PageResult(page.getTotal(), page.getResult());
}

    @Override
    public List<Map> selectOptionList() {
        return specPOMapper.selectOptionList();
    }

}
