package com.windcoder.cloudCourseDemo.file.controller.admin;

import com.windcoder.cloudCourseDemo.server.dto.ResponseDto;
import com.windcoder.cloudCourseDemo.server.utils.UuidUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@RestController
@RequestMapping("/admin")
@Slf4j
public class UploadController {
    public static final String BUSINESS_NAME = "文件上传";

    @PostMapping("/upload")
    public ResponseDto upload(@RequestParam MultipartFile file) throws IOException {
        log.info("上传文件开始：{}", file);
        log.info(file.getOriginalFilename());
        log.info(String.valueOf(file.getSize()));

        String fileName = file.getOriginalFilename();
        String key = UuidUtil.getShortUuid();
        String fullPath = "E:/00Work/Program/data/imooc/teacher/" + key + "-" + fileName;
        File dest = new File(fullPath);
        file.transferTo(dest);
        log.info(dest.getAbsolutePath());
        ResponseDto responseDto = new ResponseDto();
        return responseDto;
    }

}
