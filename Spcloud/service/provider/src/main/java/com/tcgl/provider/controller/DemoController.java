package com.tcgl.provider.controller;

import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 11'
 *
 * @author Shuguang_Liux
 * @date 2021/10/29 18:25
 */
@RestController
//@RequestMapping("/provider")
@RefreshScope
public class DemoController {
    @GetMapping(value = "/demo/{string}")
    public String demoTesting(@PathVariable("string") String string){
        return "hello" + string;
    }
}
