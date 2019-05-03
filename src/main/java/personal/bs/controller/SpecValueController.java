package personal.bs.controller;


import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import personal.bs.domain.po.SpecValuePO;
import personal.bs.domain.vo.PageResult;
import personal.bs.domain.vo.Result;
import personal.bs.service.SpecValueService;

import javax.annotation.Resource;
import java.util.List;

/**
 * controller
 *
 */
@RestController
@RequestMapping("/specificationOption")
public class SpecValueController {

    @Resource
    private SpecValueService specValueService;

    /**
     * 返回全部列表
     *
     * @return
     */
    @RequestMapping("/findAll")
    public List<SpecValuePO> findAll() {
        return specValueService.findAll();
    }


    /**
     * 返回全部列表
     *
     * @return
     */
    @RequestMapping("/findPage")
    public PageResult findPage(int page, int rows) {
        return specValueService.findPage(page, rows);
    }

    /**
     * 增加
     *
     * @param specValuePO
     * @return
     */
    @RequestMapping("/add")
    public Result add(@RequestBody SpecValuePO specValuePO) {
        try {
            specValueService.add(specValuePO);
            return new Result(true, "增加成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, "增加失败");
        }
    }

    /**
     * 修改
     *
     * @param specValuePO
     * @return
     */
    @RequestMapping("/update")
    public Result update(@RequestBody SpecValuePO specValuePO) {
        try {
            specValueService.update(specValuePO);
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
    public SpecValuePO findOne(Integer id) {
        return specValueService.findOne(id);
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
            specValueService.delete(ids);
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
    public PageResult search(@RequestBody SpecValuePO specValuePO, int page, int rows) {
        return specValueService.findPage(specValuePO, page, rows);
    }

}
