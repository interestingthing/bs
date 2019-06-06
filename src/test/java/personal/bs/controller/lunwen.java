package personal.bs.controller;

import freemarker.template.Configuration;
import freemarker.template.Template;
import org.junit.Test;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import java.io.File;

/**
 * @Author: chenjingang@gauzi.com  2019/5/29 10:24
 */
public class lunwen {
    @Test
    public void freeMarkerConfigurer() throws Exception {
        Configuration configuration = new Configuration(Configuration.VERSION_2_3_23);
        configuration.setDirectoryForTemplateLoading(new File("/Users/chenjingang/Desktop/wgxcb/src/main/resources/templates/ftl/"));
        configuration.setDefaultEncoding("UTF-8");
        Template template = configuration.getTemplate("item.ftl");

//        configuration.setClassForTemplateLoading();
//        Configuration configuration = new Configuration();

//        FreeMarkerConfigurer configuration = new FreeMarkerConfigurer();
//        configuration.setDefaultEncoding("UTF-8");
//        configuration.setTemplateLoaderPath("/templates/ftl/");

        System.out.println(template);
    }

    @Test
    public void freeMarkerConfigurer2() throws Exception {
        FreeMarkerConfigurer configuration = new FreeMarkerConfigurer();
        configuration.setDefaultEncoding("UTF-8");
        configuration.setTemplateLoaderPath("classpath:/templates/ftl/");

        Configuration configuration1 = new Configuration(Configuration.VERSION_2_3_23);
        configuration1.setDirectoryForTemplateLoading(new File("/Users/chenjingang/Desktop/wgxcb/src/main/resources/templates/ftl/"));
        configuration1.setDefaultEncoding("UTF-8");
        configuration.setConfiguration(configuration1);
        Template template = configuration.getConfiguration().getTemplate("item.ftl");

        System.out.println(template);
    }
}
