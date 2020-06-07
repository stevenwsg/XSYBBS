package com.wsg.xsybbs.module.register.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.wsg.xsybbs.bean.User
import com.wsg.xsybbs.module.register.bean.RegisterResultMessage
import com.wsg.xsybbs.module.register.model.RegisterModel

/**
 * Created by wsg
 * on         2020/5/31
 * function:
 */
class RegisterViewModel : ViewModel() {
    var message = MutableLiveData<RegisterResultMessage>()
    private val model = RegisterModel()

    fun userRegister(user: User, password: String) {
        model.userRegister(user, password, message)
    }
}