package com.wsg.xsybbs.module.forgetpassword.view

import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.wsg.xsybbs.R
import com.wsg.xsybbs.base.BaseActivity
import com.wsg.xsybbs.module.forgetpassword.viewmodel.ForgetPasswordViewModel
import es.dmoral.toasty.Toasty
import kotlinx.android.synthetic.main.activity_forget_password.*

/**
 * Created by wsg
 * on         2020/5/17
 * function: 忘记密码MVVM改造
 */
class ForgetPasswordActivity : BaseActivity() {

    var viewModel: ForgetPasswordViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forget_password)
        initVm()
        initView()
        initObserval()
    }

    private fun initVm() {
        viewModel = ViewModelProviders.of(this).get(ForgetPasswordViewModel::class.java)
    }

    private fun initView() {
        btn_forget_password.setOnClickListener {
            if (!TextUtils.isEmpty(et_email.text.toString())) {
                viewModel?.resetPasswordByEmail(et_email.text.toString())
            } else {
                Toasty.info(this, getString(R.string.text_tost_empty), Toast.LENGTH_SHORT, true).show()
            }
        }
    }

    private fun initObserval() {
        viewModel?.liveData?.observe(this, Observer {
            if (it.code == 0) {
                Toasty.success(this, it.message, Toast.LENGTH_SHORT, true).show()
                finish()
            } else {
                Toasty.error(this, it.message, Toast.LENGTH_SHORT, true).show()
            }
        })
    }
}