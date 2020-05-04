package com.windcoder.cloudCourseDemo.business.controller.admin;

import com.windcoder.cloudCourseDemo.server.domain.Chapter;
import com.windcoder.cloudCourseDemo.server.dto.ChapterDto;
import com.windcoder.cloudCourseDemo.server.dto.PageDto;
import com.windcoder.cloudCourseDemo.server.service.ChapterService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/admin/chapter")
@Slf4j
public class ChapterController {

    @Resource
    private ChapterService chapterService;

    @PostMapping("/list")
    public PageDto testA(@RequestBody  PageDto pageDto) {
        chapterService.list(pageDto);
        return pageDto;
    }

    @PostMapping("/save")
    public ChapterDto save(@RequestBody  ChapterDto chapterDto) {
        log.info("Save ChapterDto：{}", chapterDto);
        chapterService.save(chapterDto);
        return chapterDto;
    }
}
