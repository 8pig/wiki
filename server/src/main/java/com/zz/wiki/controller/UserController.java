package com.zz.wiki.controller;


import com.alibaba.fastjson.JSONObject;
import com.zz.wiki.req.UserLoginReq;
import com.zz.wiki.req.UserQueryReq;
import com.zz.wiki.req.UserSaveReq;
import com.zz.wiki.resp.CommonResp;
import com.zz.wiki.resp.UserLoginResp;
import com.zz.wiki.resp.UserQueryResp;
import com.zz.wiki.resp.PageResp;
import com.zz.wiki.service.UserService;
import com.zz.wiki.util.SnowFlake;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/user")
public class UserController {



    @Resource
    private UserService userService;

    @Resource
    private RedisTemplate redisTemplate;

    @Resource
    private SnowFlake snowFlake;



    @GetMapping("/list")
    public CommonResp<PageResp<UserQueryResp>> list (@Valid UserQueryReq req){
        System.out.println(req.toString());
        PageResp<UserQueryResp> list = userService.list(req);

        return  CommonResp.ok(list);
    }

    @PostMapping("/save")
    public CommonResp save (@Valid @RequestBody UserSaveReq req){
//        MD5密码
        req.setPassword(DigestUtils.md5DigestAsHex(req.getPassword().getBytes()));
        return  CommonResp.ok(userService.save(req));
    }

    @DeleteMapping("/delete/{id}")
    public CommonResp delete (@PathVariable Long id){
        return  CommonResp.ok(userService.delete(id));
    }


    @PostMapping("/login")
    public CommonResp<UserLoginResp> login (@Valid @RequestBody UserLoginReq req){


        UserLoginResp login = userService.login(req);

        Long token = snowFlake.nextId();

        redisTemplate.opsForValue().set(token, JSONObject.toJSONString(login), 3600 * 24, TimeUnit.SECONDS);

        System.out.println(redisTemplate.hasKey(token));
        System.out.println(redisTemplate.opsForValue().get(token));
        login.setToken(token.toString());


        return   CommonResp.ok(login);
    }
}
