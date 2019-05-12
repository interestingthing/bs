package personal.bs.controller;


import com.alibaba.fastjson.JSON;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import personal.bs.domain.po.SpecTemplatePO;
import personal.bs.domain.vo.PageResult;
import personal.bs.domain.vo.Result;
import personal.bs.domain.vo.SpecTemplateDto;
import personal.bs.service.SpecTemplateService;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * controller
 */
@RestController
@RequestMapping("/typeTemplate")
public class SpecTemplateController {

    @Resource
    private SpecTemplateService specTemplateService;

    /**
     * 返回全部列表
     *
     * @return
     */
    @RequestMapping("/findAll")
    public List<SpecTemplatePO> findAll() {
        return specTemplateService.findAll();
    }


    /**
     * 返回全部列表
     *
     * @return
     */
    @RequestMapping("/findPage")
    public PageResult findPage(int page, int rows) {
        return specTemplateService.findPage(page, rows);
    }

    /**
     * 增加
     *
     * @param specTemplateDto
     * @return
     */
    @RequestMapping("/add")
    public Result add(@RequestBody SpecTemplateDto specTemplateDto) {
        SpecTemplatePO specTemplatePO = new SpecTemplatePO();
        specTemplatePO.setName(specTemplateDto.getName());
        specTemplatePO.setSpecValueIds(JSON.toJSON(specTemplateDto.getSpecValueIds()).toString());
        specTemplatePO.setExtendAttributes(JSON.toJSON(specTemplateDto.getExtendAttributes()).toString());
        try {
            specTemplateService.add(specTemplatePO);
            return new Result(true, "增加成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, "增加失败");
        }
    }

    /**
     * 修改
     *
     * @param specTemplatePO
     * @return
     */
    @RequestMapping("/update")
    public Result update(@RequestBody SpecTemplatePO specTemplatePO) {
        try {
            specTemplateService.update(specTemplatePO);
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
    public SpecTemplatePO findOne(Integer id) {
        return specTemplateService.findOne(id);
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
            specTemplateService.delete(ids);
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
    public PageResult search(@RequestBody SpecTemplatePO SpecTemplatePO, int page, int rows) {
        return specTemplateService.findPage(SpecTemplatePO, page, rows);
    }

    @RequestMapping("/selectOptionList")
    public List<Map> selectOptionList() {
        return specTemplateService.selectOptionList();
    }

    @GetMapping("/findSpecList")
    public List<Map> findSpecList(Integer id) {
        return specTemplateService.findSpecList(id);
    }
}
