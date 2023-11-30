package com.zz.wiki.service;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zz.wiki.domain.Content;
import com.zz.wiki.domain.Doc;
import com.zz.wiki.domain.DocExample;
import com.zz.wiki.exception.BusinessException;
import com.zz.wiki.exception.BusinessExceptionCode;
import com.zz.wiki.mapper.ContentMapper;
import com.zz.wiki.mapper.CustomDocMapper;
import com.zz.wiki.mapper.DocMapper;
import com.zz.wiki.req.DocQueryReq;
import com.zz.wiki.req.DocSaveReq;
import com.zz.wiki.resp.DocQueryResp;
import com.zz.wiki.resp.PageResp;
import com.zz.wiki.util.CopyUtil;
import com.zz.wiki.util.RedisUtil;
import com.zz.wiki.util.RequestContext;
import com.zz.wiki.util.SnowFlake;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.util.List;

@Service
public class DocService {

    @Resource
    private DocMapper docMapper;

    @Resource
    private SnowFlake snowFlake;

    @Resource
    private ContentMapper contentMapper;


    @Resource
    private CustomDocMapper customDocMapper;

    @Resource
    private RedisUtil redisUtil;


    public PageResp<DocQueryResp> list (DocQueryReq req) {

        DocExample docExample = new DocExample();
        // @ 相当于where 条件
        DocExample.Criteria criteria = docExample.createCriteria();
        docExample.setOrderByClause("sort asc");

        PageHelper.startPage(req.getPage(), req.getSize());
        List<Doc> docs = docMapper.selectByExample(docExample);

        PageInfo<Doc> pageInfo = new PageInfo<>(docs);
        long total = pageInfo.getTotal();
        int pages = pageInfo.getPages();

        PageResp<DocQueryResp> pageResp = new PageResp<>();
        pageResp.setList(CopyUtil.copyList(docs, DocQueryResp.class));
        pageResp.setTotal(pageInfo.getTotal());



        return pageResp;
    }


    public List<DocQueryResp> all (DocQueryReq req) {

        DocExample docExample = new DocExample();
        docExample.setOrderByClause("sort asc");


        List<Doc> docs = docMapper.selectByExample(docExample);

        List<DocQueryResp> list = CopyUtil.copyList(docs, DocQueryResp.class);

        return list;
    }

    public int save(DocSaveReq req) {
        /*
         * 保存
         * */
        Doc doc = CopyUtil.copy(req, Doc.class);
        Content content = CopyUtil.copy(req, Content.class);

        if(ObjectUtils.isEmpty(doc.getId())){
            doc.setId(snowFlake.nextId());
            doc.setViewCount(0);
            doc.setVoteCount(0);
            content.setId(doc.getId());
            contentMapper.insert(content);
            return docMapper.insert(doc);
        }else{
            // 大字段使用 下面方法
            int contentInt = contentMapper.updateByPrimaryKeyWithBLOBs(content);
            if(contentInt == 0){
                contentMapper.insert(content);

            }

            return docMapper.updateByPrimaryKey(doc);
        }



    }


    public int delete(Long id) {
        return docMapper.deleteByPrimaryKey(id);
    }

    public String findContent(Long id) {
        Content content = contentMapper.selectByPrimaryKey(id);
        /* 文档阅读数+1*/
        customDocMapper.incrementViewCount(id);
        return content.getContent();
    }


    public void vote(Long id) {
//        获取ip+doc 做key 24小时不能重复
        String key = RequestContext.getRemoteAddr();

        if(redisUtil.validateRepeat("DOC_VOTE_"+ id + "_" + key, 3600 * 24)){
            customDocMapper.incrementVoteCount(id);
        }else {
            throw  new BusinessException(BusinessExceptionCode.VOTE_REPEAT);
        }




    }
}
