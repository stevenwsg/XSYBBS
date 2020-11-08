package com.wsg.xsybbs.module.register.view

import android.os.Bundle
import android.text.TextUtils
import android.view.Gravity
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.wsg.xsybbs.R
import com.wsg.xsybbs.base.BaseActivity
import com.wsg.xsybbs.bean.User
import com.wsg.xsybbs.module.register.viewmodel.RegisterViewModel
import com.wsg.xsybbs.view.CustomDialog
import es.dmoral.toasty.Toasty
import kotlinx.android.synthetic.main.activity_register.*

/**
 * Created by wsg
 * on         2020/5/31
 * function:
 */
class RegisterActivity : BaseActivity() {

    private var mDialog: CustomDialog? = null

    private var isGender = true
    private var mVm: RegisterViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        initView()
        initVm()
        initObserval()
    }

    private fun initView() {
        mDialog = CustomDialog(this, 100, 100, R.layout.dialog_loding_register, R.style.Theme_dialog, Gravity.CENTER, R.style.pop_anim_style);
        mDialog?.setCancelable(false)
        mRadioGroup.setOnCheckedChangeListener { _, checkedId -> isGender = checkedId == R.id.rb_boy }

        btnRegistered.setOnClickListener {
            if (!TextUtils.isEmpty(et_user.text) && !TextUtils.isEmpty(et_age.text) && !TextUtils.isEmpty(et_desc.text)
                    && !TextUtils.isEmpty(et_pass.text) && !TextUtils.isEmpty(et_password.text) && !TextUtils.isEmpty(et_email.text)) {
                if (et_pass.text != et_password.text) {
                    mDialog?.show()
                    val user = User()
                    user.username = et_user.text.toString()
                    user.setPassword(et_pass.text.toString())
                    user.isSex = isGender
                    user.desc = et_desc.text.toString()
                    user.age = et_age.text.toString().toInt()
                    user.email = et_email.text.toString()
                    mVm?.userRegister(user, et_pass.text.toString())
                } else {
                    Toasty.info(this, getString(R.string.text_two_input_not_consistent), Toast.LENGTH_SHORT, true).show()
                }
            } else {
                Toasty.info(this, getString(R.string.text_tost_empty), Toast.LENGTH_SHORT, true).show()
            }
        }
    }

    private fun initVm() {
        mVm = ViewModelProvider(this).get(RegisterViewModel::class.java)
    }

    private fun initObserval() {
        mVm?.message?.observe(this, Observer {
            if (it.code == 0) {
                Toasty.success(this, it.message, Toast.LENGTH_SHORT, true).show()
                finish()
            } else {
                Toasty.error(this, it.message, Toast.LENGTH_SHORT, true).show()
            }
            mDialog?.dismiss()
        })
    }
}