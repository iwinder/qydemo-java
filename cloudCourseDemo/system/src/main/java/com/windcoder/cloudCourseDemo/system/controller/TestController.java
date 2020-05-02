package com.windcoder.cloudCourseDemo.system.controller;

import com.windcoder.cloudCourseDemo.system.domain.Test;
import com.windcoder.cloudCourseDemo.system.service.TestService;
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
