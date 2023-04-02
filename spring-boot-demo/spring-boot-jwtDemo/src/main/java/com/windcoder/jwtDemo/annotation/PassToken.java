package com.windcoder.jwtDemo.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @interface Annotation{ } 定义一个注解 @Annotation，一个注解是一个类
 *
 * @Target:注解的作用目标
 * 	ElementType.METHOD ---方法
 * 	ElementType.TYPE --- 接口、类、枚举、注解
 *
 * 	@Retention：注解的保留位置
 * 	RetentionPolicy.RUNTIME:这种类型的Annotations将被JVM保留,所以他们能在运行时被JVM或其他使用反射机制的代码所读取和使用。
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface PassToken {
	boolean required() default true;
}
