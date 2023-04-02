package com.windcoder.thinking.in.spring.bean.definition.factory;

import com.windcoder.thinking.in.spring.ioc.overview.domain.User;

/**
 * {@ User} 工厂类
 */
public class UserFacotry {

    public User createUser() {
        return User.createUser();
    }
}
