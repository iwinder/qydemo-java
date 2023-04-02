package com.windcoder.cloudCourseDemo.business.controller.admin;

import com.windcoder.cloudCourseDemo.server.domain.Teacher;
import com.windcoder.cloudCourseDemo.server.dto.TeacherDto;
import com.windcoder.cloudCourseDemo.server.dto.PageDto;
import com.windcoder.cloudCourseDemo.server.dto.ResponseDto;
import com.windcoder.cloudCourseDemo.server.service.TeacherService;
import com.windcoder.cloudCourseDemo.server.utils.ValidatorUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/admin/teacher")
@Slf4j
public class TeacherController {

    @Resource
    private TeacherService teacherService;

    public static final String BUSINESS_NAME = "讲师";


    /**
     +     * 列表查询
     +     */
     @PostMapping("/all")
     public ResponseDto all() {
         ResponseDto responseDto = new ResponseDto();
         List<TeacherDto> teacherDtoList = teacherService.all();
         responseDto.setContent(teacherDtoList);
         return responseDto;
     }

    /**
     * 列表查询
     * @param pageDto
     * @return
     */
    @PostMapping("/list")
    public ResponseDto list(@RequestBody  PageDto pageDto) {
        teacherService.list(pageDto);
        ResponseDto responseDto = new ResponseDto(pageDto);
        return responseDto;
    }

    /**
     * 保存，id有值时更新，无值时新增
     * @param teacherDto
     * @return
     */
    @PostMapping("/save")
    public ResponseDto save(@RequestBody  TeacherDto teacherDto) {
        // 保存校验
        ValidatorUtil.require(teacherDto.getName(), "姓名");
        ValidatorUtil.length(teacherDto.getName(), "姓名", 1, 50);
        ValidatorUtil.length(teacherDto.getNickname(), "昵称", 1, 50);
        ValidatorUtil.length(teacherDto.getImage(), "头像", 1, 100);
        ValidatorUtil.length(teacherDto.getPosition(), "职位", 1, 50);
        ValidatorUtil.length(teacherDto.getMotto(), "座右铭", 1, 50);
        ValidatorUtil.length(teacherDto.getIntro(), "简介", 1, 500);

        teacherService.save(teacherDto);
        ResponseDto responseDto = new ResponseDto(teacherDto);
        return responseDto;
    }

    /**
     * 删除
     * @param id
     * @return
     */
    @DeleteMapping("/delete/{id}")
    public ResponseDto delete(@PathVariable String id) {
        teacherService.delete(id);
        ResponseDto responseDto = new ResponseDto();
        return responseDto;
    }
}
