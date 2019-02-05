package com.wsg.xsybbs.ThreadPool;

/*
 *  项目名：  XSYBBS
 *  包名：    com.wsg.xsybbs.ThreadPool
 *  文件名:   MyThreadPool
 *  创建者:   wsg
 *  创建时间: 2019/2/5 14:30
 *  描述：     试着解决内存泄漏问题
 */

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MyThreadPool {

    private static  ExecutorService pool;

     static {
        pool=Executors.newSingleThreadExecutor();
    }

    public static ExecutorService  getThreadPool(){
        return pool;
    }




}
