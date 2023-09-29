package com.windCoder.SSMDemo.services;

import com.windCoder.SSMDemo.entity.User;
import com.windCoder.common.utills.ReturnResult;

/**
 * Description:
 * User: wind
 * Date: 2017-08-01
 * Time: 18:14 下午
 */
public interface IUserService {
    public ReturnResult addUser(User user);
    public ReturnResult findAllUserInterface();

}
