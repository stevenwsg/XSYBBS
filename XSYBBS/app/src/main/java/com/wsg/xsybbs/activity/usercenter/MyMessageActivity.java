package com.wsg.xsybbs.activity.usercenter;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import androidx.annotation.Nullable;

import com.wsg.xsybbs.R;
import com.wsg.xsybbs.module.messagecomment.view.MyMessageCommentActivity;
import com.wsg.xsybbs.activity.message.MyMessageZanActivity;
import com.wsg.xsybbs.base.BaseActivity;

/**
 * Created by wsg
 * on         2018/6/30.
 * function:  我的消息
 */
public class MyMessageActivity extends BaseActivity implements View.OnClickListener {

    private RelativeLayout relativeLayout1;
    private RelativeLayout relativeLayout2;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_message);

        intiView();

    }

    private void intiView() {
        relativeLayout1=(RelativeLayout)findViewById(R.id.rl_zan);
        relativeLayout2=(RelativeLayout)findViewById(R.id.rl_comment);


        relativeLayout1.setOnClickListener(this);
        relativeLayout2.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent i=null;
        switch (v.getId()){
            case R.id.rl_zan:
                i=new Intent(MyMessageActivity.this, MyMessageZanActivity.class);
                startActivity(i);
                break;
            case R.id.rl_comment:
                i=new Intent(MyMessageActivity.this, MyMessageCommentActivity.class);
                startActivity(i);
                break;
        }
    }
}
