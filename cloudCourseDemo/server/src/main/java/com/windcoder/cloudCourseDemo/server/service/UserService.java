package com.windcoder.cloudCourseDemo.server.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import com.windcoder.cloudCourseDemo.server.domain.User;
import com.windcoder.cloudCourseDemo.server.domain.UserExample;
import com.windcoder.cloudCourseDemo.server.dto.LoginUserDto;
import com.windcoder.cloudCourseDemo.server.dto.UserDto;
import com.windcoder.cloudCourseDemo.server.dto.PageDto;
import com.windcoder.cloudCourseDemo.server.enums.BusinessExceptionCode;
import com.windcoder.cloudCourseDemo.server.exception.BusinessException;
import com.windcoder.cloudCourseDemo.server.mapper.UserMapper;
import com.windcoder.cloudCourseDemo.server.utils.CopyUtil;
import com.windcoder.cloudCourseDemo.server.utils.UuidUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;

@Service
@Slf4j
public class UserService {
    @Resource
    private UserMapper userMapper;

    /**
     * 列表查询
     * @param pageDto
     */
    public void list(PageDto pageDto) {
        PageHelper.startPage(pageDto.getPage(),pageDto.getSize());
        UserExample userExample = new UserExample();
        List<User> users = userMapper.selectByExample(userExample);
        PageInfo<User> pageInfo = new PageInfo<>(users);
        pageDto.setTotal(pageInfo.getTotal());
        List<UserDto> userDtoList = CopyUtil.copyList(users, UserDto.class);
        pageDto.setList(userDtoList);
    }


    /**
     * 保存，id有值时更新，无值时新增
     * @param userDto
     */
    public void save(UserDto userDto){
        User user = CopyUtil.copy(userDto, User.class);
        if (StringUtils.isEmpty(user.getId())) {
            this.inster(user);
        } else {
            this.update(user);
        }
    }

    /**
     * 删除
     * @param id
     */
    public void delete(String id) {
        userMapper.deleteByPrimaryKey(id);
    }

    /**
     * 新增
     * @param user
     */
    private void inster(User user){
        user.setId(UuidUtil.getShortUuid());
        User userDb = this.selectByLoginName(user.getLoginName());
        if (userDb != null) {
            throw new BusinessException(BusinessExceptionCode.USER_LOGIN_NAME_EXIST);
        }
        userMapper.insert(user);
    }

    /**
     * 更新
     * @param user
     */
    private void update(User user){
        user.setPassword(null);
        userMapper.updateByPrimaryKeySelective(user);
    }


    public User selectByLoginName(String loginName) {
        UserExample userExample = new UserExample();
        userExample.createCriteria().andLoginNameEqualTo(loginName);
        List<User> userList = userMapper.selectByExample(userExample);
        if (CollectionUtils.isEmpty(userList)) {
            return null;
        } else {
            return userList.get(0);
        }
    }

    /**
     * 重置密码
     * @param userDto
     */
    public void savePassword(UserDto userDto) {
        User user = new User();
        user.setId(userDto.getId());
        user.setPassword(userDto.getPassword());
        userMapper.updateByPrimaryKeySelective(user);
    }

    public LoginUserDto login(UserDto userDto) {
        User user = selectByLoginName(userDto.getLoginName());
        if (user == null) {
            log.info("用户名不存在, {}", userDto.getLoginName());
            throw new BusinessException(BusinessExceptionCode.LOGIN_ERROR);
        } else {
            if (user.getPassword().equals(userDto.getPassword())) {
                // 登录成功
                return CopyUtil.copy(user, LoginUserDto.class);
            } else {
                log.info("密码不对, 输入密码：{}, 数据库密码：{}", userDto.getPassword(), user.getPassword());
                throw new BusinessException(BusinessExceptionCode.LOGIN_ERROR);
            }
        }
    }

}
