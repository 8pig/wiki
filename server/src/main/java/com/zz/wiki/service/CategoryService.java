package com.zz.wiki.service;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zz.wiki.domain.Category;
import com.zz.wiki.domain.CategoryExample;
import com.zz.wiki.mapper.CategoryMapper;
import com.zz.wiki.req.CategoryQueryReq;
import com.zz.wiki.req.CategorySaveReq;
import com.zz.wiki.resp.CategoryQueryResp;
import com.zz.wiki.resp.PageResp;
import com.zz.wiki.util.CopyUtil;
import com.zz.wiki.util.SnowFlake;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.util.List;

@Service
public class CategoryService {

    @Resource
    private CategoryMapper categoryMapper;

    @Resource
    private SnowFlake snowFlake;

    public PageResp<CategoryQueryResp> list (CategoryQueryReq req) {

        CategoryExample categoryExample = new CategoryExample();
        // @ 相当于where 条件
        CategoryExample.Criteria criteria = categoryExample.createCriteria();
        categoryExample.setOrderByClause("sort asc");

        PageHelper.startPage(req.getPage(), req.getSize());
        List<Category> categorys = categoryMapper.selectByExample(categoryExample);

        PageInfo<Category> pageInfo = new PageInfo<>(categorys);
        long total = pageInfo.getTotal();
        int pages = pageInfo.getPages();

        PageResp<CategoryQueryResp> pageResp = new PageResp<>();
        pageResp.setList(CopyUtil.copyList(categorys, CategoryQueryResp.class));
        pageResp.setTotal(pageInfo.getTotal());



        return pageResp;
    }


    public List<CategoryQueryResp> all (CategoryQueryReq req) {

        CategoryExample categoryExample = new CategoryExample();
        categoryExample.setOrderByClause("sort asc");


        List<Category> categorys = categoryMapper.selectByExample(categoryExample);

        List<CategoryQueryResp> list = CopyUtil.copyList(categorys, CategoryQueryResp.class);

        return list;
    }

    public int save(CategorySaveReq req) {
        /*
         * 保存
         * */
        Category category = CopyUtil.copy(req, Category.class);
        if(ObjectUtils.isEmpty(category.getId())){
            category.setId(snowFlake.nextId());
            return categoryMapper.insert(category);
        }else{
            return categoryMapper.updateByPrimaryKey(category);
        }



    }


    public int delete(Long id) {
        return categoryMapper.deleteByPrimaryKey(id);
    }
}
