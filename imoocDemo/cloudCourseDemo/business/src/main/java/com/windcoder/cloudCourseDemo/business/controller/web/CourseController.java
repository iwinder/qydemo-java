package com.windcoder.cloudCourseDemo.business.controller.web;

import com.windcoder.cloudCourseDemo.server.dto.CourseDto;
import com.windcoder.cloudCourseDemo.server.dto.CoursePageDto;
import com.windcoder.cloudCourseDemo.server.dto.PageDto;
import com.windcoder.cloudCourseDemo.server.dto.ResponseDto;
import com.windcoder.cloudCourseDemo.server.enums.CourseStatusEnum;
import com.windcoder.cloudCourseDemo.server.service.CourseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController("webCourseController")
@RequestMapping("/web/course")
@Slf4j
public class CourseController {

    public static final String BUSINESS_NAME = "课程";
    @Resource
    private CourseService courseService;

    /**
     * 列表查询，查询最新的3门已发布的课程
     * @return
     */
   @GetMapping("/list-new")
   public ResponseDto listNew() {
       PageDto pageDto = new PageDto();
       pageDto.setPage(1);
       pageDto.setSize(3);
       ResponseDto responseDto = new ResponseDto();
       List<CourseDto> courseDtoList = courseService.listNew(pageDto);
       responseDto.setContent(courseDtoList);
       return responseDto;
   }

    @PostMapping("/list")
    public ResponseDto list(@RequestBody CoursePageDto pageDto) {
        ResponseDto responseDto = new ResponseDto();
        pageDto.setStatus(CourseStatusEnum.PUBLISH.getCode());
        courseService.list(pageDto);
        responseDto.setContent(pageDto);
        return responseDto;
    }
    @GetMapping("/find/{id}")
    public ResponseDto findCourse(@PathVariable String id) {
        log.info("查找课程开始：{}", id);
        ResponseDto responseDto = new ResponseDto();
        CourseDto courseDto = courseService.findCourse(id);
        responseDto.setContent(courseDto);
        log.info("查找课程结束：{}", responseDto);
        return responseDto;
    }
}
