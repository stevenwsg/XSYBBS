package com.wsg.xsybbs.activity.usercenter

import android.content.Intent
import android.os.Bundle
import com.wsg.xsybbs.R
import com.wsg.xsybbs.base.BaseActivity
import com.wsg.xsybbs.module.messagecomment.view.MyMessageCommentActivity
import com.wsg.xsybbs.module.messagezan.view.MyMessageZanActivity
import kotlinx.android.synthetic.main.activity_my_message.*

/**
 * Created by wsg
 * on         2018/6/30.
 * function:  我的消息
 */
class MyMessageActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_message)
        intiView()
    }

    private fun intiView() {
        rl_zan.setOnClickListener {
            val i = Intent(this, MyMessageZanActivity::class.java)
            startActivity(i)
        }

        rl_comment.setOnClickListener {
            val i = Intent(this, MyMessageCommentActivity::class.java)
            startActivity(i)
        }
    }
}