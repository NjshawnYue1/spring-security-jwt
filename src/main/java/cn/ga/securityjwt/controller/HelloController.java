package cn.ga.securityjwt.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;


/**
 * 测试security
 *
 * @author GuAn
 * @Description
 * @createTime 2020年11月06日 15:09:00
 */
@RestController
@Log4j2
public class HelloController {

    @GetMapping("hello")
    public String say() {
        return "hello";
    }

}
