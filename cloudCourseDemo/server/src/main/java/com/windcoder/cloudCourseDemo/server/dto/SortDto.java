package com.windcoder.cloudCourseDemo.server.dto;

import lombok.Data;

@Data
public class SortDto {
    /**
     * ID
     */
    private String id;

    /**
     * 当前排序
     */
    private int oldSort;

    /**
     * 新排序
     */
    private int newSort;

}
