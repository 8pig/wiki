package com.zz.wiki.controller;


import com.zz.wiki.domain.Test;
import com.zz.wiki.service.TestService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class TestController {


    @Value("${test.hello:CATCH}")
    private String name;

    @Resource
    private TestService testService;

//    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    @GetMapping("/hello")
    public String get () {
        return  name;
    }

    @GetMapping("/test/list")
    public List<Test> list (){
        return testService.list();
//        return  testService.list();
    }
}
