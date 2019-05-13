package com.wsg.kotlin.application

import android.app.Application


/*
 *  项目名:  Kotlin
 *  包名:    com.wsg.kotlin.application
 *  文件名:   MyApplication
 *  创建者:   wsg
 *  创建时间: 2019/5/13 10:50
 *  描述:     全局application 封装
 */
 
class MyApplication :Application(){
    
    override fun onCreate() {
        super.onCreate()
    }
}