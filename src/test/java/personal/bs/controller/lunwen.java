package personal.bs.controller;

import freemarker.template.Configuration;
import freemarker.template.Template;
import org.junit.Test;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

/**
 * @Author: chenjingang@gauzi.com  2019/5/29 10:24
 */
public class lunwen {
    @Test
    public void freeMarkerConfigurer() throws  Exception {
        Configuration configuration = new Configuration(Configuration.VERSION_2_3_23);
//        configuration.setClassForTemplateLoading();
//       FreeMarkerConfigurer configuration = new FreeMarkerConfigurer();
        configuration.setDefaultEncoding("UTF-8");
        configuration.setClassForTemplateLoading(CreateHtmlByFreemarker);
//        configuration.setTemplateLoaderPath("/templates/ftl/");
//        configuration.setConfiguration(configuration);
        configuration.setTemplateLoader();
        Template template = configuration.getTemplate("item.ftl");

        System.out.println(template);
        //return configuration;
    }
}
