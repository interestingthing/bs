package personal.bs.controller;


import com.alibaba.fastjson.JSON;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import personal.bs.domain.po.SkuPO;
import personal.bs.domain.po.SpuPO;
import personal.bs.domain.dto.GoodsDto;
import personal.bs.domain.vo.GoodsVO;
import personal.bs.domain.vo.PageResult;
import personal.bs.domain.vo.Result;
import personal.bs.service.GoodsService;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping("/goods")
public class GoodsController {

    @Resource
    private GoodsService goodsService;

    /**
     * 返回全部列表
     *
     * @return
     */
    @RequestMapping("/findAll")
    @ResponseBody
    public List<SpuPO> findAll() {
        return goodsService.findAll();
    }


    /**
     * 返回全部列表
     *
     * @return
     */
    @RequestMapping("/findPage")
    @ResponseBody
    public PageResult findPage(int page, int rows) {
        return goodsService.findPage(page, rows);
    }

    /**
     * 增加
     *
     * @param goodsDto
     * @return
     */
    @PostMapping("/add")
    @ResponseBody
    public Result add(@RequestBody GoodsDto goodsDto) {
        try {
            //todo 获取商家ID
//            String sellerId = SecurityContextHolder.getContext().getAuthentication().getName();
            goodsDto.getGoods().setStoreId(0);
            goodsService.add(goodsDto);
            return new Result(true, "增加成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, "增加失败");
        }
    }

    /**
     * 修改
     *
     * @param goodsDto
     * @return
     */
    @PostMapping("/update")
    @ResponseBody
    public Result update(@RequestBody GoodsDto goodsDto) {
        try {
            goodsService.update(goodsDto);
            return new Result(true, "修改成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, "修改失败");
        }
    }

    /**
     * 获取实体
     *
     * @param id
     * @return
     */
    @RequestMapping("/findOne")
    @ResponseBody
    public GoodsVO findOne(Integer id) {
        return goodsService.findOne(id);
    }

//    @Resource
//    private Destination queueSolrDeleteDestination;
//
//    @Resource
//    private Destination topicPageDeleteDestination;

    /**
     * 批量删除
     *
     * @param ids
     * @return
     */
    @RequestMapping("/delete")
    @ResponseBody
    public Result delete(final Integer[] ids) {
//		try {
        goodsService.delete(ids);

        //从索引库中删除
        //itemSearchService.deleteByGoodsIds(Arrays.asList(ids));
//			jmsTemplate.send(queueSolrDeleteDestination, new MessageCreator() {
//
//				@Override
//				public Message createMessage(Session session) throws JMSException {
//					return session.createObjectMessage(ids);
//				}
//			});
//
//			//删除每个服务器上的商品详细页
//			jmsTemplate.send(topicPageDeleteDestination, new MessageCreator() {
//
//				@Override
//				public Message createMessage(Session session) throws JMSException {
//					return session.createObjectMessage(ids);
//				}
//			});

        return new Result(true, "删除成功");
//		} catch (Exception e) {
//			e.printStackTrace();
//			return new Result(false, "删除失败");
//		}
    }

    /**
     * 查询+分页
     *
     * @param page
     * @param rows
     * @return
     */
    @PostMapping("/search")
    @ResponseBody
    public PageResult search(@RequestBody SpuPO goods, int page, int rows) {
        //TODO 获取商家ID
        //String sellerId = SecurityContextHolder.getContext().getAuthentication().getName();
        //添加查询条件
        goods.setStoreId(0);

        return goodsService.findPage(goods, page, rows);
    }

    /**
     * 查询+分页
     *
     * @param page
     * @param rows
     * @return
     */
    @GetMapping("/search")
    public PageResult searchByTypeId(Model model,Integer typeId,
                                     @RequestParam(name = "page", defaultValue = "1", required = false) int page,
                                     @RequestParam(name = "rows", defaultValue = "20", required = false) int rows) {

        return goodsService.searchByTypeId(typeId, page, rows);
    }
    //@Resource(timeout=100000)
    //private ItemSearchService itemSearchService;
//
//    @Resource
//    private Destination queueSolrDestination;//用于导入solr索引库的消息目标（点对点）
//
//    @Resource
//    private Destination topicPageDestination;//用于生成商品详细页的消息目标(发布订阅)

    @RequestMapping("/updateStatus")
    @ResponseBody
    public Result updateStatus(Integer[] ids, String status) {
        try {
            goodsService.updateStatus(ids, status);

            if ("1".equals(status)) {//如果是审核通过
                //*****导入到索引库
                //得到需要导入的SKU列表
                List<SkuPO> itemList = goodsService.findItemListByGoodsIdListAndStatus(ids, status);
                //导入到solr
                //itemSearchService.importList(itemList);
                final String jsonString = JSON.toJSONString(itemList);//转换为json传输

//                jmsTemplate.send(queueSolrDestination, new MessageCreator() {
//
//                    @Override
//                    public Message createMessage(Session session) throws JMSException {
//
//                        return session.createTextMessage(jsonString);
//                    }
//                });

                //****生成商品详细页
//                for (final Integer goodsId : ids) {
//                    //	itemPageService.genItemHtml(goodsId);
//                    jmsTemplate.send(topicPageDestination, new MessageCreator() {
//
//                        @Override
//                        public Message createMessage(Session session) throws JMSException {
//                            return session.createTextMessage(goodsId + "");
//                        }
//                    });
//                }

            }

            return new Result(true, "修改状态成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, "修改状态失败");
        }
    }

    //@Resource(timeout=40000)
    //private ItemPageService itemPageService;

    @RequestMapping("/genHtml")
    @ResponseBody
    public void genHtml(Integer goodsId) {

        //itemPageService.genItemHtml(goodsId);

    }

}
