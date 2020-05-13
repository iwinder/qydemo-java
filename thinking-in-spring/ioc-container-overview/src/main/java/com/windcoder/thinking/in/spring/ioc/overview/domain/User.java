package com.windcoder.thinking.in.spring.ioc.overview.domain;

/**
 * 用户类
 */
public class User {
    private Long id;
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    /**
     * 用于静态工厂创建Bean
     * @return
     */
    public static User createUser() {
        User user = new User();
        user.setId(3L);
        user.setName("windcoder");
        return user;
    }
    public static User createUser(long id) {
        User user = new User();
        user.setId(id);
        user.setName("windcoder");
        return user;
    }
}
