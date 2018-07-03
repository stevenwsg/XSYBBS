package com.wsg.xsybbs.application;

import android.app.Application;

import com.tencent.bugly.crashreport.CrashReport;
import com.wsg.xsybbs.util.StaticClass;
import cn.bmob.v3.Bmob;
import cn.bmob.v3.update.BmobUpdateAgent;

/**
 * Created by wsg
 * on         2018/6/29.
 * function:   全局application的封装
 */
public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        //初始化bugly
        CrashReport.initCrashReport(getApplicationContext(), StaticClass.BUGLY_APP_ID, true);
        //初始化Bmob
        Bmob.initialize(this, StaticClass.BMOB_APP_ID);

        //自动创建APP更新表，注意只能使用一次
        //BmobUpdateAgent.initAppVersion();

        //wifi/3g/3g/4g均可更新
        BmobUpdateAgent.setUpdateOnlyWifi(false);
        //开启更新
        BmobUpdateAgent.update(this);



    }
}
