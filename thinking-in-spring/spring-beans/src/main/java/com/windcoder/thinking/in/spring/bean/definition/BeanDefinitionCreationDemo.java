package com.windcoder.thinking.in.spring.bean.definition;

import com.windcoder.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.GenericBeanDefinition;

/**
 * {@link org.springframework.beans.factory.config.BeanDefinition} 构建实例
 */
public class BeanDefinitionCreationDemo {
    public static void main(String[] args) {
        // 1.通过 BeanDefinitionBuilder
        // 有generic和root两种，选择root类型因为是最顶层BeanDefinition，将不能有parent
        BeanDefinitionBuilder beanDefinitionBuilder =  BeanDefinitionBuilder.genericBeanDefinition(User.class);
        // 通过属性设置
        beanDefinitionBuilder.addPropertyValue("id",1);
        beanDefinitionBuilder.addPropertyValue("name","windcoder.com");
        // 获取 BeanDefinition 实例
        BeanDefinition beanDefinition = beanDefinitionBuilder.getBeanDefinition();
        // BeanDefinition 并非 Bean 最终态,可自定义修改。
        // BeanDefinition 以往的版本无法进行setter操作，在之后的版本（5.0.2等）做的补充提升
        // 以往一些属性BeanDefinition无法设置，需要 AbstractBeanDefinition来操作，里面有一些实现。

        // 2. 通过  AbstractBeanDefinition 以及派生类
        GenericBeanDefinition genericBeanDefinition = new GenericBeanDefinition();
        // 设置 Bean 类型
        genericBeanDefinition.setBeanClass(User.class);
        // 通过 MutablePropertyValues 批量操作属性
        MutablePropertyValues mutablePropertyValues = new MutablePropertyValues();
        // 易于理解的方式
//        mutablePropertyValues.addPropertyValue("id",2);
//        mutablePropertyValues.addPropertyValue("name","windcoder.com");
        // add方式相对更便利
        mutablePropertyValues.add("id",2).add("name","windcoder.com");
        // 通过 set MutablePropertyValues 批量操作属性
        genericBeanDefinition.setPropertyValues(mutablePropertyValues);

    }
}
