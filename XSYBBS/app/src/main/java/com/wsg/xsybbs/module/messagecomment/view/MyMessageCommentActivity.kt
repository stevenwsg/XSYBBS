package com.wsg.xsybbs.module.messagecomment.view

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.wsg.xsybbs.R
import com.wsg.xsybbs.base.BaseActivity
import com.wsg.xsybbs.module.messagecomment.viewmodel.MessageCommentViewModel
import kotlinx.android.synthetic.main.activity_my_message_comment.*

/**
 * Created by wsg
 * on         2020/5/30
 * function: 我收到的评论改造
 */
class MyMessageCommentActivity : BaseActivity() {

    private var mVm: MessageCommentViewModel? = null
    private var mAdapter: MessageCommentAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_message_comment)
        initView()
        initVm()
        initObserval()
        initData()
    }

    private fun initView() {
        comment_lv.layoutManager = LinearLayoutManager(this)
        mAdapter = MessageCommentAdapter()
        comment_lv.adapter = mAdapter
    }

    private fun initVm() {
        mVm = ViewModelProviders.of(this).get(MessageCommentViewModel::class.java)
    }

    private fun initObserval() {
        mVm?.commentList?.observe(this, Observer {
            comment_tv.visibility = View.GONE
            mAdapter?.items = it
            mAdapter?.notifyDataSetChanged()
        })
        mVm?.commentMessage?.observe(this, Observer {
            comment_tv.text = it.message
        })

    }

    private fun initData() {
        mVm?.getCommentInfo()
    }

}