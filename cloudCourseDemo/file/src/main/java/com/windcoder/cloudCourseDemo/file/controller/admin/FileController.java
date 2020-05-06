package com.windcoder.cloudCourseDemo.file.controller.admin;

import com.windcoder.cloudCourseDemo.server.domain.File;
import com.windcoder.cloudCourseDemo.server.dto.FileDto;
import com.windcoder.cloudCourseDemo.server.dto.PageDto;
import com.windcoder.cloudCourseDemo.server.dto.ResponseDto;
import com.windcoder.cloudCourseDemo.server.service.FileService;
import com.windcoder.cloudCourseDemo.server.utils.ValidatorUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/admin/file")
@Slf4j
public class FileController {

    @Resource
    private FileService fileService;

    public static final String BUSINESS_NAME = "文件";

    /**
     * 列表查询
     * @param pageDto
     * @return
     */
    @PostMapping("/list")
    public ResponseDto list(@RequestBody  PageDto pageDto) {
        fileService.list(pageDto);
        ResponseDto responseDto = new ResponseDto(pageDto);
        return responseDto;
    }




}
