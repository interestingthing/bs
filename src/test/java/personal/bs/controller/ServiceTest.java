package personal.bs.controller;


import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.data.solr.core.SolrTemplate;
import org.springframework.data.solr.core.query.*;
import org.springframework.data.solr.core.query.result.*;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import personal.bs.TestRunAppication;
import personal.bs.dao.mapper.SkuPOMapper;
import personal.bs.dao.mapper.SpuPOMapper;
import personal.bs.dao.mapper.TypePOMapper;
import personal.bs.domain.po.*;

import javax.annotation.Resource;
import java.util.*;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = TestRunAppication.class, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@Slf4j
@Transactional
public class ServiceTest {
    @Ignore
    @Test
    public void sTest() {
        // AbstractTransactionalJUnit4SpringContextTests
        log.warn("project order-center-service Test Service Start =====================================================================");
    }


    @Resource
    TypePOMapper typePOMapper;

    @Resource
    SpuPOMapper spuPOMapper;

    /**
     * 测试事务回滚
     * 在基类ServiceTest上面加注解@Transactional,
     * 该注解可以被继承，所以所有的方法都会自动回滚
     */
    @Test
    public void transactionTest() {
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

        System.out.println(JSON.toJSON(map));

    }

    @Resource
    SolrTemplate solrTemplate;

    @Test
    public void testsolr() {
        SkuPO skuPO = new SkuPO();
        solrTemplate.saveBean("", skuPO);
        solrTemplate.commit("");

    }

    @Test
    public void testreturnid() {

        SpuPO spuPO = new SpuPO();
        spuPO.setType1Id(2);
        int insert = spuPOMapper.insert(spuPO);
        System.out.println(insert);
        System.out.println(spuPO.getId());
        System.out.println("================");
        SpuPO spuPO1 = new SpuPO();
        spuPO1.setType1Id(2);
        int insert1 = spuPOMapper.insert(spuPO1);
        System.out.println(insert1);
        System.out.println(spuPO1.getId());

    }

    @Resource
    SkuPOMapper skuPOMapper;
//
//    @Resource
//    SolrTemplate solrTemplate;

    /**
     * 导入商品数据
     */
    @Test
    public void deleteSolr() {
        solrTemplate.delete("wgxcb", new SimpleQuery("*:*"));
        solrTemplate.commit("wgxcb");
    }

    @Test
    public void importItemData() {

        SkuPOExample example = new SkuPOExample();
        SkuPOExample.Criteria criteria = example.createCriteria();
//        criteria.andStatusEqualTo("1");//已审核
        List<SkuPO> itemList = skuPOMapper.selectByExample(example);
        ArrayList<SolrSkuPO> solrSkuPOS = new ArrayList<>();
        System.out.println("===商品列表===");
        for (SkuPO item : itemList) {
            Map<String, String> specMap = JSON.parseObject(item.getSpec(), Map.class);
            //将spec字段中的json字符串转换为map
            //给带注解的字段赋值
            System.out.println(item.getTitle());
            SolrSkuPO solrSkuPO = SolrSkuPO.builder()
                    .id(item.getId()).spuId(item.getSpuId())
                    .imgUrl(item.getImgUrl()).price(item.getPrice().toString())
                    .type(item.getType()).specMap(specMap)
                    .title(item.getTitle()).store(item.getStore())
                    .saleNum(item.getSaleNum()).uploaddate(item.getUploaddate())
                    .comment(item.getComment())
                    .build();
            solrSkuPOS.add(solrSkuPO);
        }

        solrTemplate.saveBeans("wgxcb", solrSkuPOS);
        solrTemplate.commit("wgxcb");
        System.out.println("===结束===");
    }

