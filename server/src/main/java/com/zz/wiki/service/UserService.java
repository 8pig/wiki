package com.zz.wiki.service;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zz.wiki.domain.User;
import com.zz.wiki.domain.UserExample;
import com.zz.wiki.exception.BusinessException;
import com.zz.wiki.mapper.UserMapper;
import com.zz.wiki.req.UserQueryReq;
import com.zz.wiki.req.UserSaveReq;
import com.zz.wiki.resp.UserQueryResp;
import com.zz.wiki.resp.PageResp;
import com.zz.wiki.util.CopyUtil;
import com.zz.wiki.util.SnowFlake;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.List;

import static com.zz.wiki.exception.BusinessExceptionCode.USER_LOGIN_NAME_EXIST;

/**
 * @author zhou
 */
@Service
public class UserService {


    @Resource
    private UserMapper userMapper;

    @Resource
    private SnowFlake snowFlake;

    public PageResp<UserQueryResp> list (UserQueryReq req) {

        UserExample userExample = new UserExample();
        // @ 相当于where 条件
        UserExample.Criteria criteria = userExample.createCriteria();
        if(!ObjectUtils.isEmpty(req.getLoginName())){
            criteria.andLoginNameEqualTo(req.getLoginName());
        }
        PageHelper.startPage(req.getPage(), req.getSize());
        List<User> users = userMapper.selectByExample(userExample);

        PageInfo<User> pageInfo = new PageInfo<>(users);
        long total = pageInfo.getTotal();
        int pages = pageInfo.getPages();
        System.out.println(total);
        System.out.println(pages);
        PageResp<UserQueryResp> pageResp = new PageResp<>();
        pageResp.setList(CopyUtil.copyList(users, UserQueryResp.class));
        pageResp.setTotal(pageInfo.getTotal());


//        for (User user : users) {
//            UserResp userResp = new UserResp();
//            BeanUtils.copyProperties(user, userResp);
//            UserResp copy = CopyUtil.copy(user, UserResp.class);
//            respList.add(copy);
//        }

        return pageResp;
    }


    public int save(UserSaveReq req) {
        /*
        * 保存
        * */
        User user = CopyUtil .copy(req, User.class);
        if(ObjectUtils.isEmpty(user.getId())){
            User userDB = selectByLoginName(req.getLoginName());
            if(ObjectUtils.isEmpty(userDB)){
                user.setId(snowFlake.nextId());
                return userMapper.insert(user);
            }
            // 用户名已存在
            throw new BusinessException(USER_LOGIN_NAME_EXIST);
        }else{
            user.setLoginName(null);
            return userMapper.updateByPrimaryKeySelective(user);
        }
    }

    public User selectByLoginName(String loginName){
        UserExample userExample = new UserExample();
        // @ 相当于where 条件
        UserExample.Criteria criteria = userExample.createCriteria();
        criteria.andLoginNameEqualTo(loginName);
        List<User> users = userMapper.selectByExample(userExample);

        if(CollectionUtils.isEmpty(users)){
            return null;
        }

        return users.get(0);


    }


    public int delete(Long id) {
        return userMapper.deleteByPrimaryKey(id);
    }
}
