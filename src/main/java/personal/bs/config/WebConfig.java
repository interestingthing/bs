package personal.bs.config;

import com.netflix.hystrix.contrib.metrics.eventstream.HystrixMetricsStreamServlet;
import org.apache.catalina.webresources.StandardRoot;
import org.springframework.boot.web.embedded.tomcat.TomcatEmbeddedWebappClassLoader;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.ServletContainerInitializer;

/**
 * 相当于之前的web.xml
 * 主要配置servelet filter等
 */
@Configuration
public class WebConfig {

    /**
     * spring2.0以上的时候 hystrix仪表盘需要配置该servlet才行， 官方reference中也未提及。。很坑
     *
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


//    @Bean
//    public InternalResourceViewResolver internalResourceViewResolver() {
//
//        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
////        viewResolver.setPrefix("classpath:/templates/");
//        viewResolver.setSuffix(".jsp");
//        viewResolver.setContentType("text/html;charset=UTF-8");
//
//        return viewResolver;
//    }

//    @Bean
//    public ServletContainerInitializer embeddedServletContainerFactory() {
//        return new TomcatEmbeddedServletContainerFactory() {
//
//            @Override
//            protected void postProcessContext(Context context) {
//                final int maxCacheSize = 40 * 1024;
//                StandardRoot standardRoot = new StandardRoot(context);
//                standardRoot.setCacheMaxSize(maxCacheSize);
//                context.setResources(standardRoot);
//            }
//        };
//    }
}
