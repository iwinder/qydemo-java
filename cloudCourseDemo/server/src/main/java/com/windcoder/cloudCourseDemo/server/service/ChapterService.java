package com.windcoder.cloudCourseDemo.server.service;

import com.windcoder.cloudCourseDemo.server.domain.Chapter;
import com.windcoder.cloudCourseDemo.server.domain.ChapterExample;
import com.windcoder.cloudCourseDemo.server.mapper.ChapterMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ChapterService {
    @Resource
    private ChapterMapper chapterMapper;

    public List<Chapter> list() {
        ChapterExample testExample = new ChapterExample();
//        testExample.setOrderByClause("id ASC");
        testExample.createCriteria().andIdEqualTo("1");
        testExample.setOrderByClause("id DESC");
        return chapterMapper.selectByExample(testExample);
    }

}
