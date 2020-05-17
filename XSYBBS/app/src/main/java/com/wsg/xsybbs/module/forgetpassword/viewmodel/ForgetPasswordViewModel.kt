package com.wsg.xsybbs.module.forgetpassword.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.wsg.xsybbs.module.forgetpassword.bean.ForgetPasswordResultMessage
import com.wsg.xsybbs.module.forgetpassword.model.ForgetPasswordModel

/**
 * Created by wsg
 * on         2020/5/17
 * function:
 */
class ForgetPasswordViewModel : ViewModel() {

    var liveData = MutableLiveData<ForgetPasswordResultMessage>()
    val model = ForgetPasswordModel()

    fun resetPasswordByEmail(email: String) {
        model.resetPasswordByEmail(email, liveData)
    }
}