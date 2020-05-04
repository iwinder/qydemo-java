package com.windcoder.cloudCourseDemo.generator.server;

import com.windcoder.cloudCourseDemo.generator.utils.FreeMarkerUtil;
import freemarker.template.TemplateException;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 *  用于后端代码的生成，包含controller、service、dto
 */
public class ServerGenerator {
    static String toServicePath = "server\\src\\main\\java\\com\\windcoder\\cloudCourseDemo\\server\\service\\";
    public static void main(String[] args) throws IOException, TemplateException {
        String Domain = "Section";
        String domain = "section";
        Map<String, Object> map = new HashMap<>();
        map.put("Domain", Domain);
        map.put("domain", domain);
        FreeMarkerUtil.initConfig("service.ftl");
        FreeMarkerUtil.generator(toServicePath+ Domain +"Service.java", map);
    }
}
