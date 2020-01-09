package com.windcoder.thinking.in.spring.ioc.overview.domain;

/**
 * 用户类
 */
public class User {
    private Long id;
    private String namel;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNamel() {
        return namel;
    }

    public void setNamel(String namel) {
        this.namel = namel;
    }


    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", namel='" + namel + '\'' +
                '}';
    }
}
