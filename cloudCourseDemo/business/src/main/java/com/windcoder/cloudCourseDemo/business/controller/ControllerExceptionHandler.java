package com.windcoder.cloudCourseDemo.business.controller;

import com.windcoder.cloudCourseDemo.server.dto.ResponseDto;
import com.windcoder.cloudCourseDemo.server.exception.ValidatorException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *  统一异常处理
 */
@ControllerAdvice
@Slf4j
public class ControllerExceptionHandler {

    @ExceptionHandler(value = ValidatorException.class)
    @ResponseBody
    public ResponseDto validatorExceptionHandler(ValidatorException e) {
        ResponseDto responseDto = new ResponseDto();
        responseDto.setSuccess(false);
        log.warn(e.getMessage());
        responseDto.setMessage("请求参数异常！");
        return responseDto;
    }
}
