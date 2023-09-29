package com.windCoder.SSMDemo.entity;

/**
 * Description:
 * User: wind
 * Date: 2017-08-01
 * Time: 18:13 下午
 */
public class User {
    private Integer id;
    private String uname;
    private String createtime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }
}
