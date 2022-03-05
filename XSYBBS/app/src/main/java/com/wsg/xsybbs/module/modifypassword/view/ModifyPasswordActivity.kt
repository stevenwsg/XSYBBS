package com.wsg.xsybbs.module.modifypassword.view

import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.wsg.base.BaseActivity
import com.wsg.xsybbs.R
import com.wsg.xsybbs.module.modifypassword.viewmodel.ModifyPassWordViewModel
import com.wsg.xsybbs.util.SPUtils
import com.wsg.xsybbs.util.StaticClass
import es.dmoral.toasty.Toasty
import kotlinx.android.synthetic.main.activity_modify_password.*

/**
 * Created by wsg
 * on         2020/5/10
 * function:  修改密码Activity
 */
class ModifyPasswordActivity : BaseActivity() {

    var viewModel : ModifyPassWordViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_modify_password)
        initView()
        initViewModel()
        initObserval()
    }

    private fun initView() {
        btn_update_password.setOnClickListener { modifyPassWord() }
        topBar.setTitle(getString(R.string.text_editor_password))
    }

    private fun initViewModel() {
        viewModel = ViewModelProvider(this).get(ModifyPassWordViewModel::class.java)
    }

    private fun initObserval() {
        viewModel?.result?.observe(this, Observer {
            if (it?.code == 0) {
                //保存用户信息
                SPUtils.putString(this, StaticClass.PASSWORD, et_new.text.toString())
                Toasty.success(this, getString(it.message), Toast.LENGTH_SHORT, true).show()
                finish()
            } else {
                Toasty.error(this, getString(it.message), Toast.LENGTH_SHORT, true).show()
            }
        })
    }

    private fun modifyPassWord() {
        if (!TextUtils.isEmpty(et_now.text)
                && !TextUtils.isEmpty(et_new.text)
                && !TextUtils.isEmpty(et_new_password.text)) {
            if (et_new.text.toString().equals(et_new_password.text.toString())) {
                viewModel?.modifyPassWord(et_now.text.toString(), et_new.text.toString())
            } else {
                Toasty.info(this, getString(R.string.text_two_input_not_consistent), Toast.LENGTH_SHORT, true).show()
            }
        } else {
            Toasty.info(this, getString(R.string.text_tost_empty), Toast.LENGTH_SHORT, true).show()
        }
    }
}