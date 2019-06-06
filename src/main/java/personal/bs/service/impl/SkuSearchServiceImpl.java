package personal.bs.service.impl;


import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.data.solr.core.SolrTemplate;
import org.springframework.data.solr.core.query.*;
import org.springframework.data.solr.core.query.result.*;
import org.springframework.stereotype.Service;
import personal.bs.dao.mapper.SpecTemplatePOMapper;
import personal.bs.dao.mapper.TypePOMapper;
import personal.bs.domain.po.SkuPO;
import personal.bs.domain.po.TypePO;
import personal.bs.domain.po.TypePOExample;
import personal.bs.service.SkuSearchService;
import personal.bs.service.SpecTemplateService;

import javax.annotation.Resource;
import java.util.*;

@Service
@Slf4j
public class SkuSearchServiceImpl implements SkuSearchService {

    @Resource
    private SolrTemplate solrTemplate;

    @Resource
    SpecTemplateService specTemplateService;

    @Resource
    TypePOMapper typePOMapper;

    @Override
    public Map search(Map searchMap) {
        Map map = new HashMap();
        //空格处理
        String keywords = (String) searchMap.get("keywords");
        if (StringUtils.isNotBlank(keywords)) {
            //关键字去掉空格
            searchMap.put("keywords", keywords.replace(" ", ""));
        }
        //1.查询列表
        map.putAll(searchList(searchMap));
        //2.分组查询 商品分类列表
        List<String> categoryList = searchCategoryList(searchMap);
        map.put("categoryList", categoryList);

        //3.查询规格列表
        String category = (String) searchMap.get("category");
        if (!category.equals("")) {
            map.putAll(searchSpecList(category));
        } else {
            if (categoryList.size() > 0) {
                map.putAll(searchSpecList(categoryList.get(0)));
            }
        }
        return map;
    }


    //查询列表
    private Map searchList(Map searchMap) {
        Map map = new HashMap();
        //1.0 高亮选项初始化
        HighlightQuery query = new SimpleHighlightQuery();
        //高亮域
        HighlightOptions highlightOptions = new HighlightOptions().addField("sku_title");
        //前缀
        highlightOptions.setSimplePrefix("<em style='color:red'>");
        highlightOptions.setSimplePostfix("</em>");
        //为查询对象设置高亮选项
        query.setHighlightOptions(highlightOptions);

        //1.1 关键字查询
        if (searchMap.get("keywords") != null && StringUtils.isNotBlank(searchMap.get("keywords").toString())) {
            Criteria criteria = new Criteria("sku_keywords").contains(searchMap.get("keywords").toString().replace(" ", ""));
            query.addCriteria(criteria);

        } else {
            Criteria criteria = new Criteria("sku_keywords").isNotNull();
            query.addCriteria(criteria);
        }
        //1.2 按商品分类过滤
        if (searchMap.get("category") != null && StringUtils.isNotBlank(searchMap.get("category").toString())) {
            //如果用户选择了分类
            FilterQuery filterQuery = new SimpleFilterQuery();
            //如果是一级分类就按照子类别筛选
            String category = (String) searchMap.get("category");
            TypePOExample typePOExample = new TypePOExample();
            TypePOExample.Criteria criteria = typePOExample.createCriteria();
            criteria.andNameEqualTo(category).andPidEqualTo(0);
            List<TypePO> typePOS = typePOMapper.selectByExample(typePOExample);
            if (!typePOS.isEmpty()) {
                typePOExample.clear();
                TypePOExample.Criteria criteria1 = typePOExample.createCriteria();
                criteria1.andPidEqualTo(typePOS.get(0).getId());
                List<TypePO> typePOS1 = typePOMapper.selectByExample(typePOExample);
                Criteria filterCriteria = new Criteria("sku_category").in(typePOS1);
                filterQuery.addCriteria(filterCriteria);
            } else {
                Criteria filterCriteria = new Criteria("sku_category").in(searchMap.get("category").toString());
                filterQuery.addCriteria(filterCriteria);
            }
            query.addFilterQuery(filterQuery);
        }
        //1.3 按规格过滤
        if (searchMap.get("spec") != null && StringUtils.isNotBlank(searchMap.get("spec").toString())) {
            Map<String, String> specMap = (Map<String, String>) searchMap.get("spec");
            for (String key : specMap.keySet()) {

                FilterQuery filterQuery = new SimpleFilterQuery();
                Criteria filterCriteria = new Criteria("sku_spec_" + String.join("", Collections.nCopies(key.length(), "_"))).contains(specMap.get(key));
                filterQuery.addCriteria(filterCriteria);
                query.addFilterQuery(filterQuery);

            }

        }

        //1.4按价格过滤
        if (searchMap.get("price") != null && StringUtils.isNotBlank(searchMap.get("price").toString())) {
            String[] price = ((String) searchMap.get("price")).split("-");
            if (!price[0].equals("0")) {
                //如果最低价格不等于0
                FilterQuery filterQuery = new SimpleFilterQuery();
                Criteria filterCriteria = new Criteria("sku_price").greaterThanEqual(price[0]);
                filterQuery.addCriteria(filterCriteria);
                query.addFilterQuery(filterQuery);
            }
            if (!price[1].equals("*")) { //如果最高价格不等于*
                FilterQuery filterQuery = new SimpleFilterQuery();
                Criteria filterCriteria = new Criteria("sku_price").lessThanEqual(price[1]);
                filterQuery.addCriteria(filterCriteria);
                query.addFilterQuery(filterQuery);
            }
        }


        //1.5 分页
        Integer pageNo = (Integer) searchMap.get("pageNo");//获取页码
        if (pageNo == null) {
            pageNo = 1;
        }
        Integer pageSize = (Integer) searchMap.get("pageSize");
        //获取页大小
        if (pageSize == null) {
            pageSize = 20;
        }
        //起始索引
        query.setOffset(Long.valueOf((pageNo - 1) * pageSize));
        //每页记录数
        query.setRows(pageSize);
        //1.6 排序
        String sortValue = (String) searchMap.get("sort");
        //升序ASC 降序DESC
        String sortField = (String) searchMap.get("sortField");
        //排序字段
        if (StringUtils.isNotBlank(sortValue)) {
            if (sortValue.equals("ASC")) {
                Sort sort = new Sort(Sort.Direction.ASC, "sku_" + sortField);
                query.addSort(sort);
            }
            if (sortValue.equals("DESC")) {
                Sort sort = new Sort(Sort.Direction.DESC, "sku_" + sortField);
                query.addSort(sort);
            }
        }
        // 获取高亮结果集
        HighlightPage<SkuPO> page = solrTemplate.queryForHighlightPage("wgxcb", query, SkuPO.class);
        List<HighlightEntry<SkuPO>> entryList = page.getHighlighted();
        for (HighlightEntry<SkuPO> entry : entryList) {
            //获取高亮列表(高亮域的个数)
            List<HighlightEntry.Highlight> highlightList = entry.getHighlights();
            if (highlightList.size() > 0 && highlightList.get(0).getSnipplets().size() > 0) {
                SkuPO item = entry.getEntity();
                item.setTitle(highlightList.get(0).getSnipplets().get(0));
            }
        }
        map.put("rows", page.getContent());
        //总页数
        map.put("totalPages", page.getTotalPages());
        //总记录数
        map.put("total", page.getTotalElements());

        return map;

    }

