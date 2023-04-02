package com.windcoder.thinking.in.spring.ioc.overview.domain;

import com.windcoder.thinking.in.spring.ioc.overview.enums.City;
import org.springframework.core.io.Resource;

import java.util.Arrays;
import java.util.List;

/**
 * 用户类
 */
public class User {
    private Long id;
    private String name;
    private City city;
    private List<City> lifeCities;
    private City[] workCities;
    private Resource configFileLocation;

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

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public City[] getWorkCities() {
        return workCities;
    }

    public void setWorkCities(City[] workCities) {
        this.workCities = workCities;
    }

    public List<City> getLifeCities() {
        return lifeCities;
    }

    public void setLifeCities(List<City> lifeCities) {
        this.lifeCities = lifeCities;
    }

    public Resource getConfigFileLocation() {
        return configFileLocation;
    }

    public void setConfigFileLocation(Resource configFileLocation) {
        this.configFileLocation = configFileLocation;
    }


    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", city=" + city +
                ", lifeCities=" + lifeCities +
                ", workCities=" + Arrays.toString(workCities) +
                ", configFileLocation=" + configFileLocation +
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
