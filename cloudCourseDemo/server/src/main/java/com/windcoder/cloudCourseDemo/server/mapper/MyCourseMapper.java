package com.windcoder.cloudCourseDemo.server.mapper;

import org.apache.ibatis.annotations.Param;

public interface MyCourseMapper {
    int updateTime(@Param("courseId") String courseId);
}
