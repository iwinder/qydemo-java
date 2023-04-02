package com.windcoder.thinking.in.spring.bean.definition.factory.factoryBeanDemo;

import com.windcoder.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.FactoryBean;

/**
 * {@link User }Bean 的 {@link org.springframework.beans.factory.FactoryBean} 实现
 *
 * Spring5 之后使用Java8,此时Singleton用了default的方法，所以默认是单例模式，单例可以用单例或原型的方式。
 */
public class UserFactoryBean implements FactoryBean {
    @Override
    public Object getObject() throws Exception {
        return User.createUser();
    }

    @Override
    public Class<?> getObjectType() {
        return User.class;
    }
}
