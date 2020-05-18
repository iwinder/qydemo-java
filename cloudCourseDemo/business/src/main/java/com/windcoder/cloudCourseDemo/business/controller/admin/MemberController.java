package com.windcoder.cloudCourseDemo.business.controller.admin;

import com.windcoder.cloudCourseDemo.server.domain.Member;
import com.windcoder.cloudCourseDemo.server.dto.MemberDto;
import com.windcoder.cloudCourseDemo.server.dto.PageDto;
import com.windcoder.cloudCourseDemo.server.dto.ResponseDto;
import com.windcoder.cloudCourseDemo.server.service.MemberService;
import com.windcoder.cloudCourseDemo.server.utils.ValidatorUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/admin/member")
@Slf4j
public class MemberController {

    @Resource
    private MemberService memberService;

    public static final String BUSINESS_NAME = "会员";

    /**
     * 列表查询
     * @param pageDto
     * @return
     */
    @PostMapping("/list")
    public ResponseDto list(@RequestBody  PageDto pageDto) {
        memberService.list(pageDto);
        ResponseDto responseDto = new ResponseDto(pageDto);
        return responseDto;
    }

    /**
     * 保存，id有值时更新，无值时新增
     * @param memberDto
     * @return
     */
    @PostMapping("/save")
    public ResponseDto save(@RequestBody  MemberDto memberDto) {
        // 保存校验
        ValidatorUtil.length(memberDto.getMobile(), "手机号", 1, 11);
        ValidatorUtil.require(memberDto.getPassword(), "密码");
        ValidatorUtil.length(memberDto.getName(), "昵称", 1, 50);
        ValidatorUtil.length(memberDto.getPhoto(), "头像url", 1, 200);

        memberService.save(memberDto);
        ResponseDto responseDto = new ResponseDto(memberDto);
        return responseDto;
    }

    /**
     * 删除
     * @param id
     * @return
     */
    @DeleteMapping("/delete/{id}")
    public ResponseDto delete(@PathVariable String id) {
        memberService.delete(id);
        ResponseDto responseDto = new ResponseDto();
        return responseDto;
    }
}
