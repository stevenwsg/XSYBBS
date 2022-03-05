package com.wsg.xsybbs.activity.usercenter

import android.os.Bundle
import com.wsg.base.BaseActivity
import com.wsg.xsybbs.databinding.ActivityAboutBinding

/*
 *  项目名:  XSYBBS
 *  包名:    com.wsg.xsybbs.activity.usercenter
 *  文件名:   AboutActivity
 *  创建者:   wsg
 *  创建时间: 2019/4/26 19:39
 *  描述:     关于界面
 */
class AboutActivity : BaseActivity() {

    private lateinit var binding: ActivityAboutBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAboutBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}