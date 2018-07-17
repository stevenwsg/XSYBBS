package com.wsg.xsybbs.bean;

import java.io.Serializable;


import cn.bmob.v3.BmobObject;


/**
 * Created by wsg
 * on         2018/7/5.
 * function:  帖子实体类
 */
public class Note extends BmobObject implements Serializable {
    private String userid;
    private String image;
    private String typeid;
    private int top;
    private String title;
    private String content;

    //2018/7/8新加属性，为节省时间，将点赞数量和评论数量加到note表中
    private int zancount;
    private int replaycount;




    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getTypeid() {
        return typeid;
    }

    public void setTypeid(String typeid) {
        this.typeid = typeid;
    }

    public int getTop() {
        return top;
    }

    public void setTop(int top) {
        this.top = top;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }


    public int getZancount() {
        return zancount;
    }

    public void setZancount(int zancount) {
        this.zancount = zancount;
    }

    public int getReplaycount() {
        return replaycount;
    }

    public void setReplaycount(int replaycount) {
        this.replaycount = replaycount;
    }





}
