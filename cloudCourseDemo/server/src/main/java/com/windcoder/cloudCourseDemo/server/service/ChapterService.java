package com.windcoder.cloudCourseDemo.server.service;

import com.github.pagehelper.PageHelper;
import com.windcoder.cloudCourseDemo.server.domain.Chapter;
import com.windcoder.cloudCourseDemo.server.domain.ChapterExample;
import com.windcoder.cloudCourseDemo.server.dto.ChapterDto;
import com.windcoder.cloudCourseDemo.server.mapper.ChapterMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class ChapterService {
    @Resource
    private ChapterMapper chapterMapper;

    public List<ChapterDto> list() {
        PageHelper.startPage(1,1);
        ChapterExample testExample = new ChapterExample();
//        testExample.setOrderByClause("id ASC");
//        testExample.createCriteria().andIdEqualTo("1");
//        testExample.setOrderByClause("id DESC");
        List<Chapter> chapters = chapterMapper.selectByExample(testExample);
        List<ChapterDto> chapterDtoList = new ArrayList<ChapterDto>();
        for (Chapter chapter: chapters) {
            ChapterDto chapterDto = new ChapterDto();
            BeanUtils.copyProperties(chapter, chapterDto);
            chapterDtoList.add(chapterDto);
        }
        return chapterDtoList;
    }

}
