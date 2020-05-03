package com.windcoder.cloudCourseDemo.business.controller.admin;

import com.windcoder.cloudCourseDemo.server.domain.Chapter;
import com.windcoder.cloudCourseDemo.server.dto.ChapterDto;
import com.windcoder.cloudCourseDemo.server.dto.PageDto;
import com.windcoder.cloudCourseDemo.server.service.ChapterService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/admin/chapter")
public class ChapterController {

    @Resource
    private ChapterService chapterService;

    @PostMapping("/list")
    public PageDto testA(@RequestBody  PageDto pageDto) {
        chapterService.list(pageDto);
        return pageDto;
    }
}
