package com.windcoder.cloudCourseDemo.server.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import com.windcoder.cloudCourseDemo.server.domain.RoleUser;
import com.windcoder.cloudCourseDemo.server.domain.RoleUserExample;
import com.windcoder.cloudCourseDemo.server.dto.RoleUserDto;
import com.windcoder.cloudCourseDemo.server.dto.PageDto;
import com.windcoder.cloudCourseDemo.server.mapper.RoleUserMapper;
import com.windcoder.cloudCourseDemo.server.utils.CopyUtil;
import com.windcoder.cloudCourseDemo.server.utils.UuidUtil;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;

@Service
public class RoleUserService {
    @Resource
    private RoleUserMapper roleUserMapper;

    /**
     * 列表查询
     * @param pageDto
     */
    public void list(PageDto pageDto) {
        PageHelper.startPage(pageDto.getPage(),pageDto.getSize());
        RoleUserExample roleUserExample = new RoleUserExample();
        List<RoleUser> roleUsers = roleUserMapper.selectByExample(roleUserExample);
        PageInfo<RoleUser> pageInfo = new PageInfo<>(roleUsers);
        pageDto.setTotal(pageInfo.getTotal());
        List<RoleUserDto> roleUserDtoList = CopyUtil.copyList(roleUsers, RoleUserDto.class);
        pageDto.setList(roleUserDtoList);
    }


    /**
     * 保存，id有值时更新，无值时新增
     * @param roleUserDto
     */
    public void save(RoleUserDto roleUserDto){
        RoleUser roleUser = CopyUtil.copy(roleUserDto, RoleUser.class);
        if (StringUtils.isEmpty(roleUser.getId())) {
            this.inster(roleUser);
        } else {
            this.update(roleUser);
        }
    }

    /**
     * 删除
     * @param id
     */
    public void delete(String id) {
        roleUserMapper.deleteByPrimaryKey(id);
    }

    /**
     * 新增
     * @param roleUser
     */
    private void inster(RoleUser roleUser){
        roleUser.setId(UuidUtil.getShortUuid());
        roleUserMapper.insert(roleUser);
    }

    /**
     * 更新
     * @param roleUser
     */
    private void update(RoleUser roleUser){
        roleUserMapper.updateByPrimaryKey(roleUser);
    }

}
