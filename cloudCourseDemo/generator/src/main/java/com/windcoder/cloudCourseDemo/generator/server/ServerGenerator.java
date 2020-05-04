package com.windcoder.cloudCourseDemo.generator.server;

import com.windcoder.cloudCourseDemo.generator.utils.FreeMarkerUtil;
import freemarker.template.TemplateException;

import java.io.IOException;

/**
 *  用于后端代码的生成，包含controller、service、dto
 */
public class ServerGenerator {
    static String toPath = "generator\\src\\main\\java\\com\\windcoder\\cloudCourseDemo\\generator\\test\\";
    public static void main(String[] args) throws IOException, TemplateException {
        FreeMarkerUtil.initConfig("test.ftl");
        FreeMarkerUtil.generator(toPath+"Test.java");
    }
}
