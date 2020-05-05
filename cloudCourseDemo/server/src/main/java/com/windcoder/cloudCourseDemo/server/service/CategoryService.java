package com.windcoder.cloudCourseDemo.server.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import com.windcoder.cloudCourseDemo.server.domain.Category;
import com.windcoder.cloudCourseDemo.server.domain.CategoryExample;
import com.windcoder.cloudCourseDemo.server.dto.CategoryDto;
import com.windcoder.cloudCourseDemo.server.dto.PageDto;
import com.windcoder.cloudCourseDemo.server.mapper.CategoryMapper;
import com.windcoder.cloudCourseDemo.server.utils.CopyUtil;
import com.windcoder.cloudCourseDemo.server.utils.UuidUtil;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;

@Service
public class CategoryService {
    @Resource
    private CategoryMapper categoryMapper;


    /**
     * 列表查询-无分页
     * @return
     */
    public List<CategoryDto> all() {
        CategoryExample categoryExample = new CategoryExample();
        categoryExample.setOrderByClause("sort asc");
        List<Category> categorys = categoryMapper.selectByExample(categoryExample);
        List<CategoryDto> categoryDtoList = CopyUtil.copyList(categorys, CategoryDto.class);
        return categoryDtoList;
    }

    /**
     * 列表查询
     * @param pageDto
     */
    public void list(PageDto pageDto) {
        PageHelper.startPage(pageDto.getPage(),pageDto.getSize());
        CategoryExample categoryExample = new CategoryExample();
         categoryExample.setOrderByClause("sort asc");
        List<Category> categorys = categoryMapper.selectByExample(categoryExample);
        PageInfo<Category> pageInfo = new PageInfo<>(categorys);
        pageDto.setTotal(pageInfo.getTotal());
        List<CategoryDto> categoryDtoList = CopyUtil.copyList(categorys, CategoryDto.class);
        pageDto.setList(categoryDtoList);
    }


    /**
     * 保存，id有值时更新，无值时新增
     * @param categoryDto
     */
    public void save(CategoryDto categoryDto){
        Category category = CopyUtil.copy(categoryDto, Category.class);
        if (StringUtils.isEmpty(category.getId())) {
            this.inster(category);
        } else {
            this.update(category);
        }
    }

    /**
     * 删除
     * @param id
     */
    public void delete(String id) {
        categoryMapper.deleteByPrimaryKey(id);
    }

    /**
     * 新增
     * @param category
     */
    private void inster(Category category){
        category.setId(UuidUtil.getShortUuid());
        categoryMapper.insert(category);
    }

    /**
     * 更新
     * @param category
     */
    private void update(Category category){
        categoryMapper.updateByPrimaryKey(category);
    }

}
