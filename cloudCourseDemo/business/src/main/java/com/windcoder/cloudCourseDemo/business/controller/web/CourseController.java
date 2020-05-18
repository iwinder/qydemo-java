package com.windcoder.cloudCourseDemo.business.controller.web;

import com.windcoder.cloudCourseDemo.server.dto.CourseDto;
import com.windcoder.cloudCourseDemo.server.dto.PageDto;
import com.windcoder.cloudCourseDemo.server.dto.ResponseDto;
import com.windcoder.cloudCourseDemo.server.service.CourseService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController("webCourseController")
@RequestMapping("/web/course")
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
}
