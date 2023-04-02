package com.windcoder.thinking.in.spring.bean.definition.factory.abstractFactory;

import com.windcoder.thinking.in.spring.common.utils.PrintZUtill;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.core.annotation.Order;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.security.auth.Destroyable;

/**
 *  {@link IUserFactory} 抽象工厂的默认实现
 */
public class DefaultUserFactory implements IUserFactory, InitializingBean, DisposableBean {

    // 基于@PostConstruct 注解
    // 1.6已有的Java标准注解
    @PostConstruct
    public void init() {
        PrintZUtill.println("@PostConstruct: IUserFactory 初始化中...");
    }

    public void initUserFactory() {
        PrintZUtill.println("自定义初始化方法 initUserFactory: IUserFactory 初始化中...");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        PrintZUtill.println("InitializingBean#afterPropertiesSet: IUserFactory 初始化中...");
    }

    @PreDestroy
    public void preDestroy() {
        PrintZUtill.println("@PreDestroy: IUserFactory 销毁中...");
    }

    @Override
    public void destroy() throws Exception {
        PrintZUtill.println("DisposableBean#destroy: IUserFactory 销毁中...");
    }


    public void doDestroy() {
        PrintZUtill.println("自定义初始化方法 doDestroy: IUserFactory 销毁中...");
    }

    /**
     * 不一定完全会被回调
     * @throws Throwable
     */
    @Override
    protected void  finalize() throws Throwable {
        PrintZUtill.println("当前对象 DefaultUserFactory  正在被回收...");
    }


}