package personal.bs;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ActiveProfiles;

@SpringBootApplication
@Slf4j
@ActiveProfiles("test")
public class TestRunAppication {

    public static void main(String[] args) {

        ApplicationContext ctx = SpringApplication.run(TestRunAppication.class, args);
        //print env profile info
        String[] activeProfiles = ctx.getEnvironment().getActiveProfiles();

        for (String profile : activeProfiles) {
            log.info("unit test ======================");
            log.info("boot by profile:  {} =============================== {} ====== {} ====== {}", profile, profile, profile, profile);
        }
    }
}
