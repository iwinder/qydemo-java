package com.windcoder.cloudCourseDemo.business.controller.admin;

import com.windcoder.cloudCourseDemo.server.domain.Course;
import com.windcoder.cloudCourseDemo.server.dto.*;
import com.windcoder.cloudCourseDemo.server.service.CourseCategoryService;
import com.windcoder.cloudCourseDemo.server.service.CourseService;
import com.windcoder.cloudCourseDemo.server.utils.ValidatorUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/admin/course")
@Slf4j
public class CourseController {

    @Resource
    private CourseService courseService;
    @Resource
    private CourseCategoryService courseCategoryService;
    public static final String BUSINESS_NAME = "课程";

    /**
     * 列表查询
     * @param pageDto
     * @return
     */
    @PostMapping("/list")
    public ResponseDto list(@RequestBody  PageDto pageDto) {
        courseService.list(pageDto);
        ResponseDto responseDto = new ResponseDto(pageDto);
        return responseDto;
    }

    /**
     * 保存，id有值时更新，无值时新增
     * @param courseDto
     * @return
     */
    @PostMapping("/save")
    public ResponseDto save(@RequestBody  CourseDto courseDto) {
        // 保存校验
        ValidatorUtil.require(courseDto.getName(), "名称");
        ValidatorUtil.length(courseDto.getName(), "名称", 1, 50);
        ValidatorUtil.length(courseDto.getSummary(), "概述", 1, 2000);
        ValidatorUtil.length(courseDto.getImage(), "封面", 1, 100);

        courseService.save(courseDto);
        ResponseDto responseDto = new ResponseDto(courseDto);
        return responseDto;
    }

    /**
     * 删除
     * @param id
     * @return
     */
    @DeleteMapping("/delete/{id}")
    public ResponseDto delete(@PathVariable String id) {
        courseService.delete(id);
        ResponseDto responseDto = new ResponseDto();
        return responseDto;
    }


    /**
     * 查找课程下所有分类
     * @param courseId
     * @return
     */
     @PostMapping("/list-category/{courseId}")
   public ResponseDto listCategory(@PathVariable(value = "courseId") String courseId) {
        ResponseDto responseDto = new ResponseDto();
        List<CourseCategoryDto> dtoList = courseCategoryService.listByCourse(courseId);
        responseDto.setContent(dtoList);
        return responseDto;
    }


    @GetMapping("/find-content/{id}")
   public ResponseDto findContent(@PathVariable String id) {
       ResponseDto responseDto = new ResponseDto();
       CourseContentDto contentDto = courseService.findContent(id);
       responseDto.setContent(contentDto);
       return responseDto;
   }

   @PostMapping("/save-content")
   public ResponseDto saveContent(@RequestBody CourseContentDto contentDto) {
       ResponseDto responseDto = new ResponseDto();
       courseService.saveContent(contentDto);
       return responseDto;
   }

    @PostMapping("/sort")
    public ResponseDto sort(@RequestBody SortDto sortDto) {
         log.info("更新排序");
        ResponseDto responseDto = new ResponseDto();
        courseService.sort(sortDto);
        return responseDto;
    }
}
