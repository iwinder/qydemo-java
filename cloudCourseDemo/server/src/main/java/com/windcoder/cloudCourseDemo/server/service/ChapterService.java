package com.windcoder.cloudCourseDemo.server.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.windcoder.cloudCourseDemo.server.domain.Chapter;
import com.windcoder.cloudCourseDemo.server.domain.ChapterExample;
import com.windcoder.cloudCourseDemo.server.dto.ChapterDto;
import com.windcoder.cloudCourseDemo.server.dto.PageDto;
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

    public void list(PageDto pageDto) {
        PageHelper.startPage(pageDto.getPage(),pageDto.getSize());
        ChapterExample testExample = new ChapterExample();
//        testExample.setOrderByClause("id ASC");
//        testExample.createCriteria().andIdEqualTo("1");
//        testExample.setOrderByClause("id DESC");
        List<Chapter> chapters = chapterMapper.selectByExample(testExample);
        PageInfo<Chapter> pageInfo = new PageInfo<>(chapters);
        pageDto.setTotal(pageInfo.getTotal());
        List<ChapterDto> chapterDtoList = new ArrayList<ChapterDto>();
        for (Chapter chapter: chapters) {
            ChapterDto chapterDto = new ChapterDto();
            BeanUtils.copyProperties(chapter, chapterDto);
            chapterDtoList.add(chapterDto);
        }
        pageDto.setList(chapterDtoList);
    }

}
