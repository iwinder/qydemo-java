package com.windcoder.cloudCourseDemo.file.controller.admin;

import com.alibaba.fastjson.JSON;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.vod.model.v20170321.GetMezzanineInfoResponse;
import com.windcoder.cloudCourseDemo.server.dto.FileDto;
import com.windcoder.cloudCourseDemo.server.dto.ResponseDto;
import com.windcoder.cloudCourseDemo.server.enums.FileUseEnum;
import com.windcoder.cloudCourseDemo.server.service.FileService;
import com.windcoder.cloudCourseDemo.server.utils.Base64ToMultipartFile;
import com.windcoder.cloudCourseDemo.server.utils.UuidUtil;
import com.windcoder.cloudCourseDemo.server.utils.VodUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.StringUtils;
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
    @Value("${file.domain}")
    private String FILE_DOMAIN;
    @Value("${file.vod.accessKeyId}")
    private String accessKeyId;
    @Value("${file.vod.accessKeySecret}")
    private String accessKeySecret;
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

        String path = new StringBuffer(dir)
                .append(File.separator)
                .append(key)
                .append(".")
                .append(suffix).toString(); // course\6sfSqfOwzmik4A4icMYuUe.mp4
        String localPath = new StringBuffer(path)
                .append(".")
                .append(fileDto.getShardIndex()).toString(); // course\6sfSqfOwzmik4A4icMYuUe.mp4.1
        String fullPath = FILE_PATH + localPath;
        File dest = new File(fullPath);
        shard.transferTo(dest);
        log.info(dest.getAbsolutePath());

        log.info("保存文件记录开始");
        fileDto.setPath(path);
        fileService.saveBigFile(fileDto);

        ResponseDto responseDto = new ResponseDto();
        responseDto.setContent(fileDto);

        // 合并
        if (fileDto.getShardIndex().equals(fileDto.getShardTotal())) {
            this.merge(fileDto);
        }
        return responseDto;
    }

    private void merge(FileDto fileDto) {
        log.info("合并分片开始");
        String path = fileDto.getPath();
        Integer shardTotal = fileDto.getShardTotal();
        File newFile = new File(FILE_PATH + path);
        byte[] byt = new byte[10 * 1024 * 1024];
        FileInputStream inputStream = null;   // 分片文件
        int len;

        // 文件追加写入
        try (FileOutputStream outputStream = new FileOutputStream(newFile, true);
              ) {
            for (int i = 0; i < shardTotal; i++) {
                // 读取第一个分片
                inputStream = new FileInputStream(new File(FILE_PATH + path + "." + (i+1))); // course\6sfSqfOwzmik4A4icMYuUe.mp4.1
                while ((len = inputStream.read(byt))!=-1) {
                    outputStream.write(byt, 0, len);
                }
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
        log.error("合并分片结束");

        System.gc();
        // 删除分片
        log.info("删除分片开始");
        for (int i = 0; i < shardTotal; i++) {
            String filePath = FILE_PATH + path + "." + (i + 1);
            File file = new File(filePath);
            boolean result = file.delete();
            log.info("删除{}，{}", filePath, result ? "成功" : "失败");
        }
        log.info("删除分片结束");
    }

    @GetMapping("/check/{key}")
    public ResponseDto check(@PathVariable String key) throws Exception {
        log.info("检测上传分片开始：{}}", key);
        ResponseDto responseDto = new ResponseDto();
        FileDto fileDto = fileService.findByKey(key);
        if (null != fileDto) {
            if (StringUtils.isEmpty(fileDto.getVod())) {
                fileDto.setPath(FILE_DOMAIN + fileDto.getPath());
            } else {
                DefaultAcsClient vodClient = VodUtil.initVodClient(accessKeyId, accessKeySecret);
                GetMezzanineInfoResponse response = VodUtil.getMezzanineInfo(vodClient, fileDto.getVod());
                System.out.println("获取视频信息, response : " + JSON.toJSONString(response));
                String fileUrl = response.getMezzanine().getFileURL();
                fileDto.setPath(fileUrl);
            }
        }
        responseDto.setContent(fileDto);
        return responseDto;
    }

}
