package com.windcoder.thinking.in.spring.bean.definition.factory.abstractFactory;

import com.windcoder.thinking.in.spring.common.utils.PrintZUtill;
import org.springframework.beans.factory.InitializingBean;

import javax.annotation.PostConstruct;

/**
 *  {@link IUserFactory} 抽象工厂的默认实现
 */
public class DefaultUserFactory implements IUserFactory, InitializingBean {

    // 基于@PostConstruct 注解
    // 1.6已有的Java标准注解
    @PostConstruct
    public void init() {
        PrintZUtill.println("@PostConstruct: IUserFactory 初始化中");
    }

    public void initUserFactory() {
        PrintZUtill.println("自定义初始化方法 initUserFactory: IUserFactory 初始化中");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        PrintZUtill.println("InitializingBean#afterPropertiesSet: IUserFactory 初始化中");
    }
}
