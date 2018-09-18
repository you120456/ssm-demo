package com.ssm.entity;

import cn.afterturn.easypoi.excel.annotation.Excel;

import java.io.Serializable;

/**
 * Created by jiangfeixiang on 2018/9/4
 *
 */
public class User implements Serializable {
    @Excel(name = "id", orderNum = "0")
    private Integer id;
    @Excel(name = "姓名", orderNum = "1")
    private String username;
    @Excel(name = "性别", orderNum = "2")
    private String sex;
    @Excel(name = "城市", orderNum = "3")
    private String city;
    @Excel(name = "年龄", orderNum = "4")
    private Integer age;

    public User() {
    }

    public User(Integer id, String username, String sex, String city, Integer age) {
        this.id = id;
        this.username = username;
        this.sex = sex;
        this.city = city;
        this.age = age;
    }

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
