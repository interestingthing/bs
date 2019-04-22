package personal.bs.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

/**
 * @author wendazhi
 * @date 2019
 * test
 */
@RestController
@ApiIgnore
@Slf4j
public class HelloController {

    @GetMapping("/healthCheck")
    public String healthCheck() {
        return "I am OK !";
    }
}
