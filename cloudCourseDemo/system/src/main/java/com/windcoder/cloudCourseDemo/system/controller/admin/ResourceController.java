package com.windcoder.cloudCourseDemo.system.controller.admin;

import com.windcoder.cloudCourseDemo.server.domain.Resource;
import com.windcoder.cloudCourseDemo.server.dto.ResourceDto;
import com.windcoder.cloudCourseDemo.server.dto.PageDto;
import com.windcoder.cloudCourseDemo.server.dto.ResponseDto;
import com.windcoder.cloudCourseDemo.server.service.ResourceService;
import com.windcoder.cloudCourseDemo.server.utils.ValidatorUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/admin/resource")
@Slf4j
public class ResourceController {

    @javax.annotation.Resource
    private ResourceService resourceService;

    public static final String BUSINESS_NAME = "资源";

    /**
     * 列表查询
     * @param pageDto
     * @return
     */
    @PostMapping("/list")
    public ResponseDto list(@RequestBody  PageDto pageDto) {
        resourceService.list(pageDto);
        ResponseDto responseDto = new ResponseDto(pageDto);
        return responseDto;
    }

    /**
     * 保存，id有值时更新，无值时新增
     * @param resourceDto
     * @return
     */
    @PostMapping("/save")
    public ResponseDto save(@RequestBody  String jsonStr) {
        // 保存校验
        ValidatorUtil.require(jsonStr, "资源");

        resourceService.saveJson(jsonStr);
        ResponseDto responseDto = new ResponseDto();
        return responseDto;
    }

    /**
     * 删除
     * @param id
     * @return
     */
    @DeleteMapping("/delete/{id}")
    public ResponseDto delete(@PathVariable String id) {
        resourceService.delete(id);
        ResponseDto responseDto = new ResponseDto();
        return responseDto;
    }
}