    //查询列表
    private Map searchList(Map<String, Object> searchMap) {
        Map map = new HashMap();
        //高亮选项初始化
        HighlightQuery query = new SimpleHighlightQuery();
        HighlightOptions highlightOptions = new HighlightOptions().addField("sku_title");//高亮域
        highlightOptions.setSimplePrefix("<em style='color:red'>");//前缀
        highlightOptions.setSimplePostfix("</em>");
        query.setHighlightOptions(highlightOptions);//为查询对象设置高亮选项

        //1.1 关键字查询
        if (searchMap.get("keywords") != null && StringUtils.isNotBlank(searchMap.get("keywords").toString())) {
            Criteria criteria = new Criteria("sku_keywords").contains(searchMap.get("keywords").toString());
            query.addCriteria(criteria);
        } else {
            Criteria criteria = new Criteria("sku_keywords").contains("");
            query.addCriteria(criteria);
        }
        //1.2 按商品分类过滤
        if (searchMap.get("category") != null && StringUtils.isNotBlank(searchMap.get("category").toString())) {//如果用户选择了分类
            FilterQuery filterQuery = new SimpleFilterQuery();
            Criteria filterCriteria = new Criteria("sku_category").contains(searchMap.get("category").toString());
            filterQuery.addCriteria(filterCriteria);
            query.addFilterQuery(filterQuery);
        }
        //1.4 按规格过滤
        if (searchMap.get("spec") != null && StringUtils.isNotBlank(searchMap.get("spec").toString())) {
            Map<String, String> specMap = (Map<String, String>) searchMap.get("spec");
            for (String key : specMap.keySet()) {

                FilterQuery filterQuery = new SimpleFilterQuery();
                Criteria filterCriteria = new Criteria("sku_spec_" + key).contains(specMap.get(key));
                filterQuery.addCriteria(filterCriteria);
                query.addFilterQuery(filterQuery);

            }

        }

        //1.5按价格过滤
        if (searchMap.get("price") != null && StringUtils.isNotBlank(searchMap.get("price").toString())) {
            String[] price = ((String) searchMap.get("price")).split("-");
            if (!price[0].equals("0")) { //如果最低价格不等于0
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


        //1.6 分页
        Integer pageNo = (Integer) searchMap.get("pageNo");//获取页码
        if (pageNo == null) {
            pageNo = 1;
        }
        Integer pageSize = (Integer) searchMap.get("pageSize");
        //获取页大小
        if (pageSize == null) {
            pageSize = 20;
        }

        query.setOffset(Long.valueOf((pageNo - 1) * pageSize));
        //起始索引
        query.setRows(pageSize);
        //每页记录数


        //1.7 排序

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


        //***********  获取高亮结果集  ***********
        //高亮页对象
        HighlightPage<SkuPO> page = solrTemplate.queryForHighlightPage("wgxcb", query, SkuPO.class);
        //高亮入口集合(每条记录的高亮入口)
        List<HighlightEntry<SkuPO>> entryList = page.getHighlighted();
        for (HighlightEntry<SkuPO> entry : entryList) {
            //获取高亮列表(高亮域的个数)
            List<HighlightEntry.Highlight> highlightList = entry.getHighlights();
			/*
			for(Highlight h:highlightList){
				List<String> sns = h.getSnipplets();//每个域有可能存储多值
				System.out.println(sns);
			}*/
            if (highlightList.size() > 0 && highlightList.get(0).getSnipplets().size() > 0) {
                SkuPO item = entry.getEntity();
                item.setTitle(highlightList.get(0).getSnipplets().get(0));
            }
        }
        map.put("rows", page.getContent());
        map.put("totalPages", page.getTotalPages());//总页数
        map.put("total", page.getTotalElements());//总记录数
        return map;

    }


    @Test
    public void testSolr() {
        HashMap<String, Object> map = new HashMap<>();
//        map.put("sku_keywords", "床品");
//        Map map1 = searchList(map);
        List<String> strings = searchCategoryList(map);
        strings.forEach(s -> {
            System.out.println(s);
        });

    }


    private List<String> searchCategoryList(Map searchMap) {
        List<String> list = new ArrayList();

        Query query = new SimpleQuery("*:*");
        //根据关键字查询
        if (searchMap.get("keywords") != null && StringUtils.isNotBlank(searchMap.get("keywords").toString())) {
            Criteria criteria = new Criteria("sku_keywords").contains(searchMap.get("keywords").toString());
            query.addCriteria(criteria);
        }
        //设置分组选项
        GroupOptions groupOptions = new GroupOptions().addGroupByField("sku_category");  //group by ...
        groupOptions.setOffset(0);
        groupOptions.setLimit(20);
        query.setGroupOptions(groupOptions);
        query.setOffset(0L);
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
        return list;

    }
}
