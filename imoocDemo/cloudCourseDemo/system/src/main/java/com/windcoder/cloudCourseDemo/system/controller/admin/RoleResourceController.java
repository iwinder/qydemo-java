package com.windcoder.cloudCourseDemo.system.controller.admin;

import com.windcoder.cloudCourseDemo.server.domain.RoleResource;
import com.windcoder.cloudCourseDemo.server.dto.RoleResourceDto;
import com.windcoder.cloudCourseDemo.server.dto.PageDto;
import com.windcoder.cloudCourseDemo.server.dto.ResponseDto;
import com.windcoder.cloudCourseDemo.server.service.RoleResourceService;
import com.windcoder.cloudCourseDemo.server.utils.ValidatorUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/admin/roleResource")
@Slf4j
public class RoleResourceController {

    @Resource
    private RoleResourceService roleResourceService;

    public static final String BUSINESS_NAME = "角色资源关联";

    /**
     * 列表查询
     * @param pageDto
     * @return
     */
    @PostMapping("/list")
    public ResponseDto list(@RequestBody  PageDto pageDto) {
        roleResourceService.list(pageDto);
        ResponseDto responseDto = new ResponseDto(pageDto);
        return responseDto;
    }

    /**
     * 保存，id有值时更新，无值时新增
     * @param roleResourceDto
     * @return
     */
    @PostMapping("/save")
    public ResponseDto save(@RequestBody  RoleResourceDto roleResourceDto) {
        // 保存校验
        ValidatorUtil.require(roleResourceDto.getRoleId(), "角色");
        ValidatorUtil.require(roleResourceDto.getResourceId(), "资源");

        roleResourceService.save(roleResourceDto);
        ResponseDto responseDto = new ResponseDto(roleResourceDto);
        return responseDto;
    }

    /**
     * 删除
     * @param id
     * @return
     */
    @DeleteMapping("/delete/{id}")
    public ResponseDto delete(@PathVariable String id) {
        roleResourceService.delete(id);
        ResponseDto responseDto = new ResponseDto();
        return responseDto;
    }
}
