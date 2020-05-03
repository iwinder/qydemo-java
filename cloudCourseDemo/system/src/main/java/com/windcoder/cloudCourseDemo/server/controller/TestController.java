package com.windcoder.cloudCourseDemo.server.controller;

import com.windcoder.cloudCourseDemo.server.domain.Test;
import com.windcoder.cloudCourseDemo.server.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TestController {

    @Autowired
    private TestService testService;

    @GetMapping("/test")
    public List<Test> testA() {
        return testService.list();
    }
}
