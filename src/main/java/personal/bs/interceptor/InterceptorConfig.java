package personal.bs.interceptor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

    private static List includeUrl = new ArrayList<String>();
    private static List excludeUrl = new ArrayList<String>();

    //设置（模糊）匹配的url
    static {
        includeUrl.add("/**");

        excludeUrl.add("/favicon.ico");
    }
    //将拦截器作为bean写入配置中
    @Bean
    public RequestInterceptor getRequestInterceptor() {
        return new RequestInterceptor();
    }

    /**
     * 配置servlet处理
     */
    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {


        registry.addInterceptor(getRequestInterceptor()).addPathPatterns(includeUrl).excludePathPatterns(excludeUrl);
    }


}
