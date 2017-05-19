package com.sung.controller;

import java.io.Serializable;

/**
 * Created by sungang on 2017/5/19.
 */
public class User implements Serializable {

    private static final long serialVersionUID = -1L;

    /**
     * 编号
     */
    private Long id;

    /**
     * 用户名
     */
    private String account;

    /**
     * 姓名
     */
    private String name;

    /**
     * 年级
     */
    private Integer age;


    private Integer sex;

    public User() {
    }

    public User(Long id, String account, String name, Integer age, Integer sex) {
        this.id = id;
        this.account = account;
        this.name = name;
        this.age = age;
        this.sex = sex;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }
}
