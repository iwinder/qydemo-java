package com.windcoder.cloudCourseDemo.file.controller.admin;

import com.windcoder.cloudCourseDemo.server.dto.FileDto;
import com.windcoder.cloudCourseDemo.server.dto.ResponseDto;
import com.windcoder.cloudCourseDemo.server.enums.FileUseEnum;
import com.windcoder.cloudCourseDemo.server.service.FileService;
import com.windcoder.cloudCourseDemo.server.utils.Base64ToMultipartFile;
import com.windcoder.cloudCourseDemo.server.utils.UuidUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.*;

@RestController
@RequestMapping("/admin")
@Slf4j
public class UploadController {
    public static final String BUSINESS_NAME = "文件上传";

    @Value("${file.path}")
    private String FILE_PATH;
    @Resource
    private FileService fileService;

    /**
     * 普通文件上传
     * @param file
     * @param use
     * @return
     * @throws IOException
     */
    @PostMapping("/upload")
    public ResponseDto upload(@RequestParam MultipartFile file, String use) throws IOException {
        log.info("上传文件开始");

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

    @PostMapping("/big-upload")
    public ResponseDto uploadOfMerge(@RequestBody FileDto fileDto) throws IOException {
        log.info("上传文件开始");

        // 保存文件到本地
        String use = fileDto.getUse();
        String key = fileDto.getKey();
        String suffix = fileDto.getSuffix();
        String shardBase64 = fileDto.getShard();
        MultipartFile shard = Base64ToMultipartFile.base64ToMultipart(fileDto.getShard());

        // 保存文件到本地
        FileUseEnum useEnum = FileUseEnum.getByCode(use);
            // 若文件夹不存在则创建
        String dir = useEnum.name().toLowerCase();
        File fullDir = new File(FILE_PATH + dir);
        if (!fullDir.exists()) {
            fullDir.mkdir();
        }

        String path = dir + File.separator + key + "." + suffix;
        String fullPath = FILE_PATH + path;
        File dest = new File(fullPath);
        shard.transferTo(dest);
        log.info(dest.getAbsolutePath());

        log.info("保存文件记录开始");
        fileDto.setPath(path);
        fileService.saveBigFile(fileDto);

        ResponseDto responseDto = new ResponseDto();
        responseDto.setContent(fileDto);
        return responseDto;
    }
    /**
     * 分片合并测试
     */
    @GetMapping("/merge")
    public void merge() {
        File newFile = new File(FILE_PATH + "/course/test123.mp4");
        byte[] byt = new byte[10 * 1024 * 1024];
        FileInputStream inputStream = null;
        int len;

        // 文件追加写入
        try (FileOutputStream outputStream = new FileOutputStream(newFile, true);
              ) {
            // 读取第一个分片
            inputStream = new FileInputStream(new File(FILE_PATH + "/course/NtZBZajp.blob"));
            while ((len = inputStream.read(byt))!=-1) {
                outputStream.write(byt, 0, len);
            }
            // 读取第二个分片
            inputStream = new FileInputStream(new File(FILE_PATH + "course/RzYnC43R.blob"));
            while ((len = inputStream.read(byt))!=-1) {
                outputStream.write(byt, 0, len);
            }

        } catch (FileNotFoundException e) {
            log.info("文件寻找异常", e);
        } catch (IOException e) {
            log.info("分片合并异常", e);
        } finally {
            try {
                if(inputStream !=null) {
                    inputStream.close();
                }
                log.info("IO流关闭");
            } catch (IOException e) {
                log.error("IO流关闭", e);
            }

        }



    }

}
