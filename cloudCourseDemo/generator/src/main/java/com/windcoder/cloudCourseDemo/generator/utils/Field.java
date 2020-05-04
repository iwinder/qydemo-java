package com.windcoder.cloudCourseDemo.generator.utils;

import lombok.Data;

/**
 *  字段类
 *
 *   用于存储每个字段的信息。
 *   流程：
 *   根据表名获取所有字段的信息。将字段信息填充到Field类中，得到Field列表，之后将Field列表变量传入模板。
 */
@Data
public class Field {
    // 字段名：course_id
    private String name;
    // 字段名小驼峰：courseId
    private String nameHump;
    // 字段名大驼峰：CourseId
    private String nameBigHump;
    // 中文名：课程
    private String nameCn;
    // 字段类型：char(8)
    private String type;
    // java类型：String
    private String javaType;
    // 注释：课程|ID
    private String comment;
    // 是否可为空
    private Boolean nullAble;
    // 字符串长度
    private Integer length;

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Field{");
        sb.append("name='").append(name).append('\'');
        sb.append(", nameHump='").append(nameHump).append('\'');
        sb.append(", nameBigHump='").append(nameBigHump).append('\'');
        sb.append(", nameCn='").append(nameCn).append('\'');
        sb.append(", type='").append(type).append('\'');
        sb.append(", javaType='").append(javaType).append('\'');
        sb.append(", comment='").append(comment).append('\'');
        sb.append(", nullAble=").append(nullAble);
        sb.append(", length=").append(length);
        sb.append('}');
        return sb.toString();
    }
}
