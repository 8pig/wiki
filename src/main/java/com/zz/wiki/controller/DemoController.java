package com.zz.wiki.controller;


import com.zz.wiki.domain.Demo;
import com.zz.wiki.service.DemoService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author zhou
 */
@RestController
@RequestMapping("/demo")
public class DemoController {



    @Resource
    private DemoService demoService;

    @GetMapping("/list")
    public List<Demo> list (){
        return demoService.list();
    }
}
