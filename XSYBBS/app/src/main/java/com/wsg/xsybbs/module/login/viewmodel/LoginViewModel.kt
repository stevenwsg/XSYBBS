package com.wsg.xsybbs.module.login.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.wsg.xsybbs.bean.User
import com.wsg.xsybbs.module.login.bean.LoginResultMessage
import com.wsg.xsybbs.module.login.model.LoginModel

/**
 * Created by wsg
 * on         2020/6/14
 * function:
 */
class LoginViewModel : ViewModel() {

    val message = MutableLiveData<LoginResultMessage>()
    private val model = LoginModel()

    fun login(user: User, password : String) {
        model.login(user, password, message)
    }
}