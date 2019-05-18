package personal.bs;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import javax.servlet.annotation.WebServlet;

@SpringBootApplication
@ComponentScan(basePackages = {"personal.bs"})
@MapperScan("personal.bs.dao.mapper")
@Slf4j
public class WGXCBApplication extends SpringBootServletInitializer {
    public static void main(String[] args) {

        ApplicationContext ctx = SpringApplication.run(WGXCBApplication.class, args);
        //print env profile info
        String[] activeProfiles = ctx.getEnvironment().getActiveProfiles();
        for (String profile : activeProfiles) {
            log.info("boot by profile:  {} =============================== {} ====== {} ====== {}", profile, profile, profile, profile);
        }
        System.out.println("start over==============================================================================");
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(WGXCBApplication.class);
    }

}
