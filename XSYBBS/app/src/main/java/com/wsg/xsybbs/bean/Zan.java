package com.wsg.xsybbs.bean;

import cn.bmob.v3.BmobObject;


/**
 * Created by wsg
 * on         2018/7/8.
 * function: 赞
 */
public class Zan extends BmobObject {

    private String noteid;
    private String userid;
    private String username;
    private Boolean status;
    private String notename;

    public String getNoteid() {
        return noteid;
    }
    public void setNoteid(String noteid) {
        this.noteid = noteid;
    }
    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }


    public String getNotename() {
        return notename;
    }

    public void setNotename(String notename) {
        this.notename = notename;
    }
}
