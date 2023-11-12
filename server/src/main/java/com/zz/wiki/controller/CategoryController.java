package com.zz.wiki.controller;


import com.zz.wiki.req.CategoryQueryReq;
import com.zz.wiki.req.CategorySaveReq;
import com.zz.wiki.resp.CategoryQueryResp;
import com.zz.wiki.resp.CommonResp;
import com.zz.wiki.resp.PageResp;
import com.zz.wiki.service.CategoryService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {



    @Resource
    private CategoryService categoryService;

    @GetMapping("/list")
    public CommonResp<PageResp<CategoryQueryResp>> list (@Valid CategoryQueryReq req){
        System.out.println(req.toString());
        PageResp<CategoryQueryResp> list = categoryService.list(req);

        return  CommonResp.ok(list);
    }

    @GetMapping("/all")
    public CommonResp<List<CategoryQueryResp>> all (@Valid CategoryQueryReq req){
        System.out.println(req.toString());
        List<CategoryQueryResp> list = categoryService.all(req);

        return  CommonResp.ok(list);
    }

    @PostMapping("/save")
    public CommonResp save (@RequestBody CategorySaveReq req){
        return  CommonResp.ok(categoryService.save(req));
    }

    @DeleteMapping("/delete/{id}")
    public CommonResp delete (@PathVariable Long id){
        return  CommonResp.ok(categoryService.delete(id));
    }
}
