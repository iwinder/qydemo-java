package com.windcoder.cloudCourseDemo.file.controller.admin;

import com.windcoder.cloudCourseDemo.server.dto.FileDto;
import com.windcoder.cloudCourseDemo.server.dto.ResponseDto;
import com.windcoder.cloudCourseDemo.server.enums.FileUseEnum;
import com.windcoder.cloudCourseDemo.server.service.FileService;
import com.windcoder.cloudCourseDemo.server.utils.UuidUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;

@RestController
@RequestMapping("/admin")
@Slf4j
public class UploadController {
    public static final String BUSINESS_NAME = "文件上传";

    @Value("${file.path}")
    private String FILE_PATH;
    @Resource
    private FileService fileService;

    @PostMapping("/upload")
    public ResponseDto upload(@RequestParam MultipartFile file, String use) throws IOException {
        log.info("上传文件开始");
        log.info(file.getOriginalFilename());
        log.info(String.valueOf(file.getSize()));

        // 保存文件到本地
        FileUseEnum useEnum = FileUseEnum.getByCode(use);
        String fileName = file.getOriginalFilename();
        String key = UuidUtil.getShortUuid();
        String suffix = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();

        // 若文件夹不存在则创建
        String dir = useEnum.name().toLowerCase();
        File fullDir = new File(FILE_PATH + dir);
        if (!fullDir.exists()) {
            fullDir.mkdir();
        }

        String path = dir + File.separator + key + "." + suffix;
        String fullPath = FILE_PATH + path;
        File dest = new File(fullPath);
        file.transferTo(dest);
        log.info(dest.getAbsolutePath());

        log.info("保存文件记录开始");
        FileDto fileDto = new FileDto();
        fileDto.setName(fileName);
        fileDto.setPath(path);
        fileDto.setSize(Math.toIntExact(file.getSize()));
        fileDto.setSuffix(suffix);
        fileDto.setUse(use);
        fileService.save(fileDto);

        ResponseDto responseDto = new ResponseDto();
        responseDto.setContent(fileDto);
        return responseDto;
    }

}
