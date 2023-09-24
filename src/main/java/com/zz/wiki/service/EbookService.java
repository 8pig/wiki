package com.zz.wiki.service;


import com.zz.wiki.domain.Ebook;
import com.zz.wiki.domain.EbookExample;
import com.zz.wiki.mapper.EbookMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author zhou
 */
@Service
public class EbookService {

    @Resource
    private EbookMapper ebookMapper;

    public List<Ebook> list () {
        return  ebookMapper.selectByExample(new EbookExample());
    }
}
