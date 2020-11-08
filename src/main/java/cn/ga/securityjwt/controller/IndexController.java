package cn.ga.securityjwt.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author GuAn
 * @Description
 * @createTime 2020年11月06日 15:27:00
 */
@Controller
public class IndexController {

    @GetMapping("/{pageName}")
    public String index(@PathVariable String pageName) {
        return pageName;
    }
}
