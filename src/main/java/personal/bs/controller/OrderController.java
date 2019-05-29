package personal.bs.controller;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;
import personal.bs.domain.po.OrderPO;
import personal.bs.domain.vo.PageResult;
import personal.bs.domain.vo.Result;
import personal.bs.service.OrderService;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Resource
    private OrderService orderService;

    /**
     * 返回全部列表
     *
     * @return
     */
    @RequestMapping("/findAll")
    public List<OrderPO> findAll() {
        return orderService.findAll();
    }


    /**
     * 返回全部列表
     *
     * @return
     */
    @RequestMapping("/findPage")
    public PageResult findPage(int page, int rows) {
        return orderService.findPage(page, rows);
    }

    /**
     * 增加
     *
     * @param order
     * @return
     */
    @RequestMapping("/add")
    public Result add(@RequestBody OrderPO order, @SessionAttribute(name = "userId", required = false) Integer id) {

        if(id==null){
            return new Result(false,"请登录！");
        }
        //获取当前登录人账号
        order.setUserId(id);

        try {
            orderService.add(order);
            return new Result(true, "增加成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, "增加失败");
        }
    }

    /**
     * 修改
     *
     * @param order
     * @return
     */
    @RequestMapping("/update")
    public Result update(@RequestBody OrderPO order) {
        try {
            orderService.update(order);
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
    public OrderPO findOne(Integer id) {
        return orderService.findOne(id);
    }

    /**
     * 批量删除
     *
     * @param ids
     * @return
     */
    @RequestMapping("/delete")
    public Result delete(Integer[] ids) {
        try {
            orderService.delete(ids);
            return new Result(true, "删除成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, "删除失败");
        }
    }

    /**
     * 查询+分页
     *
     * @param page
     * @param rows
     * @return
     */
    @RequestMapping("/search")
    public PageResult search(@RequestBody OrderPO order, int page, int rows) {
        return orderService.findPage(order, page, rows);
    }

}
