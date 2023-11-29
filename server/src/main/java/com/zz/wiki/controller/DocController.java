package com.zz.wiki.controller;


import com.zz.wiki.domain.Content;
import com.zz.wiki.req.DocQueryReq;
import com.zz.wiki.req.DocSaveReq;
import com.zz.wiki.resp.DocQueryResp;
import com.zz.wiki.resp.CommonResp;
import com.zz.wiki.resp.PageResp;
import com.zz.wiki.service.DocService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/doc")
public class DocController {



    @Resource
    private DocService docService;

    @GetMapping("/list")
    public CommonResp<PageResp<DocQueryResp>> list (@Valid DocQueryReq req){
        System.out.println(req.toString());
        PageResp<DocQueryResp> list = docService.list(req);

        return  CommonResp.ok(list);
    }

    @GetMapping("/all")
    public CommonResp<List<DocQueryResp>> all (@Valid DocQueryReq req){
        System.out.println(req.toString());
        List<DocQueryResp> list = docService.all(req);

        return  CommonResp.ok(list);
    }

    @PostMapping("/save")
    public CommonResp save (@Valid @RequestBody DocSaveReq req){
        return  CommonResp.ok(docService.save(req));
    }

    @DeleteMapping("/delete/{id}")
    public CommonResp delete (@PathVariable Long id){
        return  CommonResp.ok(docService.delete(id));
    }


    @GetMapping("/find-content/{id}")
    public CommonResp<String>
    findContent (@PathVariable Long id){
        System.out.println("id"+id);
        String content = docService.findContent(id);

        return  CommonResp.ok(content);
    }

    @GetMapping("/vote/{id}")
    public CommonResp
    vote (@PathVariable Long id){
        docService.vote(id);

        return  CommonResp.ok("");
    }
}
