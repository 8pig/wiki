package com.zz.wiki.req;


import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
public class UserSaveReq {
    private Long id;

    @NotNull(message = "用户名不可为空")
    private String loginName;

    @NotNull(message = "name不可为空")
    private String name;

    @NotNull(message = "password not null")
    @Pattern(regexp = "^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,32}$", message = "【密码】至少包含 数字和英文，长度6-32")
    private String password;
}
