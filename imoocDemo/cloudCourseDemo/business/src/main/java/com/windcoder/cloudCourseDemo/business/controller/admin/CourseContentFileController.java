package com.windcoder.cloudCourseDemo.business.controller.admin;

import com.windcoder.cloudCourseDemo.server.domain.CourseContentFile;
import com.windcoder.cloudCourseDemo.server.dto.CourseContentFileDto;
import com.windcoder.cloudCourseDemo.server.dto.PageDto;
import com.windcoder.cloudCourseDemo.server.dto.ResponseDto;
import com.windcoder.cloudCourseDemo.server.service.CourseContentFileService;
import com.windcoder.cloudCourseDemo.server.utils.ValidatorUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/admin/course-content-file")
@Slf4j
public class CourseContentFileController {

    @Resource
    private CourseContentFileService courseContentFileService;

    public static final String BUSINESS_NAME = "课程内容文件";

    /**
     * 列表查询
     * @param
     * @return
     */
    @GetMapping("/list/{courseId}")
    public ResponseDto list(@PathVariable String courseId) {
        List<CourseContentFileDto> dtos =  courseContentFileService.list(courseId);
        ResponseDto responseDto = new ResponseDto(dtos);
        return responseDto;
    }

    /**
     * 保存，id有值时更新，无值时新增
     * @param courseContentFileDto
     * @return
     */
    @PostMapping("/save")
    public ResponseDto save(@RequestBody  CourseContentFileDto courseContentFileDto) {
        // 保存校验
        ValidatorUtil.require(courseContentFileDto.getCourseId(), "课程id");
        ValidatorUtil.length(courseContentFileDto.getUrl(), "地址", 1, 100);
        ValidatorUtil.length(courseContentFileDto.getName(), "文件名", 1, 100);

        courseContentFileService.save(courseContentFileDto);
        ResponseDto responseDto = new ResponseDto(courseContentFileDto);
        return responseDto;
    }

    /**
     * 删除
     * @param id
     * @return
     */
    @DeleteMapping("/delete/{id}")
    public ResponseDto delete(@PathVariable String id) {
        courseContentFileService.delete(id);
        ResponseDto responseDto = new ResponseDto();
        return responseDto;
    }
}
