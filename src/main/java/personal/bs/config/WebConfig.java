package personal.bs.config;

import com.netflix.hystrix.contrib.metrics.eventstream.HystrixMetricsStreamServlet;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

/**
 * 相当于之前的web.xml
 * 主要配置servelet filter等
 */
@Configuration
public class WebConfig {

    /**
     * spring2.0以上的时候 hystrix仪表盘需要配置该servlet才行， 官方reference中也未提及。。很坑
     * @return
     */
    @Bean
    public ServletRegistrationBean getServlet() {
        HystrixMetricsStreamServlet streamServlet = new HystrixMetricsStreamServlet();
        ServletRegistrationBean registrationBean = new ServletRegistrationBean(streamServlet);
        registrationBean.setLoadOnStartup(1);
        registrationBean.addUrlMappings("/hystrix.stream");
        registrationBean.setName("HystrixMetricsStreamServlet");
        return registrationBean;
    }

//    @Bean
//    public CommonsMultipartResolver commonsMultipartResolver(){
//        CommonsMultipartResolver commonsMultipartResolver = new CommonsMultipartResolver();
//        commonsMultipartResolver.setDefaultEncoding("utf-8");
//        commonsMultipartResolver.setMaxInMemorySize(1024*1024*10);
//        commonsMultipartResolver.setMaxUploadSizePerFile(1024*1024*10);
//        return commonsMultipartResolver;
//    }

}
