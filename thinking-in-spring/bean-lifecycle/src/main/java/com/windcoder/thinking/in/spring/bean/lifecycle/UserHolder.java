package com.windcoder.thinking.in.spring.bean.lifecycle;

import com.windcoder.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanClassLoaderAware;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.BeanNameAware;
import sun.plugin.com.BeanClass;

/**
 *  User Holder 类
 */
public class UserHolder  implements BeanNameAware, BeanClassLoaderAware, BeanFactoryAware {
    private final User user;

    private Integer number;

    private String description;

    private ClassLoader classLoader;
    private BeanFactory beanFactory;
    private String beanName;


    public UserHolder(User user) {
        this.user = user;
    }


    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "UserHolder{" +
                "user=" + user +
                ", number=" + number +
                ", description='" + description + '\'' +
                '}';
    }

    @Override
    public void setBeanName(String name) {
        this.beanName = name;

    }

    @Override
    public void setBeanClassLoader(ClassLoader classLoader) {
        this.classLoader = classLoader;
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = beanFactory;
    }
}
