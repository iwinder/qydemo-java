package com.windcoder.cloudCourseDemo.server.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import com.windcoder.cloudCourseDemo.server.domain.Section;
import com.windcoder.cloudCourseDemo.server.domain.SectionExample;
import com.windcoder.cloudCourseDemo.server.dto.SectionDto;
import com.windcoder.cloudCourseDemo.server.dto.PageDto;
import com.windcoder.cloudCourseDemo.server.mapper.SectionMapper;
import com.windcoder.cloudCourseDemo.server.utils.CopyUtil;
import com.windcoder.cloudCourseDemo.server.utils.UuidUtil;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;

@Service
public class SectionService {
    @Resource
    private SectionMapper sectionMapper;

    /**
     * 列表查询
     * @param pageDto
     */
    public void list(PageDto pageDto) {
        PageHelper.startPage(pageDto.getPage(),pageDto.getSize());
        SectionExample testExample = new SectionExample();
        List<Section> sections = sectionMapper.selectByExample(testExample);
        PageInfo<Section> pageInfo = new PageInfo<>(sections);
        pageDto.setTotal(pageInfo.getTotal());
        List<SectionDto> sectionDtoList = CopyUtil.copyList(sections, SectionDto.class);
        pageDto.setList(sectionDtoList);
    }


    /**
     * 保存，id有值时更新，无值时新增
     * @param sectionDto
     */
    public void save(SectionDto sectionDto){
        Section section = CopyUtil.copy(sectionDto, Section.class);
        if (StringUtils.isEmpty(section.getId())) {
            this.inster(section);
        } else {
            this.update(section);
        }
    }

    /**
     * 删除
     * @param id
     */
    public void delete(String id) {
        sectionMapper.deleteByPrimaryKey(id);
    }

    /**
     * 新增
     * @param section
     */
    private void inster(Section section){
        section.setId(UuidUtil.getShortUuid());
        sectionMapper.insert(section);
    }

    /**
     * 更新
     * @param section
     */
    private void update(Section section){
        sectionMapper.updateByPrimaryKey(section);
    }

}
