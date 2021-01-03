package com.wsg.xsybbs.module.forgetpassword.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import cn.bmob.v3.exception.BmobException
import cn.bmob.v3.listener.UpdateListener
import com.wsg.xsybbs.base.BaseViewModel
import com.wsg.xsybbs.bean.User
import com.wsg.xsybbs.module.forgetpassword.bean.ForgetPasswordResultMessage
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * Created by wsg
 * on         2020/5/17
 * function:
 */
class ForgetPasswordViewModel : BaseViewModel() {

    var liveData = MutableLiveData<ForgetPasswordResultMessage>()
    private var message: ForgetPasswordResultMessage? = null

    fun resetPasswordByEmail(email: String) {
        viewModelScope.launch(Dispatchers.IO) {
            User.resetPasswordByEmail(email, object : UpdateListener() {
                override fun done(p0: BmobException?) {
                    message = if (p0 == null) {
                        ForgetPasswordResultMessage(
                            ForgetPasswordResultMessage.CODE_SUCCESS,
                            ForgetPasswordResultMessage.MESSAGE_SUCCESS
                        )
                    } else {
                        ForgetPasswordResultMessage(
                            ForgetPasswordResultMessage.CODE_ERROR,
                            ForgetPasswordResultMessage.MESSAGE_ERROR
                        )
                    }
                    liveData.postValue(message)
                }
            })
        }
    }
}