    /**
     * 分组查询（查询商品分类列表）
     *
     * @return
     */
    private List<String> searchCategoryList(Map searchMap) {
        List<String> list = new ArrayList();

        Query query = new SimpleQuery("*:*");
        //根据关键字查询
        if (searchMap.get("keywords") != null && StringUtils.isNotBlank(searchMap.get("keywords").toString())) {
            Criteria criteria = new Criteria("sku_keywords").contains(searchMap.get("keywords").toString());
            query.addCriteria(criteria);
        }

        //设置分组选项
        GroupOptions groupOptions = new GroupOptions().addGroupByField("sku_category");
        //group by ...
        groupOptions.setOffset(0);
        groupOptions.setLimit(2000);
        query.setGroupOptions(groupOptions);
        query.setOffset(0L);
        //起始索引
        query.setRows(20);
        //获取分组页
        GroupPage<SkuPO> page = solrTemplate.queryForGroupPage("wgxcb", query, SkuPO.class);
        //获取分组结果对象
        GroupResult<SkuPO> groupResult = page.getGroupResult("sku_category");
        //获取分组入口页
        Page<GroupEntry<SkuPO>> groupEntries = groupResult.getGroupEntries();
        //获取分组入口集合
        List<GroupEntry<SkuPO>> entryList = groupEntries.getContent();

        for (GroupEntry<SkuPO> entry : entryList) {
            list.add(entry.getGroupValue());    //将分组的结果添加到返回值中
        }
        //如果是一级分类，展示该该一级分类的二级分类与solr中的二级分类求交集
        String category = (String) searchMap.get("category");
        TypePOExample typePOExample = new TypePOExample();
        TypePOExample.Criteria criteria = typePOExample.createCriteria();
        criteria.andNameEqualTo(category).andPidEqualTo(0);
        List<TypePO> typePOS = typePOMapper.selectByExample(typePOExample);
        if (!typePOS.isEmpty()) {
            typePOExample.clear();
            TypePOExample.Criteria criteria1 = typePOExample.createCriteria();
            criteria1.andPidEqualTo(typePOS.get(0).getId());
            List<TypePO> typePOS1 = typePOMapper.selectByExample(typePOExample);
            list.retainAll(typePOS1);
        }
        log.info("查询类别列表：{}", list);
        return list;

    }

    @Resource
    SpecTemplatePOMapper specTemplatePOMapper;

    /**
     * @param category 商品分类名称
     * @return
     */
    private Map searchSpecList(String category) {
        Map map = new HashMap();
        //1.根据商品分类名称得到模板ID
        TypePOExample typePOExample = new TypePOExample();
        TypePOExample.Criteria criteria = typePOExample.createCriteria();
        criteria.andNameEqualTo(category);

        List<TypePO> typePOS = typePOMapper.selectByExample(typePOExample);
        if (typePOS.size() > 0) {
            List<Map> specList = specTemplateService.findSpecList(typePOS.get(0).getTemplateId());
            log.warn("specValueIds{}", JSON.toJSON(specList));
            map.put("specList", JSON.toJSON(specList));
        }
        return map;
    }


    @Override
    public void importToSolr(List<SkuPO> list) {
        ArrayList<SkuPO> solrSkuPOS = new ArrayList<>();
        System.out.println("===商品列表===");
        for (SkuPO item : list) {
            //将spec字段中的json字符串转换为map
            Map<String, String> specMap = JSON.parseObject(item.getSpec(), Map.class);
            item.setSpecMap(specMap);
            solrSkuPOS.add(item);
        }
        solrTemplate.saveBeans("wgxcb", list);
        solrTemplate.commit("wgxcb");
    }


    @Override
    public void deleteByGoodsIds(List spuIds) {

        Query query = new SimpleQuery("*:*");
        Criteria criteria = new Criteria("spu_id").in(spuIds);
        query.addCriteria(criteria);
        solrTemplate.delete("wgxcb", query);
        solrTemplate.commit("wgxcb");
    }


}
