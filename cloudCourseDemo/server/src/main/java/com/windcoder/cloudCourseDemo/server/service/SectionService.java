package com.windcoder.cloudCourseDemo.server.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import com.windcoder.cloudCourseDemo.server.domain.Section;
import com.windcoder.cloudCourseDemo.server.domain.SectionExample;
import com.windcoder.cloudCourseDemo.server.dto.SectionDto;
import com.windcoder.cloudCourseDemo.server.dto.PageDto;
import com.windcoder.cloudCourseDemo.server.dto.SectionPageDto;
import com.windcoder.cloudCourseDemo.server.mapper.SectionMapper;
import com.windcoder.cloudCourseDemo.server.utils.CopyUtil;
import com.windcoder.cloudCourseDemo.server.utils.UuidUtil;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;
import java.util.Date;

@Service
public class SectionService {
    @Resource
    private SectionMapper sectionMapper;
    @Resource
    private CourseService courseService;

    /**
     * 列表查询
     * @param sectionPageDto
     */
    public void list(SectionPageDto sectionPageDto) {
        PageHelper.startPage(sectionPageDto.getPage(),sectionPageDto.getSize());
        SectionExample sectionExample = new SectionExample();
        SectionExample.Criteria criteria = sectionExample.createCriteria();
        if (!StringUtils.isEmpty(sectionPageDto.getCourseId())) {
            criteria.andCourseIdEqualTo(sectionPageDto.getCourseId());
        }
        if (!StringUtils.isEmpty(sectionPageDto.getChapterId())) {
            criteria.andChapterIdEqualTo(sectionPageDto.getChapterId());
        }
        sectionExample.setOrderByClause("sort asc");
        List<Section> sections = sectionMapper.selectByExample(sectionExample);
        PageInfo<Section> pageInfo = new PageInfo<>(sections);
        sectionPageDto.setTotal(pageInfo.getTotal());
        List<SectionDto> sectionDtoList = CopyUtil.copyList(sections, SectionDto.class);
        sectionPageDto.setList(sectionDtoList);
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
        courseService.updateTime(section.getCourseId());
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
        Date now = new Date();
        section.setCreatedAt(now);
        section.setUpdatedAt(now);
        section.setId(UuidUtil.getShortUuid());
        sectionMapper.insert(section);
    }

    /**
     * 更新
     * @param section
     */
    private void update(Section section){
        section.setUpdatedAt(new Date());
        sectionMapper.updateByPrimaryKey(section);
    }

}
