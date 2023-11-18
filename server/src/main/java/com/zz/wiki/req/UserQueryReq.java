package com.zz.wiki.req;


import lombok.Data;

@Data
public class UserQueryReq extends PageReq{
    private String loginName;
}
