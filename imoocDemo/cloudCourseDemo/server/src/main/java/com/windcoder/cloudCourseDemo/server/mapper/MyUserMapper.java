package com.windcoder.cloudCourseDemo.server.mapper;

import com.windcoder.cloudCourseDemo.server.dto.ResourceDto;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MyUserMapper {

    List<ResourceDto> findResources(@Param("userId") String userId);
}
