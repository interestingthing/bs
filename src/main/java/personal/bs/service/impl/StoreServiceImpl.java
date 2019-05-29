package personal.bs.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.stereotype.Service;
import personal.bs.dao.mapper.StorePOMapper;
import personal.bs.domain.po.StorePO;
import personal.bs.domain.po.StorePOExample;
import personal.bs.domain.vo.PageResult;
import personal.bs.service.StoreService;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * 服务实现层
 */
@Service
public class StoreServiceImpl implements StoreService {

    @Resource
    private StorePOMapper storePOMapper;

    /**
     * 查询全部
     */
    @Override
    public List<StorePO> findAll() {
        return storePOMapper.selectByExample(null);
    }

    /**
     * 按分页查询
     */
    @Override
    public PageResult findPage(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        Page<StorePO> page = (Page<StorePO>) storePOMapper.selectByExample(null);
        return new PageResult(page.getTotal(), page.getResult());
    }

    /**
     * 增加
     */
    @Override
    public void add(StorePO storePO) {
        // 设置商家状态
        storePO.setStatus("0");
        storePO.setCreateTime(new Date());
        storePOMapper.insert(storePO);
    }


    /**
     * 修改
     */
    @Override
    public void update(StorePO storePO) {
        storePOMapper.updateByPrimaryKey(storePO);
    }

    /**
     * 根据ID获取实体
     *
     * @param id
     * @return
     */
    @Override
    public StorePO findOne(Integer id) {
        return storePOMapper.selectByPrimaryKey(id);
    }

    /**
     * 批量删除
     */
    @Override
    public void delete(Integer[] ids) {
        for (Integer id : ids) {
            storePOMapper.deleteByPrimaryKey(id);
        }
    }


    @Override
    public PageResult findPage(StorePO storePO, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);

        StorePOExample example = new StorePOExample();
        StorePOExample.Criteria criteria = example.createCriteria();

        if (storePO != null) {
            if (storePO.getId() != null) {
                criteria.andIdEqualTo(storePO.getId());
            }
            if (storePO.getName() != null && storePO.getName().length() > 0) {
                criteria.andNameLike("%" + storePO.getName() + "%");
            }
            if (storePO.getNickname() != null && storePO.getNickname().length() > 0) {
                criteria.andNicknameLike("%" + storePO.getNickname() + "%");
            }
            if (storePO.getStatus() != null && storePO.getStatus().length() > 0) {
                criteria.andStatusLike("%" + storePO.getStatus() + "%");
            }

        }

        Page<StorePO> page = (Page<StorePO>) storePOMapper.selectByExample(example);
        return new PageResult(page.getTotal(), page.getResult());
    }


    @Override
    public void updateStatus(Integer storeId, String status) {
        StorePO storePO = storePOMapper.selectByPrimaryKey(storeId);
        storePO.setStatus(status);
        storePOMapper.updateByPrimaryKey(storePO);
    }

}
