package com.windcoder.cloudCourseDemo.server.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import com.windcoder.cloudCourseDemo.server.domain.Resource;
import com.windcoder.cloudCourseDemo.server.domain.ResourceExample;
import com.windcoder.cloudCourseDemo.server.dto.ResourceDto;
import com.windcoder.cloudCourseDemo.server.dto.PageDto;
import com.windcoder.cloudCourseDemo.server.mapper.ResourceMapper;
import com.windcoder.cloudCourseDemo.server.utils.CopyUtil;
import com.windcoder.cloudCourseDemo.server.utils.UuidUtil;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;


import java.util.List;

@Service
public class ResourceService {
    @javax.annotation.Resource
    private ResourceMapper resourceMapper;

    /**
     * 列表查询
     * @param pageDto
     */
    public void list(PageDto pageDto) {
        PageHelper.startPage(pageDto.getPage(),pageDto.getSize());
        ResourceExample resourceExample = new ResourceExample();
        List<Resource> resources = resourceMapper.selectByExample(resourceExample);
        PageInfo<Resource> pageInfo = new PageInfo<>(resources);
        pageDto.setTotal(pageInfo.getTotal());
        List<ResourceDto> resourceDtoList = CopyUtil.copyList(resources, ResourceDto.class);
        pageDto.setList(resourceDtoList);
    }


    /**
     * 保存，id有值时更新，无值时新增
     * @param resourceDto
     */
    public void save(ResourceDto resourceDto){
        Resource resource = CopyUtil.copy(resourceDto, Resource.class);
        if (StringUtils.isEmpty(resource.getId())) {
            this.inster(resource);
        } else {
            this.update(resource);
        }
    }

    /**
     * 删除
     * @param id
     */
    public void delete(String id) {
        resourceMapper.deleteByPrimaryKey(id);
    }

    /**
     * 新增
     * @param resource
     */
    private void inster(Resource resource){
        resource.setId(UuidUtil.getShortUuid());
        resourceMapper.insert(resource);
    }

    /**
     * 更新
     * @param resource
     */
    private void update(Resource resource){
        resourceMapper.updateByPrimaryKey(resource);
    }

}
