package com.windcoder.cloudCourseDemo.system.controller.admin;

import com.windcoder.cloudCourseDemo.server.domain.Role;
import com.windcoder.cloudCourseDemo.server.dto.RoleDto;
import com.windcoder.cloudCourseDemo.server.dto.PageDto;
import com.windcoder.cloudCourseDemo.server.dto.ResponseDto;
import com.windcoder.cloudCourseDemo.server.service.RoleService;
import com.windcoder.cloudCourseDemo.server.utils.ValidatorUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/admin/role")
@Slf4j
public class RoleController {

    @Resource
    private RoleService roleService;

    public static final String BUSINESS_NAME = "角色";

    /**
     * 列表查询
     * @param pageDto
     * @return
     */
    @PostMapping("/list")
    public ResponseDto list(@RequestBody  PageDto pageDto) {
        roleService.list(pageDto);
        ResponseDto responseDto = new ResponseDto(pageDto);
        return responseDto;
    }

    /**
     * 保存，id有值时更新，无值时新增
     * @param roleDto
     * @return
     */
    @PostMapping("/save")
    public ResponseDto save(@RequestBody  RoleDto roleDto) {
        // 保存校验
        ValidatorUtil.require(roleDto.getName(), "角色");
        ValidatorUtil.length(roleDto.getName(), "角色", 1, 50);
        ValidatorUtil.require(roleDto.getDesc(), "描述");
        ValidatorUtil.length(roleDto.getDesc(), "描述", 1, 100);

        roleService.save(roleDto);
        ResponseDto responseDto = new ResponseDto(roleDto);
        return responseDto;
    }

    /**
     * 删除
     * @param id
     * @return
     */
    @DeleteMapping("/delete/{id}")
    public ResponseDto delete(@PathVariable String id) {
        roleService.delete(id);
        ResponseDto responseDto = new ResponseDto();
        return responseDto;
    }
}
