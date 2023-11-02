package com.zz.wiki.controller;


import com.zz.wiki.req.EbookQueryReq;
import com.zz.wiki.req.EbookSaveReq;
import com.zz.wiki.resp.CommonResp;
import com.zz.wiki.resp.EbookQueryResp;
import com.zz.wiki.resp.PageResp;
import com.zz.wiki.service.EbookService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;


@RestController
@RequestMapping("/ebook")
public class EbookController {



    @Resource
    private EbookService ebookService;

    @GetMapping("/list")
    public CommonResp<PageResp<EbookQueryResp>> list (EbookQueryReq req){
        PageResp<EbookQueryResp> list = ebookService.list(req);

        return  CommonResp.ok(list);
    }

    @PostMapping("/save")
    public CommonResp save (@RequestBody EbookSaveReq req){
        return  CommonResp.ok(ebookService.save(req));
    }


}
