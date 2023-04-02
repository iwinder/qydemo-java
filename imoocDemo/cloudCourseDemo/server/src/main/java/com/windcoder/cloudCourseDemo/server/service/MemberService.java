package com.windcoder.cloudCourseDemo.server.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import com.windcoder.cloudCourseDemo.server.domain.Member;
import com.windcoder.cloudCourseDemo.server.domain.MemberExample;
import com.windcoder.cloudCourseDemo.server.dto.MemberDto;
import com.windcoder.cloudCourseDemo.server.dto.PageDto;
import com.windcoder.cloudCourseDemo.server.mapper.MemberMapper;
import com.windcoder.cloudCourseDemo.server.utils.CopyUtil;
import com.windcoder.cloudCourseDemo.server.utils.UuidUtil;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;
import java.util.Date;

@Service
public class MemberService {
    @Resource
    private MemberMapper memberMapper;

    /**
     * 列表查询
     * @param pageDto
     */
    public void list(PageDto pageDto) {
        PageHelper.startPage(pageDto.getPage(),pageDto.getSize());
        MemberExample memberExample = new MemberExample();
        List<Member> members = memberMapper.selectByExample(memberExample);
        PageInfo<Member> pageInfo = new PageInfo<>(members);
        pageDto.setTotal(pageInfo.getTotal());
        List<MemberDto> memberDtoList = CopyUtil.copyList(members, MemberDto.class);
        pageDto.setList(memberDtoList);
    }


    /**
     * 保存，id有值时更新，无值时新增
     * @param memberDto
     */
    public void save(MemberDto memberDto){
        Member member = CopyUtil.copy(memberDto, Member.class);
        if (StringUtils.isEmpty(member.getId())) {
            this.inster(member);
        } else {
            this.update(member);
        }
    }

    /**
     * 删除
     * @param id
     */
    public void delete(String id) {
        memberMapper.deleteByPrimaryKey(id);
    }

    /**
     * 新增
     * @param member
     */
    private void inster(Member member){
        Date now = new Date();
        member.setId(UuidUtil.getShortUuid());
        member.setRegisterTime(now);
        memberMapper.insert(member);
    }

    /**
     * 更新
     * @param member
     */
    private void update(Member member){
        memberMapper.updateByPrimaryKey(member);
    }

}
