package com.windcoder.cloudCourseDemo.server.dto;

import lombok.Data;

@Data
public class ResponseDto<T> {
    /**
     * 业务上的成功或失败
     */
    private boolean success = true;

    /**
     * 返回码
     */
    private String code;

    /**
     * 返回信息
     */
    private String message;

    /**
     * 返回泛型数据，自定义类型
     */
    private T content;

    public ResponseDto() {
    }

    public ResponseDto(T content) {
        this.content = content;
    }
}
