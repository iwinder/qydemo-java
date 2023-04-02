package com.windcoder.cloudCourseDemo.server.dto;

import lombok.Data;

import java.util.HashSet;
import java.util.List;

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
    /**
     * 所有资源，用于前端界面控制
     */
    private List<ResourceDto> resources;

    /**
     * 所有资源中的请求，用于后端接口拦截
     */
    private HashSet<String> requests;

}
