package com.zz.wiki.resp;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserLoginResp     {
    private Long id;

    private String loginName;

    private String name;

    private String token;
}
