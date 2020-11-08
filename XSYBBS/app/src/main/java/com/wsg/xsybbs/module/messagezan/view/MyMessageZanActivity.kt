package com.wsg.xsybbs.module.messagezan.view

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.wsg.xsybbs.R
import com.wsg.xsybbs.base.BaseActivity
import com.wsg.xsybbs.module.messagezan.viewmodel.MessageZanViewModel
import kotlinx.android.synthetic.main.activity_my_message_zan.*

/**
 * Created by wsg
 * on         2020/5/30
 * function:
 */
class MyMessageZanActivity : BaseActivity() {

    private var mVm: MessageZanViewModel? = null
    private var mAdapter: MessageZanAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_message_zan)
        initView()
        initVm()
        initObserval()
        initData()
    }

    private fun initView() {
        zan_lv.layoutManager = LinearLayoutManager(this)
        mAdapter = MessageZanAdapter()
        zan_lv.adapter = mAdapter
    }

    private fun initVm() {
        mVm = ViewModelProvider(this).get(MessageZanViewModel::class.java)

    }

    private fun initObserval() {
        mVm?.mZanList?.observe(this, Observer {
            zan_tv.visibility = View.GONE
            mAdapter?.items = it
            mAdapter?.notifyDataSetChanged()
        })
        mVm?.mZanMessage?.observe(this, Observer {
            zan_tv.text = it.message
        })
    }

    private fun initData() {
        mVm?.getZanInfo()
    }

}