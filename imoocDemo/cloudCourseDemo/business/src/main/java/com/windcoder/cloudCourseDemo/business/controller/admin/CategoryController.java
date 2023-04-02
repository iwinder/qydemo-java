package com.windcoder.cloudCourseDemo.business.controller.admin;

import com.windcoder.cloudCourseDemo.server.domain.Category;
import com.windcoder.cloudCourseDemo.server.dto.CategoryDto;
import com.windcoder.cloudCourseDemo.server.dto.PageDto;
import com.windcoder.cloudCourseDemo.server.dto.ResponseDto;
import com.windcoder.cloudCourseDemo.server.service.CategoryService;
import com.windcoder.cloudCourseDemo.server.utils.ValidatorUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/admin/category")
@Slf4j
public class CategoryController {

    @Resource
    private CategoryService categoryService;

    public static final String BUSINESS_NAME = "分类";


    @PostMapping("/all")
    public ResponseDto all() {
        List<CategoryDto> categoryDtoList = categoryService.all();
        ResponseDto responseDto = new ResponseDto(categoryDtoList);
        return responseDto;
    }

    /**
     * 列表查询
     * @param pageDto
     * @return
     */
    @PostMapping("/list")
    public ResponseDto list(@RequestBody  PageDto pageDto) {
        categoryService.list(pageDto);
        ResponseDto responseDto = new ResponseDto(pageDto);
        return responseDto;
    }

    /**
     * 保存，id有值时更新，无值时新增
     * @param categoryDto
     * @return
     */
    @PostMapping("/save")
    public ResponseDto save(@RequestBody  CategoryDto categoryDto) {
        // 保存校验
        ValidatorUtil.require(categoryDto.getParent(), "父id");
        ValidatorUtil.require(categoryDto.getName(), "名称");
        ValidatorUtil.length(categoryDto.getName(), "名称", 1, 50);

        categoryService.save(categoryDto);
        ResponseDto responseDto = new ResponseDto(categoryDto);
        return responseDto;
    }

    /**
     * 删除
     * @param id
     * @return
     */
    @DeleteMapping("/delete/{id}")
    public ResponseDto delete(@PathVariable String id) {
        categoryService.delete(id);
        ResponseDto responseDto = new ResponseDto();
        return responseDto;
    }
}
