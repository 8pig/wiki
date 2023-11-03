package com.zz.wiki.service;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zz.wiki.domain.Ebook;
import com.zz.wiki.domain.EbookExample;
import com.zz.wiki.mapper.EbookMapper;
import com.zz.wiki.req.EbookQueryReq;
import com.zz.wiki.req.EbookSaveReq;
import com.zz.wiki.resp.EbookQueryResp;
import com.zz.wiki.resp.PageResp;
import com.zz.wiki.util.CopyUtil;
import com.zz.wiki.util.SnowFlake;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

/**
 * @author zhou
 */
@Service
public class EbookService {


    @Resource
    private EbookMapper ebookMapper;

    @Resource
    private SnowFlake snowFlake;

    public PageResp<EbookQueryResp> list (EbookQueryReq req) {

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
        PageResp<EbookQueryResp> pageResp = new PageResp<>();
        pageResp.setList(CopyUtil.copyList(ebooks, EbookQueryResp.class));
        pageResp.setTotal(pageInfo.getTotal());


//        for (Ebook ebook : ebooks) {
//            EbookResp ebookResp = new EbookResp();
//            BeanUtils.copyProperties(ebook, ebookResp);
//            EbookResp copy = CopyUtil.copy(ebook, EbookResp.class);
//            respList.add(copy);
//        }

        return pageResp;
    }


    public int save(EbookSaveReq req) {
        /*
        * 保存
        * */
        Ebook ebook = CopyUtil.copy(req, Ebook.class);
        if(ObjectUtils.isEmpty(ebook.getId())){
            ebook.setId(snowFlake.nextId());
            return ebookMapper.insert(ebook);
        }else{
            return ebookMapper.updateByPrimaryKey(ebook);
        }



    }


    public int delete(Long id) {
        return ebookMapper.deleteByPrimaryKey(id);
    }
}
