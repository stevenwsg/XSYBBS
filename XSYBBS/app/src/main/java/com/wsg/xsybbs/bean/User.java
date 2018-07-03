package com.wsg.xsybbs.bean;

import cn.bmob.v3.BmobUser;

/**
 * Created by wsg
 * on         2018/6/30.
 * function: 用户实体类
 */
public class User extends BmobUser {

    private int age;
    private boolean sex;
    private String desc;
    private String image;
    private Boolean isBan;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public boolean isSex() {
        return sex;
    }

    public void setSex(boolean sex) {
        this.sex = sex;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Boolean getBan() {
        return isBan;
    }

    public void setBan(Boolean ban) {
        isBan = ban;
    }
}
