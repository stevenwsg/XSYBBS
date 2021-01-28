package com.wsg.xsybbs.activity.usercenter

import android.content.Intent
import android.os.Bundle
import com.wsg.xsybbs.base.BaseActivity
import com.wsg.xsybbs.databinding.ActivityMyMessageBinding
import com.wsg.xsybbs.module.messagecomment.view.MyMessageCommentActivity
import com.wsg.xsybbs.module.messagezan.view.MyMessageZanActivity

/**
 * Created by wsg
 * on         2018/6/30.
 * function:  我的消息
 */
class MyMessageActivity : BaseActivity() {

    // VP2改造

    private lateinit var binding: ActivityMyMessageBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMyMessageBinding.inflate(layoutInflater)
        setContentView(binding.root)
        intiView()
    }

    private fun intiView() {
        binding.apply {
            rlZan.setOnClickListener {
                val i = Intent(this@MyMessageActivity, MyMessageZanActivity::class.java)
                startActivity(i)
            }

            rlComment.setOnClickListener {
                val i = Intent(this@MyMessageActivity, MyMessageCommentActivity::class.java)
                startActivity(i)
            }
        }
    }
}