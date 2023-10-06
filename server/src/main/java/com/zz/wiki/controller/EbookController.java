package com.zz.wiki.controller;


import com.zz.wiki.req.EbookReq;
import com.zz.wiki.resp.CommonResp;
import com.zz.wiki.resp.EbookResp;
import com.zz.wiki.service.EbookService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;


@RestController
@RequestMapping("/ebook")
public class EbookController {



    @Resource
    private EbookService ebookService;

    @GetMapping("/list")
    public CommonResp< List<EbookResp>> list (EbookReq req){
        List<EbookResp> list = ebookService.list(req);

        return  CommonResp.ok(list);
    }
}
