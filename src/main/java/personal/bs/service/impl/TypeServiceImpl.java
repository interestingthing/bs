package personal.bs.service.impl;


import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import personal.bs.dao.mapper.TypePOMapper;
import personal.bs.domain.po.TypePO;
import personal.bs.domain.po.TypePOExample;
import personal.bs.domain.vo.PageResult;
import personal.bs.service.TypeService;

import javax.annotation.Resource;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 服务实现层
 *
 * @author Administrator
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class TypeServiceImpl implements TypeService {

    @Resource
    private TypePOMapper typePOMapper;
//    @Resource
//    private RedisTemplate redisTemplate;

    /**
     * 查询全部
     */
    @Override
    public List<TypePO> findAll() {
        return typePOMapper.selectByExample(null);
    }

    /**
     * 按分页查询
     */
    @Override
    public PageResult findPage(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        Page<TypePO> page = (Page<TypePO>) typePOMapper.selectByExample(null);
        return new PageResult(page.getTotal(), page.getResult());
    }

    /**
     * 增加
     */
    @Override
    public void add(TypePO typePO) {
        typePOMapper.insert(typePO);
    }


    /**
     * 修改
     */
    @Override
    public void update(TypePO typePO) {
        typePOMapper.updateByPrimaryKey(typePO);
    }

    /**
     * 根据ID获取实体
     *
     * @param id
     * @return
     */
    @Override
    public TypePO findOne(Integer id) {
        return typePOMapper.selectByPrimaryKey(id);
    }

    /**
     * 批量删除
     */
    @Override
    public void delete(Integer[] ids) {
        for (Integer id : ids) {
            TypePOExample typePOExample = new TypePOExample();
            TypePOExample.Criteria criteria = typePOExample.createCriteria();
            criteria.andPidEqualTo(id);

            typePOMapper.deleteByExample(typePOExample);
            typePOMapper.deleteByPrimaryKey(id);

        }
    }


    @Override
    public PageResult findPage(TypePO typePO, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);

        TypePOExample example = new TypePOExample();
        TypePOExample.Criteria criteria = example.createCriteria();

        if (typePO != null) {
            if (typePO.getName() != null && typePO.getName().length() > 0) {
                criteria.andNameLike("%" + typePO.getName() + "%");
            }
            if (typePO.getPid() != null ) {
                criteria.andPidEqualTo(typePO.getPid());
            }

        }

        Page<TypePO> page = (Page<TypePO>) typePOMapper.selectByExample(example);
        return new PageResult(page.getTotal(), page.getResult());
    }


    @Override
    public List<TypePO> findByParentId(Integer parentId) {
        TypePOExample example = new TypePOExample();
        TypePOExample.Criteria criteria = example.createCriteria();
        // 设置条件:
        criteria.andPidEqualTo(parentId);
        // 条件查询

        //将模板ID放入缓存（以商品分类名称作为key）

//        List<TypePO> typePOList = findAll();
//        for (TypePO typePO : typePOList) {
//            redisTemplate.boundHashOps("typePO").put(typePO.getName(), typePO.getPid());
//        }
//        System.out.println("将模板ID放入缓存");

        return typePOMapper.selectByExample(example);
    }


    @Override
    public Map showTypeList() {
        TypePOExample typePOExample = new TypePOExample();
        TypePOExample.Criteria criteria = typePOExample.createCriteria();
        criteria.andPidEqualTo(0);
        List<TypePO> typePOS = typePOMapper.selectByExample(typePOExample);

        LinkedHashMap<TypePO, List<TypePO>> map = new LinkedHashMap<>();

        typePOS.forEach(typePO -> {
            TypePOExample typePOExample1 = new TypePOExample();
            TypePOExample.Criteria criteria1 = typePOExample1.createCriteria();
            criteria1.andPidEqualTo(typePO.getId());
            List<TypePO> typePOS1 = typePOMapper.selectByExample(typePOExample1);
            map.put(typePO, typePOS1);
        });
        return map;
    }
}
