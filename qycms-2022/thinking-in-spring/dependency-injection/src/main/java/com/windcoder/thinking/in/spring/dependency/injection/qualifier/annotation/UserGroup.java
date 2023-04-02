package com.windcoder.thinking.in.spring.dependency.injection.qualifier.annotation;

import org.springframework.beans.factory.annotation.Qualifier;

import java.lang.annotation.*;

/**
 * 用户组注解，扩展{@link  Qualifier @Qualifier}
 */
@Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER, ElementType.TYPE, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
@Qualifier // 注解的派生/继承
public @interface UserGroup {
}
