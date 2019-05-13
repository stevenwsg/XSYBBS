package com.wsg.kotlin.base

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup


/*
 *  项目名:  Kotlin
 *  包名:    com.wsg.kotlin.base
 *  文件名:   BaseFragment
 *  创建者:   wsg
 *  创建时间: 2019/5/13 11:00
 *  描述:     全局Fragment封装
 */

open class BaseFragment : Fragment(){
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return super.onCreateView(inflater, container, savedInstanceState)
    }
}