package com.windcoder.thinking.in.spring.bean.definition.factory.abstractFactory;

import com.windcoder.thinking.in.spring.ioc.overview.domain.User;

/**
 *  {@link IUserFactory} 抽象工厂的默认实现
 */
public class DefaultUserFactory2 implements IUserFactory {
    public   User createUser() {
        return User.createUser(5L);
    }
}
