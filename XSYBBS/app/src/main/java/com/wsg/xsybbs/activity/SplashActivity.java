package com.wsg.xsybbs.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.widget.TextView;

import com.wsg.xsybbs.MainActivity;
import com.wsg.xsybbs.R;
import com.wsg.xsybbs.activity.user.LoginActivity;
import com.wsg.xsybbs.base.BaseActivity;
import com.wsg.xsybbs.util.SPUtils;
import com.wsg.xsybbs.util.StaticClass;
import com.wsg.xsybbs.util.UtilTools;


/**
 * Created by wsg
 * on         2018/6/28.
 * function:    闪屏页
 *
 */
public class SplashActivity extends BaseActivity{

    private TextView textView;


    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case StaticClass.HANDLER_SPLASH:
                    if(isFirst()){
                        startActivity(new Intent(SplashActivity.this,LoginActivity.class));
                    }
                    else{
                        startActivity(new Intent(SplashActivity.this,MainActivity.class));
                    }
            }
            finish();

        }
    };


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        initView();
        
    }

    private void initView() {

        //设置字体
        textView=(TextView)findViewById(R.id.splash_tv);
        UtilTools.setFont(this,textView);





        //动态申请权限







        //延时2000ms
        handler.sendEmptyMessageDelayed(StaticClass.HANDLER_SPLASH, 2000);
    }

    //判断程序是否第一次运行
    private boolean isFirst() {
        boolean isFirst = SPUtils.getBoolean(this,StaticClass.SHARE_IS_LOGIN,true);
        if(isFirst){
            return true;
        }else {
            return false;
        }

    }


}
