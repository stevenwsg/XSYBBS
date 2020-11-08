package com.wsg.xsybbs.module.feedback.view

import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.wsg.xsybbs.R
import com.wsg.xsybbs.base.BaseActivity
import com.wsg.xsybbs.module.feedback.viewmodel.FeedBackViewModel
import es.dmoral.toasty.Toasty
import kotlinx.android.synthetic.main.activity_feedback.*

/**
 * Created by wsg
 * on         2020/5/5
 * function:  反馈MVVM改造
 */

class FeedBackActivity : BaseActivity() {

    private var mFeedBackVM : FeedBackViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_feedback)
        initView()
        initVM()
        initObserval()
    }

    private fun initView() {
        bt_back.setOnClickListener {
            if (!TextUtils.isEmpty(et_back.text.toString())) {
                mFeedBackVM?.getFeedBackMessage(et_back.text.toString())
            } else {
                Toasty.info(this@FeedBackActivity,  getString(R.string.text_tost_empty), Toast.LENGTH_SHORT, true).show()
            }

        }
    }

    private fun initVM() {
        mFeedBackVM = ViewModelProvider(this).get(FeedBackViewModel::class.java)
    }

    private fun initObserval() {
        mFeedBackVM?.messageLiveData?.observe(this, Observer { t ->
            if (t?.code == 0) {
                Toasty.success(this@FeedBackActivity, t.message, Toast.LENGTH_SHORT, true).show()
                finish()
            } else {
                t?.message?.let { Toasty.error(this@FeedBackActivity, it, Toast.LENGTH_SHORT, true).show() }
            }
        })
    }
}