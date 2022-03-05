package com.wsg.xsybbs.activity.usercenter

import android.os.Bundle
import com.google.android.material.tabs.TabLayoutMediator
import com.wsg.base.BaseActivity
import com.wsg.xsybbs.R
import com.wsg.xsybbs.databinding.ActivityMyMessageBinding

/**
 * Created by wsg
 * on         2018/6/30.
 * function:  我的消息
 */
class MyMessageActivity : BaseActivity() {

    private lateinit var binding: ActivityMyMessageBinding

    // VP2改造
    private var adapter: MyMessageAdapter? = null
    private val titles = arrayOf("我收到的赞", "我收到的评论")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMyMessageBinding.inflate(layoutInflater)
        setContentView(binding.root)
        intiView()
    }

    private fun intiView() {
        binding.apply {
            topBar.setTitle(getString(R.string.text_my_message))
        }
        adapter = MyMessageAdapter(this)
        binding.vp2.adapter = adapter

        TabLayoutMediator(binding.tabLayout, binding.vp2) { tab, position ->
            //  为Tab设置Text
            tab.text = titles[position]
        }.attach()
    }
}