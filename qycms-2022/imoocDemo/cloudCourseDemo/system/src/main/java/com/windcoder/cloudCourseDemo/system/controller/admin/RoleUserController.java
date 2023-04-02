package com.windcoder.cloudCourseDemo.system.controller.admin;

import com.windcoder.cloudCourseDemo.server.domain.RoleUser;
import com.windcoder.cloudCourseDemo.server.dto.RoleUserDto;
import com.windcoder.cloudCourseDemo.server.dto.PageDto;
import com.windcoder.cloudCourseDemo.server.dto.ResponseDto;
import com.windcoder.cloudCourseDemo.server.service.RoleUserService;
import com.windcoder.cloudCourseDemo.server.utils.ValidatorUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/admin/roleUser")
@Slf4j
public class RoleUserController {

    @Resource
    private RoleUserService roleUserService;

    public static final String BUSINESS_NAME = "角色用户关联";

    /**
     * 列表查询
     * @param pageDto
     * @return
     */
    @PostMapping("/list")
    public ResponseDto list(@RequestBody  PageDto pageDto) {
        roleUserService.list(pageDto);
        ResponseDto responseDto = new ResponseDto(pageDto);
        return responseDto;
    }

    /**
     * 保存，id有值时更新，无值时新增
     * @param roleUserDto
     * @return
     */
    @PostMapping("/save")
    public ResponseDto save(@RequestBody  RoleUserDto roleUserDto) {
        // 保存校验
        ValidatorUtil.require(roleUserDto.getRoleId(), "角色");
        ValidatorUtil.require(roleUserDto.getUserId(), "用户");

        roleUserService.save(roleUserDto);
        ResponseDto responseDto = new ResponseDto(roleUserDto);
        return responseDto;
    }

    /**
     * 删除
     * @param id
     * @return
     */
    @DeleteMapping("/delete/{id}")
    public ResponseDto delete(@PathVariable String id) {
        roleUserService.delete(id);
        ResponseDto responseDto = new ResponseDto();
        return responseDto;
    }
}
