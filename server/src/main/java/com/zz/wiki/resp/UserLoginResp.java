package com.zz.wiki.resp;

import lombok.Data;

@Data
public class UserLoginResp {
    private Long id;

    private String loginName;

    private String name;

    private String token;
}
