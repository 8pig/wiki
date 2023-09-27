package com.zz.wiki.service;


import com.zz.wiki.domain.Demo;
import com.zz.wiki.domain.DemoExample;
import com.zz.wiki.mapper.DemoMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class DemoService {

    @Resource
    private DemoMapper demoMapper;

    public List<Demo> list () {
        return  demoMapper.selectByExample(new DemoExample());
    }
}
