package com.zz.wiki.service;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zz.wiki.domain.Ebook;
import com.zz.wiki.domain.EbookExample;
import com.zz.wiki.mapper.EbookMapper;
import com.zz.wiki.req.EbookReq;
import com.zz.wiki.resp.EbookResp;
import com.zz.wiki.resp.PageResp;
import com.zz.wiki.util.CopyUtil;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author zhou
 */
@Service
public class EbookService {


    @Resource
    private EbookMapper ebookMapper;

    public PageResp<EbookResp> list (EbookReq req) {

        EbookExample ebookExample = new EbookExample();
        // @ 相当于where 条件
        EbookExample.Criteria criteria = ebookExample.createCriteria();
        if(!ObjectUtils.isEmpty(req.getName())){
            criteria.andNameLike("%"+ req.getName() + "%");
        }
        PageHelper.startPage(req.getPage(), req.getSize());
        List<Ebook> ebooks = ebookMapper.selectByExample(ebookExample);

        PageInfo<Ebook> pageInfo = new PageInfo<>(ebooks);
        long total = pageInfo.getTotal();
        int pages = pageInfo.getPages();
        System.out.println(total);
        System.out.println(pages);
        PageResp<EbookResp> pageResp = new PageResp<>();
        pageResp.setList(CopyUtil.copyList(ebooks, EbookResp.class));
        pageResp.setTotal(pageInfo.getTotal());


//        for (Ebook ebook : ebooks) {
//            EbookResp ebookResp = new EbookResp();
//            BeanUtils.copyProperties(ebook, ebookResp);
//            EbookResp copy = CopyUtil.copy(ebook, EbookResp.class);
//            respList.add(copy);
//        }

        return pageResp;
    }
}
