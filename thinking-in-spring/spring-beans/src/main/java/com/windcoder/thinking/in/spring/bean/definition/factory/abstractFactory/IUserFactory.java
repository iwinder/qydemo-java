package com.windcoder.thinking.in.spring.bean.definition.factory.abstractFactory;

import com.windcoder.thinking.in.spring.ioc.overview.domain.User;

/**
 * {@ User} 抽象工厂类
 * 抽象工厂相对比普通工厂，一般都有一个接口
 *
 */
public interface IUserFactory {
    default User createUser() {
        return User.createUser();
    }
}
