package com.wsg.xsybbs.bean;

import cn.bmob.v3.BmobObject;


/**
 * Created by wsg
 * on         2018/7/5.
 * function: 贴吧板块
 */
public class Type extends BmobObject {
    private String content;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
