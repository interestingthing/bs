package personal.bs.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import personal.bs.domain.po.TypePO;
import personal.bs.domain.vo.PageResult;
import personal.bs.domain.vo.Result;
import personal.bs.service.TypeService;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/itemCat")
public class TypeController {

    @Resource
    private TypeService typeService;

    /**
     * 返回全部列表
     *
     * @return
     */
    @RequestMapping("/findAll")
    @ResponseBody
    public List<TypePO> findAll() {
        return typeService.findAll();
    }


    /**
     * 返回全部列表
     *
     * @return
     */
    @RequestMapping("/findPage")
    @ResponseBody
    public PageResult findPage(int page, int rows) {
        return typeService.findPage(page, rows);
    }

    /**
     * 增加
     *
     * @param typePO
     * @return
     */
    @RequestMapping("/add")
    @ResponseBody
    public Result add(@RequestBody TypePO typePO) {
        try {
            typeService.add(typePO);
            return new Result(true, "增加成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, "增加失败");
        }
    }

    /**
     * 修改
     *
     * @param typePO
     * @return
     */
    @RequestMapping("/update")
    @ResponseBody
    public Result update(@RequestBody TypePO typePO) {
        try {
            typeService.update(typePO);
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
    public TypePO findOne(Integer id) {
        return typeService.findOne(id);
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
            typeService.delete(ids);
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
    public PageResult search(@RequestBody TypePO typePO, int page, int rows) {
        return typeService.findPage(typePO, page, rows);
    }

    @RequestMapping("/findByParentId")
    @ResponseBody
    public List<TypePO> findByParentId(Integer parentId) {
        return typeService.findByParentId(parentId);
    }

    @RequestMapping("/showTypeList")
    @ResponseBody
    public Map showTypeList(Model model) {

        Map map = typeService.showTypeList();
        model.addAttribute("map", map);
        return map;
    }

}
