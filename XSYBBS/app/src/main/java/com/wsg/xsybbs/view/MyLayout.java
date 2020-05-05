package com.wsg.xsybbs.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;


/*
 *  项目名：  XSYBBS
 *  包名：    com.wsg.xsybbs.view
 *  文件名:   MyLayout
 *  创建者:   wsg
 *  创建时间: 2019/2/9 13:30
 *  描述： 解决mymessagefragment 内存泄漏问题
 */

public class MyLayout extends LinearLayout {
    public MyLayout(Context context) {
        this(context.getApplicationContext(),null);
    }

    public MyLayout(Context context, @Nullable AttributeSet attrs) {
        this(context.getApplicationContext(), attrs,0);
    }

    public MyLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context.getApplicationContext(), attrs, defStyleAttr);
    }


}
