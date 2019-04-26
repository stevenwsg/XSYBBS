package com.wsg.xsybbs.threadpool;

/*
 *  项目名：  XSYBBS
 *  包名：    com.wsg.xsybbs.threadpool
 *  文件名:   MyThreadPool
 *  创建者:   wsg
 *  创建时间: 2019/4/11 22:44
 *  描述：    全局线程池
 */

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class MyThreadPool {

    public final static int corePoolSize = 4;
    public final static int maximumPoolSize = 6;
    public final static long keepAliveTime = 200;
    public final static ArrayBlockingQueue<Runnable> workQueue =new ArrayBlockingQueue<Runnable>(10);
    private static ThreadPoolExecutor poolExecutor;

    static {
        poolExecutor = new ThreadPoolExecutor(corePoolSize,maximumPoolSize,keepAliveTime, TimeUnit.MILLISECONDS,workQueue);
    }

    public static void execute(Runnable runnable){
        poolExecutor.execute(runnable);
    }

    public static void shutDown(){
        poolExecutor.shutdown();
    }

}
