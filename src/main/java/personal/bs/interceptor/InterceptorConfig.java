package personal.bs.interceptor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.List;

/**
 * @author chenjingang
 */
@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

    private static List<String> includeUrl = new ArrayList<>();
    private static List<String> excludeUrl = new ArrayList<>();

    //设置（模糊）匹配的url
    static {
        includeUrl.add("/**");
        includeUrl.add("/user/index");

        
        excludeUrl.add("/favicon.ico");
    }

    //将拦截器作为bean写入配置中
    @Bean
    public CheckLoginInterceptor getRequestInterceptor() {
        return new CheckLoginInterceptor();
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
