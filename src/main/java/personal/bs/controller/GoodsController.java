package personal.bs.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import personal.bs.domain.dto.GoodsDto;
import personal.bs.domain.po.SkuPO;
import personal.bs.domain.po.SpuPO;
import personal.bs.domain.vo.GoodsVO;
import personal.bs.domain.vo.PageResult;
import personal.bs.domain.vo.Result;
import personal.bs.service.GoodsService;
import personal.bs.service.SkuSearchService;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/goods")
public class GoodsController {

    @GetMapping("genSkuHtml")
    @ResponseBody
    public Result genSkuHtml(Integer id) {
        goodsService.genSkuHtml(id);
        return new Result();
    }

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
    public Result add(@RequestBody GoodsDto goodsDto, @SessionAttribute("storeId") Integer storeId) {
        try {
            goodsDto.getGoods().setStoreId(storeId);
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
        //TODO 完成商品删除移除索引库、删除商品详细页
        //从索引库中删除
        skuSearchService.deleteByGoodsIds(Arrays.asList(ids));
//	    //TODO 删除服务器上的商品详细页

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
    public PageResult search(HttpServletRequest request, @RequestBody SpuPO goods,
         int page, int rows,
         @SessionAttribute(value = "storeId", required = false) Integer storeId,
         @SessionAttribute(value = "operateId", required = false) Integer operateId) {
        if (operateId != null) {
            //查出所有的商品
        } else if (request.getRequestURL().toString().contains("store")) {
            if (operateId != null) {
                goods.setStoreId(storeId);
            }
        }

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
    public PageResult searchByTypeId(Model model, Integer typeId,
                                     @RequestParam(name = "page", defaultValue = "1", required = false) int page,
                                     @RequestParam(name = "rows", defaultValue = "20", required = false) int rows) {

        return goodsService.searchByTypeId(typeId, page, rows);
    }

    @Resource
    SkuSearchService skuSearchService;

    @RequestMapping("/updateStatus")
    @ResponseBody
    public Result updateStatus(Integer[] ids, String status) {
        try {
            //完成商品审核导入索引库、生成商品详细页
            goodsService.updateStatus(ids, status);
            if ("2".equals(status)) {
                //如果是审核通过导入到索引库
                List<SkuPO> skuPOS = goodsService.findItemListByGoodsIdListAndStatus(ids, status);
                skuSearchService.importToSolr(skuPOS);
                for (Integer id : ids) {
                    goodsService.genSkuHtml(id);
                }

            }
            return new Result(true, "修改状态成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, "修改状态失败");
        }
    }

    @RequestMapping("/genHtml")
    @ResponseBody
    public void genHtml(Integer goodsId) {
        goodsService.genSkuHtml(goodsId);
    }

}
