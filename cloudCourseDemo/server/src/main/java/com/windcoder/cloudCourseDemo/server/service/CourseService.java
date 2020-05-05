package com.windcoder.cloudCourseDemo.server.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import com.windcoder.cloudCourseDemo.server.domain.Course;
import com.windcoder.cloudCourseDemo.server.domain.CourseExample;
import com.windcoder.cloudCourseDemo.server.dto.CourseDto;
import com.windcoder.cloudCourseDemo.server.dto.PageDto;
import com.windcoder.cloudCourseDemo.server.mapper.CourseMapper;
import com.windcoder.cloudCourseDemo.server.mapper.MyCourseMapper;
import com.windcoder.cloudCourseDemo.server.utils.CopyUtil;
import com.windcoder.cloudCourseDemo.server.utils.UuidUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;
import java.util.Date;

@Service
@Slf4j
public class CourseService {
    @Resource
    private CourseMapper courseMapper;
    @Resource
    private MyCourseMapper myCourseMapper;

    /**
     * 列表查询
     * @param pageDto
     */
    public void list(PageDto pageDto) {
        PageHelper.startPage(pageDto.getPage(),pageDto.getSize());
        CourseExample courseExample = new CourseExample();
         courseExample.setOrderByClause("sort asc");
        List<Course> courses = courseMapper.selectByExample(courseExample);
        PageInfo<Course> pageInfo = new PageInfo<>(courses);
        pageDto.setTotal(pageInfo.getTotal());
        List<CourseDto> courseDtoList = CopyUtil.copyList(courses, CourseDto.class);
        pageDto.setList(courseDtoList);
    }


    /**
     * 保存，id有值时更新，无值时新增
     * @param courseDto
     */
    public void save(CourseDto courseDto){
        Course course = CopyUtil.copy(courseDto, Course.class);
        if (StringUtils.isEmpty(course.getId())) {
            this.inster(course);
        } else {
            this.update(course);
        }
    }

    /**
     * 删除
     * @param id
     */
    public void delete(String id) {
        courseMapper.deleteByPrimaryKey(id);
    }

    /**
     * 新增
     * @param course
     */
    private void inster(Course course){
        Date now = new Date();
        course.setCreatedAt(now);
        course.setUpdatedAt(now);
        course.setId(UuidUtil.getShortUuid());
        courseMapper.insert(course);
    }

    /**
     * 更新
     * @param course
     */
    private void update(Course course){
        course.setUpdatedAt(new Date());
        courseMapper.updateByPrimaryKey(course);
    }

    /**
     * 更新课程时长
     * @param courseId
     */
    public void updateTime(String courseId) {
        log.info("更新课程时长：{}", courseId);
        myCourseMapper.updateTime(courseId);
    }

}
