package com.windcoder.cloudCourseDemo.server.mapper;

import com.windcoder.cloudCourseDemo.server.dto.CourseDto;
import com.windcoder.cloudCourseDemo.server.dto.CoursePageDto;
import com.windcoder.cloudCourseDemo.server.dto.SortDto;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MyCourseMapper {
    int updateTime(@Param("courseId") String courseId);
    int updateSort(SortDto sortDto);
    int moveSortsBackward(SortDto sortDto);
    int moveSortsForward(SortDto sortDto);
    List<CourseDto> list(@Param("pageDto") CoursePageDto pageDto);
}
