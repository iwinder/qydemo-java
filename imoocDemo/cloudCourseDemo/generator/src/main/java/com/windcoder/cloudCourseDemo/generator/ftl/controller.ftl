package com.windcoder.cloudCourseDemo.${module}.controller.admin;

import com.windcoder.cloudCourseDemo.server.domain.${Domain};
import com.windcoder.cloudCourseDemo.server.dto.${Domain}Dto;
import com.windcoder.cloudCourseDemo.server.dto.PageDto;
import com.windcoder.cloudCourseDemo.server.dto.ResponseDto;
import com.windcoder.cloudCourseDemo.server.service.${Domain}Service;
import com.windcoder.cloudCourseDemo.server.utils.ValidatorUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/admin/${domain}")
@Slf4j
public class ${Domain}Controller {

    @Resource
    private ${Domain}Service ${domain}Service;

    public static final String BUSINESS_NAME = "${tableNameCn}";

    /**
     * 列表查询
     * @param pageDto
     * @return
     */
    @PostMapping("/list")
    public ResponseDto list(@RequestBody  PageDto pageDto) {
        ${domain}Service.list(pageDto);
        ResponseDto responseDto = new ResponseDto(pageDto);
        return responseDto;
    }

    /**
     * 保存，id有值时更新，无值时新增
     * @param ${domain}Dto
     * @return
     */
    @PostMapping("/save")
    public ResponseDto save(@RequestBody  ${Domain}Dto ${domain}Dto) {
        // 保存校验
        <#list fieldList as field>
            <#if field.name !="id" && field.nameHump!="createdAt" && field.nameHump!="updatedAt" && field.nameHump!="sort" >
            <#if !field.nullAble>
        ValidatorUtil.require(${domain}Dto.get${field.nameBigHump}(), "${field.nameCn}");
            </#if>
            <#if (field.length > 0)>
        ValidatorUtil.length(${domain}Dto.get${field.nameBigHump}(), "${field.nameCn}", 1, ${field.length?c});
            </#if>
            </#if>
        </#list>

        ${domain}Service.save(${domain}Dto);
        ResponseDto responseDto = new ResponseDto(${domain}Dto);
        return responseDto;
    }

    /**
     * 删除
     * @param id
     * @return
     */
    @DeleteMapping("/delete/{id}")
    public ResponseDto delete(@PathVariable String id) {
        ${domain}Service.delete(id);
        ResponseDto responseDto = new ResponseDto();
        return responseDto;
    }
}
