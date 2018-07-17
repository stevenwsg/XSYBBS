package com.wsg.xsybbs.bean;

import cn.bmob.v3.BmobObject;


/**
 * Created by wsg
 * on         2018/7/8.
 * function: 评论
 */
public class Comment extends BmobObject {

    private String noteid;
    private String userid;
    private String username;
    private String content;

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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
