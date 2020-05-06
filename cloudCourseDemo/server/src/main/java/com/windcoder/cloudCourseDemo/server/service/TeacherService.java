package com.windcoder.cloudCourseDemo.server.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import com.windcoder.cloudCourseDemo.server.domain.Teacher;
import com.windcoder.cloudCourseDemo.server.domain.TeacherExample;
import com.windcoder.cloudCourseDemo.server.dto.TeacherDto;
import com.windcoder.cloudCourseDemo.server.dto.PageDto;
import com.windcoder.cloudCourseDemo.server.mapper.TeacherMapper;
import com.windcoder.cloudCourseDemo.server.utils.CopyUtil;
import com.windcoder.cloudCourseDemo.server.utils.UuidUtil;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;

@Service
public class TeacherService {
    @Resource
    private TeacherMapper teacherMapper;

    /**
     * 列表查询
     * @param pageDto
     */
    public void list(PageDto pageDto) {
        PageHelper.startPage(pageDto.getPage(),pageDto.getSize());
        TeacherExample teacherExample = new TeacherExample();
        List<Teacher> teachers = teacherMapper.selectByExample(teacherExample);
        PageInfo<Teacher> pageInfo = new PageInfo<>(teachers);
        pageDto.setTotal(pageInfo.getTotal());
        List<TeacherDto> teacherDtoList = CopyUtil.copyList(teachers, TeacherDto.class);
        pageDto.setList(teacherDtoList);
    }


    /**
     * 保存，id有值时更新，无值时新增
     * @param teacherDto
     */
    public void save(TeacherDto teacherDto){
        Teacher teacher = CopyUtil.copy(teacherDto, Teacher.class);
        if (StringUtils.isEmpty(teacher.getId())) {
            this.inster(teacher);
        } else {
            this.update(teacher);
        }
    }

    /**
     * 删除
     * @param id
     */
    public void delete(String id) {
        teacherMapper.deleteByPrimaryKey(id);
    }

    /**
     * 新增
     * @param teacher
     */
    private void inster(Teacher teacher){
        teacher.setId(UuidUtil.getShortUuid());
        teacherMapper.insert(teacher);
    }

    /**
     * 更新
     * @param teacher
     */
    private void update(Teacher teacher){
        teacherMapper.updateByPrimaryKey(teacher);
    }

}
