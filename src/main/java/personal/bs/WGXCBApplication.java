package personal.bs;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"personal.bs"})
@Slf4j
public class WGXCBApplication {
    public static void main(String[] args) {

        ApplicationContext ctx = SpringApplication.run(WGXCBApplication.class, args);
        //print env profile info
        String[] activeProfiles = ctx.getEnvironment().getActiveProfiles();
        for (String profile : activeProfiles) {
            log.info("boot by profile:  {} =============================== {} ====== {} ====== {}", profile, profile, profile, profile);
        }
        System.out.println("start over");
    }
}
