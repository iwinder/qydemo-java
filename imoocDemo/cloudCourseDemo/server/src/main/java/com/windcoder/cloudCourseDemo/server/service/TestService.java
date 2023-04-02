package com.windcoder.cloudCourseDemo.server.service;

import com.windcoder.cloudCourseDemo.server.domain.Test;
import com.windcoder.cloudCourseDemo.server.domain.TestExample;
import com.windcoder.cloudCourseDemo.server.mapper.TestMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class TestService {
    @Resource
    private TestMapper testMapper;

    public List<Test> list() {
        TestExample testExample = new TestExample();
//        testExample.setOrderByClause("id ASC");
        testExample.createCriteria().andIdEqualTo("1");
        testExample.setOrderByClause("id DESC");
        return testMapper.selectByExample(testExample);
    }

}
