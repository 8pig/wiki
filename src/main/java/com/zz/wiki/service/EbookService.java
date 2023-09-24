package com.zz.wiki.service;


import com.zz.wiki.domain.Ebook;
import com.zz.wiki.domain.EbookExample;
import com.zz.wiki.mapper.EbookMapper;
import com.zz.wiki.req.EbookReq;
import com.zz.wiki.resp.EbookResp;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

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

    public List<EbookResp> list (EbookReq req) {
        EbookExample ebookExample = new EbookExample();
        // @ 相当于where 条件
        EbookExample.Criteria criteria = ebookExample.createCriteria();
        criteria.andNameLike("%"+ req.getName() + "%");
        List<Ebook> ebooks = ebookMapper.selectByExample(ebookExample);
        List<EbookResp> respList = new ArrayList<>();

        for (Ebook ebook : ebooks) {
            EbookResp ebookResp = new EbookResp();
            BeanUtils.copyProperties(ebook, ebookResp);
            respList.add(ebookResp);
        }
        
        
        return respList;
    }
}
