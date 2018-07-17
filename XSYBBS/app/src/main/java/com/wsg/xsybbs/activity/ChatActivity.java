package com.wsg.xsybbs.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.view.Window;

import com.hyphenate.easeui.ui.EaseChatFragment;
import com.wsg.xsybbs.R;


/**
 * Created by wsg
 * on         2018/7/4.
 * function: 聊天界面
 */

public class ChatActivity extends FragmentActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_chat);


        EaseChatFragment easeChatFragment = new EaseChatFragment();  //环信聊天界面
        easeChatFragment.setArguments(getIntent().getExtras()); //需要的参数
        getFragmentManager().beginTransaction().add(R.id.layout_chat, easeChatFragment).commit();
    }
}
