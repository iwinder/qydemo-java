package com.windcoder.cloudCourseDemo.server.dto;


import lombok.Data;

import java.util.List;

@Data
public class RoleDto {

    /**
     * id
     */
    private String id;

    /**
     * 角色
     */
    private String name;

    /**
     * 描述
     */
    private String desc;

    private List<String> resourceIds;


    private List<String> userIds;

}