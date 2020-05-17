package com.wsg.xsybbs.module.forgetpassword.model

import androidx.lifecycle.MutableLiveData
import cn.bmob.v3.exception.BmobException
import cn.bmob.v3.listener.UpdateListener
import com.wsg.xsybbs.bean.User
import com.wsg.xsybbs.module.forgetpassword.bean.ForgetPasswordResultMessage
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

/**
 * Created by wsg
 * on         2020/5/17
 * function:  忘记密码Model
 */
class ForgetPasswordModel {

    var message: ForgetPasswordResultMessage? = null

    fun resetPasswordByEmail(email: String, liveData: MutableLiveData<ForgetPasswordResultMessage>) {
        GlobalScope.launch(Dispatchers.IO) {
            User.resetPasswordByEmail(email, object : UpdateListener() {
                override fun done(p0: BmobException?) {
                    if (p0 == null) {
                        message = ForgetPasswordResultMessage(ForgetPasswordResultMessage.CODE_SUCCESS, ForgetPasswordResultMessage.MESSAGE_SUCCESS)
                        liveData.postValue(message)
                    } else {
                        message = ForgetPasswordResultMessage(ForgetPasswordResultMessage.CODE_ERROR, ForgetPasswordResultMessage.MESSAGE_ERROR)
                        liveData.postValue(message)
                    }
                }
            })
        }
    }
}