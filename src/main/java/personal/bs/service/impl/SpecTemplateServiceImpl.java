package personal.bs.service.impl;


import com.alibaba.fastjson.JSON;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import personal.bs.dao.mapper.SpecTemplatePOMapper;
import personal.bs.dao.mapper.SpecValuePOMapper;
import personal.bs.domain.po.SpecTemplatePO;
import personal.bs.domain.po.SpecTemplatePOExample;
import personal.bs.domain.po.SpecValuePO;
import personal.bs.domain.po.SpecValuePOExample;
import personal.bs.domain.vo.PageResult;
import personal.bs.service.SpecTemplateService;

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
public class SpecTemplateServiceImpl implements SpecTemplateService {

    @Autowired
    private SpecTemplatePOMapper typeTemplateMapper;

    /**
     * 查询全部
     */
    @Override
    public List<SpecTemplatePO> findAll() {
        return typeTemplateMapper.selectByExample(null);
    }

    /**
     * 按分页查询
     */
    @Override
    public PageResult findPage(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        Page<SpecTemplatePO> page = (Page<SpecTemplatePO>) typeTemplateMapper.selectByExample(null);
        return new PageResult(page.getTotal(), page.getResult());
    }

    /**
     * 增加
     */
    @Override
    public void add(SpecTemplatePO typeTemplate) {
        typeTemplateMapper.insert(typeTemplate);
    }


    /**
     * 修改
     */
    @Override
    public void update(SpecTemplatePO typeTemplate) {
        typeTemplateMapper.updateByPrimaryKey(typeTemplate);
    }

    /**
     * 根据ID获取实体
     *
     * @param id
     * @return
     */
    @Override
    public SpecTemplatePO findOne(Integer id) {
        return typeTemplateMapper.selectByPrimaryKey(id);
    }

    /**
     * 批量删除
     */
    @Override
    public void delete(Integer[] ids) {
        for (Integer id : ids) {
            typeTemplateMapper.deleteByPrimaryKey(id);
        }
    }


    @Override
    public PageResult findPage(SpecTemplatePO typeTemplate, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);

        SpecTemplatePOExample example = new SpecTemplatePOExample();
        SpecTemplatePOExample.Criteria criteria = example.createCriteria();

        if (typeTemplate != null) {
            if (typeTemplate.getName() != null && typeTemplate.getName().length() > 0) {
                criteria.andNameLike("%" + typeTemplate.getName() + "%");
            }
            if (typeTemplate.getSpecIds() != null && typeTemplate.getSpecIds().length() > 0) {
                criteria.andSpecIdsLike("%" + typeTemplate.getSpecIds() + "%");
            }

            if (typeTemplate.getExtendAttributes() != null && typeTemplate.getExtendAttributes().length() > 0) {
                criteria.andExtendAttributesLike("%" + typeTemplate.getExtendAttributes() + "%");
            }

        }

        Page<SpecTemplatePO> page = (Page<SpecTemplatePO>) typeTemplateMapper.selectByExample(example);

        //缓存处理
        saveToRedis();

        return new PageResult(page.getTotal(), page.getResult());
    }

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * TODO 将品牌列表与规格列表放入缓存
     */
    private void saveToRedis() {
        List<SpecTemplatePO> templateList = findAll();
        for (SpecTemplatePO template : templateList) {
            //得到规格列表
            List<Map> specList = findSpecList(template.getId());
            redisTemplate.boundHashOps("specList").put(template.getId(), specList);

        }
        System.out.println("缓存品牌列表");

    }


    @Resource
    private SpecValuePOMapper specificationOptionMapper;

    @Override
    public List<Map> findSpecList(Integer id) {
        //根据ID查询到模板对象
        SpecTemplatePO typeTemplate = typeTemplateMapper.selectByPrimaryKey(id);
        // 获得规格的数据spec_ids
        String specIds = typeTemplate.getSpecIds();// [{"id":27,"text":"网络"},{"id":32,"text":"机身内存"}]
        // 将specIds的字符串转成JSON的List<Map>
        List<Map> list = JSON.parseArray(specIds, Map.class);
        // 获得每条记录:
        for (Map map : list) {
            // 根据规格的ID 查询规格选项的数据:
            // 设置查询条件:
            SpecValuePOExample example = new SpecValuePOExample();
            SpecValuePOExample.Criteria criteria = example.createCriteria();
            criteria.andSpecIdEqualTo((Integer) map.get("id"));

            List<SpecValuePO> specOptionList = specificationOptionMapper.selectByExample(example);

            map.put("options", specOptionList);
            //{"id":27,"text":"网络",options:[{颜色：xxx,网络:移动2G}]}
        }
        return list;
    }

}
