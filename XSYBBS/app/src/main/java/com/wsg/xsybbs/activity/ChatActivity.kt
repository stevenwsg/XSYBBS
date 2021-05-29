package com.wsg.xsybbs.activity

import android.os.Bundle
import android.view.Window
import com.hyphenate.easeui.modules.chat.EaseChatFragment
import com.wsg.xsybbs.R
import com.wsg.xsybbs.base.BaseActivity

/**
 * Created by wsg
 * on         2018/7/4.
 * function: 聊天界面
 */
class ChatActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.activity_chat)

        val easeChatFragment = EaseChatFragment() //环信聊天界面
        easeChatFragment.arguments = intent?.extras //需要的参数
        supportFragmentManager.beginTransaction().add(R.id.layout_chat, easeChatFragment).commit()
    }
}