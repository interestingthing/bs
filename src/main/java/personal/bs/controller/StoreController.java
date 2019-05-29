package personal.bs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import personal.bs.dao.mapper.StorePOMapper;
import personal.bs.domain.po.StorePO;
import personal.bs.domain.po.StorePOExample;
import personal.bs.domain.vo.PageResult;
import personal.bs.domain.vo.Result;
import personal.bs.service.StoreService;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * controller 店铺管理
 */
@Controller
@RequestMapping("/store")
public class StoreController {

    @Resource
    private StoreService storeService;

    @Resource
    StorePOMapper storePOMapper;

    @Autowired(required = false)
    private HttpServletRequest request;

    @Autowired(required = false)
    private HttpServletResponse response;

    @GetMapping("/showName")
    @ResponseBody
    public Map showName(@SessionAttribute(value = "storeName", required = false) String username, @SessionAttribute(value = "storeId", required = false) Integer id) {
        Map map = new HashMap();
        // 获得用户名信息:
        map.put("storeName", username);
        map.put("storeId", id);
        return map;
    }

@RequestMapping("/login")
@ResponseBody
public Result login(@RequestBody StorePO store) {
    StorePOExample storePOExample = new StorePOExample();
    StorePOExample.Criteria criteria = storePOExample.createCriteria();
    criteria.andLoginnameEqualTo(store.getLoginname()).andPasswordEqualTo(store.getPassword());
    List<StorePO> storePOS = storePOMapper.selectByExample(storePOExample);

    if (!storePOS.isEmpty()) {
        StorePO storePO = storePOS.get(0);
        if (storePO.getStatus().equals("0")){
            return new Result(false, "等待管理员审核，请稍后再试！");
        }
        HttpSession session = request.getSession();
        session.setAttribute("storeName", storePO.getNickname());
        session.setAttribute("storeId", storePO.getId());
        return new Result(true, "登录成功");
    }
    return new Result(false, "账户名与密码不匹配，请重新输入！");
}

    @GetMapping("logout")
    public String logout(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.invalidate();
        return "redirect:/store/index.html";
    }
    /**
     * 返回全部列表
     *
     * @return
     */
    @RequestMapping("/findAll")
    @ResponseBody
    public List<StorePO> findAll() {
        return storeService.findAll();
    }


    /**
     * 返回全部列表
     *
     * @return
     */
    @RequestMapping("/findPage")
    @ResponseBody
    public PageResult findPage(int page, int rows) {
        return storeService.findPage(page, rows);
    }

    /**
     * 增加
     *
     * @param store
     * @return
     */
    @RequestMapping("/add")
    @ResponseBody
    public Result add(@RequestBody StorePO store) {
        try {
            storeService.add(store);
            return new Result(true, "增加成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, "增加失败");
        }
    }

    /**
     * 修改
     *
     * @param store
     * @return
     */
    @RequestMapping("/update")
    @ResponseBody
    public Result update(@RequestBody StorePO store) {
        try {
            storeService.update(store);
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
    public StorePO findOne(Integer id) {
        return storeService.findOne(id);
    }

    /**
     * 批量删除
     *
     * @param ids
     * @return
     */
    @RequestMapping("/delete")
    @ResponseBody
    public Result delete(Integer[] ids) {
        try {
            storeService.delete(ids);
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
    @ResponseBody
    public PageResult search(@RequestBody StorePO store, int page, int rows) {
        return storeService.findPage(store, page, rows);
    }

    @RequestMapping("/updateStatus")
    @ResponseBody
    public Result updateStatus(Integer id, String status) {
        try {
            storeService.updateStatus(id, status);
            return new Result(true, "审核状态成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, "审核状态失败");
        }
    }

}
