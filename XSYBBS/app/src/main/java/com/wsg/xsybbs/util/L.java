package com.wsg.xsybbs.util;

import android.util.Log;

/**
 * Created by wsg
 * on         2018/6/28.
 * function: Log日志的封装
 */
public class L {

    //开关
    public static final  boolean DEBUG = true;
    //TAG
    public static final String TAG = "";

    //五个等级  DIWE

    public static void v(String text){
        if(DEBUG){
            Log.v(TAG,text);
        }
    }


    public static void d(String text){
        if(DEBUG){
            Log.d(TAG,text);
        }
    }

    public static void i(String text){
        if(DEBUG){
            Log.i(TAG,text);
        }
    }

    public static void w(String text){
        if(DEBUG){
            Log.w(TAG,text);
        }
    }

    public static void e(String text){
        if(DEBUG){
            Log.e(TAG,text);
        }
    }
}
