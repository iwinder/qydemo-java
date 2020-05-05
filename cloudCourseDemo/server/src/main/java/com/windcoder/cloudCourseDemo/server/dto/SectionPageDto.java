package com.windcoder.cloudCourseDemo.server.dto;


import lombok.Data;

@Data
public class SectionPageDto extends PageDto {
    private String courseId;
    private String chapterId;
}
