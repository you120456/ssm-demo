package com.ssm.entity;

import com.alibaba.excel.metadata.BaseRowModel;

import java.io.Serializable;

/**
 * Created by jiangfeixiang on 2018/9/4
 * @author smfx1314
 *
 */
public class User extends BaseRowModel implements Serializable {

    private Integer id;

    private String username;

    private String sex;

    private String city;

    private Integer age;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", sex='" + sex + '\'' +
                ", city='" + city + '\'' +
                ", age=" + age +
                '}';
    }
}
