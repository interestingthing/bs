package personal.bs.controller;


import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.solr.core.SolrTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import personal.bs.TestRunAppication;
import personal.bs.dao.mapper.TypePOMapper;
import personal.bs.domain.po.SkuPO;
import personal.bs.domain.po.TypePO;
import personal.bs.domain.po.TypePOExample;

import javax.annotation.Resource;
import java.util.LinkedHashMap;
import java.util.List;


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
    public void testsolr(){
        SkuPO skuPO = new SkuPO();
        solrTemplate.saveBean("",skuPO);
        solrTemplate.commit("");

    }

}
