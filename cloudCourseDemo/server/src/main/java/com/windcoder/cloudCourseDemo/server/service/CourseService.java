package com.windcoder.cloudCourseDemo.server.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import com.windcoder.cloudCourseDemo.server.domain.Course;
import com.windcoder.cloudCourseDemo.server.domain.CourseContent;
import com.windcoder.cloudCourseDemo.server.domain.CourseExample;
import com.windcoder.cloudCourseDemo.server.dto.*;
import com.windcoder.cloudCourseDemo.server.enums.CourseStatusEnum;
import com.windcoder.cloudCourseDemo.server.mapper.CourseContentMapper;
import com.windcoder.cloudCourseDemo.server.mapper.CourseMapper;
import com.windcoder.cloudCourseDemo.server.mapper.MyCourseMapper;
import com.windcoder.cloudCourseDemo.server.utils.CopyUtil;
import com.windcoder.cloudCourseDemo.server.utils.UuidUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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
    @Resource
    private CourseCategoryService courseCategoryService;
    @Resource
    private CourseContentMapper courseContentMapper;

    /**
     * 列表查询
     * @param pageDto
     */
    public void list(CoursePageDto pageDto) {
        PageHelper.startPage(pageDto.getPage(),pageDto.getSize());
        CourseExample courseExample = new CourseExample();
        CourseExample.Criteria criteria = courseExample.createCriteria();
        if (!StringUtils.isEmpty(pageDto.getStatus())) {
            criteria.andStatusEqualTo(pageDto.getStatus());
        }
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
    @Transactional
    public void save(CourseDto courseDto){
        Course course = CopyUtil.copy(courseDto, Course.class);
        if (StringUtils.isEmpty(course.getId())) {
            this.inster(course);
        } else {
            this.update(course);
        }
        // 批量保存课程分类
        courseCategoryService.saveBatch(course.getId(), courseDto.getCategorys());
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

    /**
     * 查找课程内容
     * @param id
     * @return
     */
    public CourseContentDto findContent(String id) {
        CourseContent content = courseContentMapper.selectByPrimaryKey(id);
        if (null == content) {
            return null;
        }
        return CopyUtil.copy(content, CourseContentDto.class);
    }

    /**
     *  保存课程内容，包含新增和修改
     * @param contentDto
     * @return
     */
    public int saveContent(CourseContentDto contentDto) {
        CourseContent content = CopyUtil.copy(contentDto, CourseContent.class);
        int i = courseContentMapper.updateByPrimaryKeyWithBLOBs(content);
        if (i == 0) {
            i = courseContentMapper.insert(content);
        }
        return i;
    }

    public void sort(SortDto sortDto) {
        // 修改当前记录的排序
        myCourseMapper.updateSort(sortDto);
        // 如果排序值变大
        if (sortDto.getNewSort() > sortDto.getOldSort()) {
            myCourseMapper.moveSortsForward(sortDto);
        }
        // 如果排序值变小
        if (sortDto.getNewSort() < sortDto.getOldSort()) {
            myCourseMapper.moveSortsBackward(sortDto);
        }
    }

    /**
     * 新课列表查询，只查询已发布的，按创建日期倒序
     * @param pageDto
     * @return
     */
    public List<CourseDto> listNew(PageDto pageDto) {
        PageHelper.startPage(pageDto.getPage(), pageDto.getSize());
        CourseExample courseExample = new CourseExample();
        courseExample.createCriteria().andStatusEqualTo(CourseStatusEnum.PUBLISH.getCode());
        courseExample.setOrderByClause("created_at desc");
        List<Course> courseList = courseMapper.selectByExample(courseExample);
        return CopyUtil.copyList(courseList, CourseDto.class);
    }
}
