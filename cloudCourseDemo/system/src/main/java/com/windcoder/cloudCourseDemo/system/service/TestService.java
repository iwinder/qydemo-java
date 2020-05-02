package com.windcoder.cloudCourseDemo.system.service;

import com.windcoder.cloudCourseDemo.system.domain.Test;
import com.windcoder.cloudCourseDemo.system.mapper.TestMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class TestService {
    @Resource
    private TestMapper testMapper;

    public List<Test> list() {
        return testMapper.list();
    }

}
