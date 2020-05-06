package com.windcoder.cloudCourseDemo.server.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import com.windcoder.cloudCourseDemo.server.domain.CourseCategory;
import com.windcoder.cloudCourseDemo.server.domain.CourseCategoryExample;
import com.windcoder.cloudCourseDemo.server.dto.CategoryDto;
import com.windcoder.cloudCourseDemo.server.dto.CourseCategoryDto;
import com.windcoder.cloudCourseDemo.server.dto.PageDto;
import com.windcoder.cloudCourseDemo.server.mapper.CourseCategoryMapper;
import com.windcoder.cloudCourseDemo.server.utils.CopyUtil;
import com.windcoder.cloudCourseDemo.server.utils.UuidUtil;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;

@Service
public class CourseCategoryService {
    @Resource
    private CourseCategoryMapper courseCategoryMapper;

    /**
     * 列表查询
     * @param pageDto
     */
    public void list(PageDto pageDto) {
        PageHelper.startPage(pageDto.getPage(),pageDto.getSize());
        CourseCategoryExample courseCategoryExample = new CourseCategoryExample();
        List<CourseCategory> courseCategorys = courseCategoryMapper.selectByExample(courseCategoryExample);
        PageInfo<CourseCategory> pageInfo = new PageInfo<>(courseCategorys);
        pageDto.setTotal(pageInfo.getTotal());
        List<CourseCategoryDto> courseCategoryDtoList = CopyUtil.copyList(courseCategorys, CourseCategoryDto.class);
        pageDto.setList(courseCategoryDtoList);
    }


    /**
     * 保存，id有值时更新，无值时新增
     * @param courseCategoryDto
     */
    public void save(CourseCategoryDto courseCategoryDto){
        CourseCategory courseCategory = CopyUtil.copy(courseCategoryDto, CourseCategory.class);
        if (StringUtils.isEmpty(courseCategory.getId())) {
            this.inster(courseCategory);
        } else {
            this.update(courseCategory);
        }
    }

    /**
     * 删除
     * @param id
     */
    public void delete(String id) {
        courseCategoryMapper.deleteByPrimaryKey(id);
    }

    /**
     * 新增
     * @param courseCategory
     */
    private void inster(CourseCategory courseCategory){
        courseCategory.setId(UuidUtil.getShortUuid());
        courseCategoryMapper.insert(courseCategory);
    }

    /**
     * 更新
     * @param courseCategory
     */
    private void update(CourseCategory courseCategory){
        courseCategoryMapper.updateByPrimaryKey(courseCategory);
    }

    public void saveBatch(String courseId, List<CategoryDto> dtoList) {
        CourseCategoryExample example = new CourseCategoryExample();
        example.createCriteria().andCourseIdEqualTo(courseId);
        courseCategoryMapper.deleteByExample(example);
        for (CategoryDto categoryDto: dtoList) {
            CourseCategory courseCategory = new CourseCategory();
            courseCategory.setId(UuidUtil.getShortUuid());
            courseCategory.setCourseId(courseId);
            courseCategory.setCategoryId(categoryDto.getId());
            inster(courseCategory);
        }
    }
}
