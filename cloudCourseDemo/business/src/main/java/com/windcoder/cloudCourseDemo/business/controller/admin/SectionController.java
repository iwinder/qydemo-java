package com.windcoder.cloudCourseDemo.business.controller.admin;

import com.windcoder.cloudCourseDemo.server.domain.Section;
import com.windcoder.cloudCourseDemo.server.dto.SectionDto;
import com.windcoder.cloudCourseDemo.server.dto.PageDto;
import com.windcoder.cloudCourseDemo.server.dto.ResponseDto;
import com.windcoder.cloudCourseDemo.server.service.SectionService;
import com.windcoder.cloudCourseDemo.server.utils.ValidatorUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/admin/section")
@Slf4j
public class SectionController {

    @Resource
    private SectionService sectionService;

    public static final String BUSINESS_NAME = "小节";

    /**
     * 列表查询
     * @param pageDto
     * @return
     */
    @PostMapping("/list")
    public ResponseDto testA(@RequestBody  PageDto pageDto) {
        sectionService.list(pageDto);
        ResponseDto responseDto = new ResponseDto(pageDto);
        return responseDto;
    }

    /**
     * 保存，id有值时更新，无值时新增
     * @param sectionDto
     * @return
     */
    @PostMapping("/save")
    public ResponseDto save(@RequestBody  SectionDto sectionDto) {
        // 保存校验
        ValidatorUtil.require(sectionDto.getTitle(), "标题");
        ValidatorUtil.length(sectionDto.getTitle(), "标题", 1, 50);
        ValidatorUtil.length(sectionDto.getVideo(), "视频", 1, 200);

        sectionService.save(sectionDto);
        ResponseDto responseDto = new ResponseDto(sectionDto);
        return responseDto;
    }

    /**
     * 删除
     * @param id
     * @return
     */
    @DeleteMapping("/delete/{id}")
    public ResponseDto delete(@PathVariable String id) {
        sectionService.delete(id);
        ResponseDto responseDto = new ResponseDto();
        return responseDto;
    }
}
