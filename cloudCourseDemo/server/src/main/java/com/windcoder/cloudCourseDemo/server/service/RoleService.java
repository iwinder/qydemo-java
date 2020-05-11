package com.windcoder.cloudCourseDemo.server.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import com.windcoder.cloudCourseDemo.server.domain.Role;
import com.windcoder.cloudCourseDemo.server.domain.RoleExample;
import com.windcoder.cloudCourseDemo.server.domain.RoleResource;
import com.windcoder.cloudCourseDemo.server.domain.RoleResourceExample;
import com.windcoder.cloudCourseDemo.server.dto.RoleDto;
import com.windcoder.cloudCourseDemo.server.dto.PageDto;
import com.windcoder.cloudCourseDemo.server.mapper.RoleMapper;
import com.windcoder.cloudCourseDemo.server.mapper.RoleResourceMapper;
import com.windcoder.cloudCourseDemo.server.utils.CopyUtil;
import com.windcoder.cloudCourseDemo.server.utils.UuidUtil;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;

@Service
public class RoleService {
    @Resource
    private RoleMapper roleMapper;
    @Resource
    private RoleResourceMapper roleResourceMapper;

    /**
     * 列表查询
     * @param pageDto
     */
    public void list(PageDto pageDto) {
        PageHelper.startPage(pageDto.getPage(),pageDto.getSize());
        RoleExample roleExample = new RoleExample();
        List<Role> roles = roleMapper.selectByExample(roleExample);
        PageInfo<Role> pageInfo = new PageInfo<>(roles);
        pageDto.setTotal(pageInfo.getTotal());
        List<RoleDto> roleDtoList = CopyUtil.copyList(roles, RoleDto.class);
        pageDto.setList(roleDtoList);
    }


    /**
     * 保存，id有值时更新，无值时新增
     * @param roleDto
     */
    public void save(RoleDto roleDto){
        Role role = CopyUtil.copy(roleDto, Role.class);
        if (StringUtils.isEmpty(role.getId())) {
            this.inster(role);
        } else {
            this.update(role);
        }
    }

    /**
     * 删除
     * @param id
     */
    public void delete(String id) {
        roleMapper.deleteByPrimaryKey(id);
    }

    /**
     * 新增
     * @param role
     */
    private void inster(Role role){
        role.setId(UuidUtil.getShortUuid());
        roleMapper.insert(role);
    }

    /**
     * 更新
     * @param role
     */
    private void update(Role role){
        roleMapper.updateByPrimaryKey(role);
    }


    /**
     * 按角色保存资源
     * @param roleDto
     */
    public void saveResource(RoleDto roleDto) {
          String roleId = roleDto.getId();
          List<String> resourceIds = roleDto.getResourceIds();
          // 清空库中所有的当前角色下的记录
          RoleResourceExample example = new RoleResourceExample();
          example.createCriteria().andRoleIdEqualTo(roleId);
          roleResourceMapper.deleteByExample(example);

          // 保存角色资源
          for (int i = 0; i < resourceIds.size(); i++) {
                  RoleResource roleResource = new RoleResource();
                  roleResource.setId(UuidUtil.getShortUuid());
                  roleResource.setRoleId(roleId);
                  roleResource.setResourceId(resourceIds.get(i));
                  roleResourceMapper.insert(roleResource);
          }
    }


}
