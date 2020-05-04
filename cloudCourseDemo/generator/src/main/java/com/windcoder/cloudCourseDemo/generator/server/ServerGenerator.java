package com.windcoder.cloudCourseDemo.generator.server;

import com.windcoder.cloudCourseDemo.generator.utils.DbUtil;
import com.windcoder.cloudCourseDemo.generator.utils.Field;
import com.windcoder.cloudCourseDemo.generator.utils.FreeMarkerUtil;
import freemarker.template.TemplateException;

import java.io.IOException;
import java.util.*;

/**
 *  用于后端代码的生成，包含controller、service、dto
 */
public class ServerGenerator {
    static final String MODULE = "business";

    static String toServicePath = "server\\src\\main\\java\\com\\windcoder\\cloudCourseDemo\\server\\service\\";
    static String toDtoePath = "server\\src\\main\\java\\com\\windcoder\\cloudCourseDemo\\server\\service\\";
    static String toControllerPath = MODULE + "\\src\\main\\java\\com\\windcoder\\cloudCourseDemo\\" + MODULE + "\\controller\\admin\\";


    public static void main(String[] args) throws Exception  {
        String Domain = "Section";
        String domain = "section";
        String tableNameCn = "小节";
        String module = MODULE;
        List<Field> fieldList = DbUtil.getColumnByTableName(domain);
        Set<String> typeSet = getJavaTypes(fieldList);
        Map<String, Object> map = new HashMap<>();
        map.put("Domain", Domain);
        map.put("domain", domain);
        map.put("tableNameCn", tableNameCn);
        map.put("module", module);
        map.put("fieldList", fieldList);
        map.put("typeSet", typeSet);

        // 生成Service
        FreeMarkerUtil.initConfig("service.ftl");
        FreeMarkerUtil.generator(toServicePath+ Domain +"Service.java", map);

        // 生成Controller
        FreeMarkerUtil.initConfig("controller.ftl");
        FreeMarkerUtil.generator(toControllerPath+ Domain +"Controller.java", map);
    }


    /**
     * 获取所有的Java类型，使用Set去重
     */
    private static Set<String> getJavaTypes(List<Field> fieldList) {
        Set<String> set = new HashSet<>();
        for (int i = 0; i < fieldList.size(); i++) {
            Field field = fieldList.get(i);
            set.add(field.getJavaType());
        }
        return set;
    }
}
