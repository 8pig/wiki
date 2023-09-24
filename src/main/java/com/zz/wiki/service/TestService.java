package com.zz.wiki.service;


import com.zz.wiki.domain.Test;
import com.zz.wiki.mapper.TestMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class TestService {

    @Resource
    private TestMapper testMapper;

    public List<Test> list () {
        return  testMapper.list();

    }
}
