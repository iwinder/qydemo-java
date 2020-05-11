package com.windcoder.cloudCourseDemo.server.service;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import com.windcoder.cloudCourseDemo.server.domain.Resource;
import com.windcoder.cloudCourseDemo.server.domain.ResourceExample;
import com.windcoder.cloudCourseDemo.server.dto.ResourceDto;
import com.windcoder.cloudCourseDemo.server.dto.PageDto;
import com.windcoder.cloudCourseDemo.server.mapper.ResourceMapper;
import com.windcoder.cloudCourseDemo.server.utils.CopyUtil;
import com.windcoder.cloudCourseDemo.server.utils.UuidUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;


import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
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
//        resource.setId(UuidUtil.getShortUuid());
        resourceMapper.insert(resource);
    }

    /**
     * 更新
     * @param resource
     */
    private void update(Resource resource){
        resourceMapper.updateByPrimaryKey(resource);
    }

    /**
     * 保存资源树
     * @param json
     */
    public void saveJson(String json) {
        List<ResourceDto> jsonList = JSON.parseArray(json, ResourceDto.class);
        List<ResourceDto> list = new ArrayList<>();
        if (!CollectionUtils.isEmpty(jsonList)) {
            for (ResourceDto d: jsonList) {
                d.setPage("");
                add(list, d);
            }
        }
        log.info("共{}条", list.size());
        resourceMapper.deleteByExample(null);
        for (int i = 0,l=list.size(); i < l; i++) {
            this.inster(CopyUtil.copy(list.get(i), Resource.class));
        }
    }

    /**
     * 递归，将树型结构的节点全部取出来，放到list
     * @param list
     * @param dto
     */
    private void add(List<ResourceDto> list,ResourceDto dto) {
        list.add(dto);
        if (!CollectionUtils.isEmpty(dto.getChildren())) {
            for (ResourceDto d: dto.getChildren()) {
                d.setParent(dto.getId());
                add(list, d);
            }
        }
    }

    public List<ResourceDto> loadTree(){
        ResourceExample example = new ResourceExample();
        example.setOrderByClause("id asc");
        List<Resource> resourceList = resourceMapper.selectByExample(example);
        List<ResourceDto> resourceDtoList = CopyUtil.copyList(resourceList, ResourceDto.class);
        for (int i = resourceDtoList.size()-1; i >= 0; i--) {
            // 当前要移动的记录
            ResourceDto child = resourceDtoList.get(i);
            // 如果当前节点没有父节点，则不用往下了
            if (StringUtils.isEmpty(child.getParent())) {
                continue;
            }
            // 查找父节点
            for (int j = i;j >= 0; j--) {
                ResourceDto parent = resourceDtoList.get(j);
                if (child.getParent().equals(parent.getId())) {
                    if (CollectionUtils.isEmpty(parent.getChildren())) {
                        parent.setChildren(new ArrayList<>());
                    }
                    // 添加到最前面，否则会变成倒叙，因为循环是从后向前循环的
                    parent.getChildren().add(0, child);

                    // 子节点找到父节点后，删除列表中的子节点
                    resourceDtoList.remove(child);
                }
            }
        }
        return resourceDtoList;
    }


}
