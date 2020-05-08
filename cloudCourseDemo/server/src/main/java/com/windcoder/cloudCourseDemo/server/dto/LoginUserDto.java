package com.windcoder.cloudCourseDemo.server.dto;

import lombok.Data;

@Data
public class LoginUserDto {
    /**
     * id
     */
    private String id;
    /**
     * 登陆名
     */
    private String loginName;
    /**
     * 昵称
     */
    private String name;

    /**
     * 登录凭证
     */
    private String token;
}
