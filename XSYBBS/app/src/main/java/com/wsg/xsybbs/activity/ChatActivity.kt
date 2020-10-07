package com.wsg.xsybbs.activity

import android.os.Bundle
import android.view.Window
import androidx.fragment.app.FragmentActivity
import com.hyphenate.easeui.ui.EaseChatFragment
import com.wsg.xsybbs.R

/**
 * Created by wsg
 * on         2018/7/4.
 * function: 聊天界面
 */
class ChatActivity : FragmentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.activity_chat)

        val easeChatFragment = EaseChatFragment() //环信聊天界面
        easeChatFragment.arguments = intent?.extras //需要的参数
        fragmentManager.beginTransaction().add(R.id.layout_chat, easeChatFragment).commit()
    }
}