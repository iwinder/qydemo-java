package com.windcoder.cloudCourseDemo.server.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import com.windcoder.cloudCourseDemo.server.domain.CourseContentFile;
import com.windcoder.cloudCourseDemo.server.domain.CourseContentFileExample;
import com.windcoder.cloudCourseDemo.server.dto.CourseContentFileDto;
import com.windcoder.cloudCourseDemo.server.dto.PageDto;
import com.windcoder.cloudCourseDemo.server.mapper.CourseContentFileMapper;
import com.windcoder.cloudCourseDemo.server.utils.CopyUtil;
import com.windcoder.cloudCourseDemo.server.utils.UuidUtil;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;

@Service
public class CourseContentFileService {
    @Resource
    private CourseContentFileMapper courseContentFileMapper;

    /**
     * 列表查询
     * @param courseId
     * @return
     */
    public List<CourseContentFileDto> list(String courseId) {
        CourseContentFileExample courseContentFileExample = new CourseContentFileExample();
        courseContentFileExample.createCriteria().andCourseIdEqualTo(courseId);
        List<CourseContentFile> courseContentFiles = courseContentFileMapper.selectByExample(courseContentFileExample);
        PageInfo<CourseContentFile> pageInfo = new PageInfo<>(courseContentFiles);

        return CopyUtil.copyList(courseContentFiles, CourseContentFileDto.class);
    }


    /**
     * 保存，id有值时更新，无值时新增
     * @param courseContentFileDto
     */
    public void save(CourseContentFileDto courseContentFileDto){
        CourseContentFile courseContentFile = CopyUtil.copy(courseContentFileDto, CourseContentFile.class);
        if (StringUtils.isEmpty(courseContentFile.getId())) {
            this.inster(courseContentFile);
        } else {
            this.update(courseContentFile);
        }
    }

    /**
     * 删除
     * @param id
     */
    public void delete(String id) {
        courseContentFileMapper.deleteByPrimaryKey(id);
    }

    /**
     * 新增
     * @param courseContentFile
     */
    private void inster(CourseContentFile courseContentFile){
        courseContentFile.setId(UuidUtil.getShortUuid());
        courseContentFileMapper.insert(courseContentFile);
    }

    /**
     * 更新
     * @param courseContentFile
     */
    private void update(CourseContentFile courseContentFile){
        courseContentFileMapper.updateByPrimaryKey(courseContentFile);
    }

}
