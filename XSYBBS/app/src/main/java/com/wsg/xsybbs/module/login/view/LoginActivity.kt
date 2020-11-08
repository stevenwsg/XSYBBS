package com.wsg.xsybbs.module.login.view

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.Gravity
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.wsg.xsybbs.MainActivity
import com.wsg.xsybbs.R
import com.wsg.xsybbs.base.BaseActivity
import com.wsg.xsybbs.bean.User
import com.wsg.xsybbs.module.forgetpassword.view.ForgetPasswordActivity
import com.wsg.xsybbs.module.login.bean.LoginResultMessage
import com.wsg.xsybbs.module.login.viewmodel.LoginViewModel
import com.wsg.xsybbs.module.register.view.RegisterActivity
import com.wsg.xsybbs.module.tourist.view.TouristLoginActivity
import com.wsg.xsybbs.util.SPUtils
import com.wsg.xsybbs.util.StaticClass
import com.wsg.xsybbs.view.CustomDialog
import es.dmoral.toasty.Toasty
import kotlinx.android.synthetic.main.activity_login.*

/**
 * Created by wsg
 * on         2020/6/7
 * function:
 */
class LoginActivity : BaseActivity() {

    private var dialog: CustomDialog? = null
    private var mVm: LoginViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        initView()
        initVm()
        initObserval()
    }

    private fun initView() {
        dialog = CustomDialog(this, 100, 100, R.layout.dialog_loding_login, R.style.Theme_dialog, Gravity.CENTER, R.style.pop_anim_style)
        dialog!!.setCancelable(false)

        et_name.setText(SPUtils.getString(this, StaticClass.USER_NAME, ""))
        et_password.setText(SPUtils.getString(this, StaticClass.PASSWORD, ""))

        bt_login.setOnClickListener { login() }
        bt_registered.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }
        tv_tourist.setOnClickListener {
            val intent = Intent(this, TouristLoginActivity::class.java)
            startActivity(intent)
        }
        tv_forget.setOnClickListener {
            val intent = Intent(this, ForgetPasswordActivity::class.java)
            startActivity(intent)
        }
    }

    private fun initVm() {
        mVm = ViewModelProvider(this).get(LoginViewModel::class.java)
    }

    private fun initObserval() {
        mVm?.message?.observe(this, Observer {
            when (it.code) {
                LoginResultMessage.CODE_LOGIN_SUCESS -> {
                    dialog?.dismiss()
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                }
                LoginResultMessage.CODE_LOGIN_FAIL -> {
                    dialog?.dismiss()
                    Toasty.error(this, it.message, Toast.LENGTH_SHORT, true).show()
                }
            }
        })
    }

    private fun login() {
        if (!TextUtils.isEmpty(et_name.text) && !TextUtils.isEmpty(et_password.text)) {
            dialog?.show()
            val user = User()
            user.username = et_name.text.toString()
            user.setPassword(et_password.text.toString())
            mVm?.login(user, et_password.text.toString())
        } else {
            Toasty.info(this, "输入框不能为空", Toast.LENGTH_SHORT, true).show()
        }
    }
}