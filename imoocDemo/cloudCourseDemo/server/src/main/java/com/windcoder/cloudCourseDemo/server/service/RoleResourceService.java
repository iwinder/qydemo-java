package com.windcoder.cloudCourseDemo.server.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import com.windcoder.cloudCourseDemo.server.domain.RoleResource;
import com.windcoder.cloudCourseDemo.server.domain.RoleResourceExample;
import com.windcoder.cloudCourseDemo.server.dto.RoleResourceDto;
import com.windcoder.cloudCourseDemo.server.dto.PageDto;
import com.windcoder.cloudCourseDemo.server.mapper.RoleResourceMapper;
import com.windcoder.cloudCourseDemo.server.utils.CopyUtil;
import com.windcoder.cloudCourseDemo.server.utils.UuidUtil;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;

@Service
public class RoleResourceService {
    @Resource
    private RoleResourceMapper roleResourceMapper;

    /**
     * 列表查询
     * @param pageDto
     */
    public void list(PageDto pageDto) {
        PageHelper.startPage(pageDto.getPage(),pageDto.getSize());
        RoleResourceExample roleResourceExample = new RoleResourceExample();
        List<RoleResource> roleResources = roleResourceMapper.selectByExample(roleResourceExample);
        PageInfo<RoleResource> pageInfo = new PageInfo<>(roleResources);
        pageDto.setTotal(pageInfo.getTotal());
        List<RoleResourceDto> roleResourceDtoList = CopyUtil.copyList(roleResources, RoleResourceDto.class);
        pageDto.setList(roleResourceDtoList);
    }


    /**
     * 保存，id有值时更新，无值时新增
     * @param roleResourceDto
     */
    public void save(RoleResourceDto roleResourceDto){
        RoleResource roleResource = CopyUtil.copy(roleResourceDto, RoleResource.class);
        if (StringUtils.isEmpty(roleResource.getId())) {
            this.inster(roleResource);
        } else {
            this.update(roleResource);
        }
    }

    /**
     * 删除
     * @param id
     */
    public void delete(String id) {
        roleResourceMapper.deleteByPrimaryKey(id);
    }

    /**
     * 新增
     * @param roleResource
     */
    private void inster(RoleResource roleResource){
        roleResource.setId(UuidUtil.getShortUuid());
        roleResourceMapper.insert(roleResource);
    }

    /**
     * 更新
     * @param roleResource
     */
    private void update(RoleResource roleResource){
        roleResourceMapper.updateByPrimaryKey(roleResource);
    }

}
