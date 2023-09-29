package com.windCoder.SSMDemo.services.impl;

import com.windCoder.SSMDemo.entity.User;
import com.windCoder.SSMDemo.services.IUserService;
import com.windCoder.common.utills.DateUtilZ;
import com.windCoder.common.utills.ReturnResult;
import org.json.JSONArray;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Description:
 * User: wind
 * Date: 2017-08-01
 * Time: 18:14 下午
 */
@Service("userService")
public class UserServiceImpl implements IUserService{
    @Autowired
    private SqlSessionTemplate sqlSessionTemplate;

    public ReturnResult addUser(User user){
        ReturnResult r = new ReturnResult();
        user.setCreatetime(DateUtilZ.getTimestampOfNow());
        Integer bol = sqlSessionTemplate.insert("User.addUser",user);
        r.setCode(bol);
        r.setMsg("addCode");
        return r;
    }

    public List<User> findAllUser(){
        List<User> users =  sqlSessionTemplate.selectList("User.findAllUser");
        if (users==null){
            users = new ArrayList<User>();
        }
        return users;
    }

    public ReturnResult findAllUserInterface(){
        ReturnResult r = new ReturnResult();
        List<User> users =findAllUser();
        JSONArray j = new JSONArray(users);
        r.setCode(1);
        r.setMsg("findAllUserInterface");
        r.setResult(j);
        return r;
    }

}
