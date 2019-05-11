package personal.bs.controller;

import com.alibaba.fastjson.JSON;
import org.junit.Test;
import personal.bs.domain.vo.SpecTemplateDto;

import java.util.ArrayList;

/**
 * @Author: chenjingang@gauzi.com  2019/5/3 23:08
 */

public class TestJson {

    @Test
    public void testJson() {
        SpecTemplateDto specTemplateDto = new SpecTemplateDto();
        ArrayList<SpecTemplateDto.SpecValueIdsBean> specValueIdsBeans = new ArrayList<>();
        specValueIdsBeans.add(new SpecTemplateDto.SpecValueIdsBean(1,"颜色"));
        specValueIdsBeans.add(new SpecTemplateDto.SpecValueIdsBean(2,"尺寸"));


        specTemplateDto.setSpecValueIds(specValueIdsBeans);
//        SpecTemplatePO specTemplatePO = new SpecTemplatePO();
//        specTemplatePO.setName(specTemplateDto.getName());
        String spec = JSON.toJSON(specTemplateDto.getSpecValueIds()).toString();


        System.out.println(spec);
    }
}
