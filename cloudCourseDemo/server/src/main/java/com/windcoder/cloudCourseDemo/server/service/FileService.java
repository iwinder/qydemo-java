package com.windcoder.cloudCourseDemo.server.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import com.windcoder.cloudCourseDemo.server.domain.File;
import com.windcoder.cloudCourseDemo.server.domain.FileExample;
import com.windcoder.cloudCourseDemo.server.dto.FileDto;
import com.windcoder.cloudCourseDemo.server.dto.PageDto;
import com.windcoder.cloudCourseDemo.server.mapper.FileMapper;
import com.windcoder.cloudCourseDemo.server.utils.CopyUtil;
import com.windcoder.cloudCourseDemo.server.utils.UuidUtil;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;
import java.util.Date;

@Service
public class FileService {
    @Resource
    private FileMapper fileMapper;

    /**
     * 列表查询
     * @param pageDto
     */
    public void list(PageDto pageDto) {
        PageHelper.startPage(pageDto.getPage(),pageDto.getSize());
        FileExample fileExample = new FileExample();
        List<File> files = fileMapper.selectByExample(fileExample);
        PageInfo<File> pageInfo = new PageInfo<>(files);
        pageDto.setTotal(pageInfo.getTotal());
        List<FileDto> fileDtoList = CopyUtil.copyList(files, FileDto.class);
        pageDto.setList(fileDtoList);
    }


    /**
     * 保存，id有值时更新，无值时新增
     * @param fileDto
     */
    public void save(FileDto fileDto){
        File file = CopyUtil.copy(fileDto, File.class);
        if (StringUtils.isEmpty(file.getId())) {
            this.inster(file);
        } else {
            this.update(file);
        }
    }

    public void saveBigFile(FileDto fileDto) {
        File fileDb = selectByKey(fileDto.getKey());
        if (null == fileDb) {
            File file = CopyUtil.copy(fileDto, File.class);
            this.inster(file);
        } else {
            fileDb.setShardIndex(fileDto.getShardIndex());
            this.update(fileDb);
        }
    }

    /**
     * 删除
     * @param id
     */
    public void delete(String id) {
        fileMapper.deleteByPrimaryKey(id);
    }

    /**
     * 新增
     * @param file
     */
    private void inster(File file){
        Date now = new Date();
        file.setCreatedAt(now);
        file.setUpdatedAt(now);
        file.setId(UuidUtil.getShortUuid());
        fileMapper.insert(file);
    }

    /**
     * 更新
     * @param file
     */
    private void update(File file){
        file.setUpdatedAt(new Date());
        fileMapper.updateByPrimaryKey(file);
    }

    /**
     * 根据key获取File
     * @param key
     * @return
     */
    private File selectByKey(String key) {
        FileExample example = new FileExample();
        example.createCriteria().andKeyEqualTo(key);
        List<File> fileList = fileMapper.selectByExample(example);
        if (CollectionUtils.isEmpty(fileList)) {
            return null;
        } else {
            return fileList.get(0);
        }
    }

}
