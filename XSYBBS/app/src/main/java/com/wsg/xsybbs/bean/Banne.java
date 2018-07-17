package com.wsg.xsybbs.bean;

import cn.bmob.v3.BmobObject;


/**
 * Created by wsg
 * on         2018/7/5.
 * function: 公告板
 */
public class Banne extends BmobObject {
    //图片地址
    private String photo;
    //图片简介
    private String desc;

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
