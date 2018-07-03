package com.wsg.xsybbs.bean;

import cn.bmob.v3.BmobObject;

/**
 * Created by wsg
 * on         2018/7/1.
 * function:
 */
public class Feedback extends BmobObject {
    //反馈内容
    private String Content;
    //反馈设备 Android/iOS
    private String deviceType;
    //反馈用户
    private String userid;

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }

    public String getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }
}
