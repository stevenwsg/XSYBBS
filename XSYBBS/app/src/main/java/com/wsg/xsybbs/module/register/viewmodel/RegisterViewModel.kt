package com.wsg.xsybbs.module.register.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import cn.bmob.v3.exception.BmobException
import cn.bmob.v3.listener.SaveListener
import com.hyphenate.chat.EMClient
import com.hyphenate.exceptions.HyphenateException
import com.wsg.base.BaseViewModel
import com.wsg.xsybbs.application.MyApplication
import com.wsg.xsybbs.bean.User
import com.wsg.xsybbs.module.register.bean.RegisterResultMessage
import com.wsg.xsybbs.util.SPUtils
import com.wsg.xsybbs.util.StaticClass
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * Created by wsg
 * on         2020/5/31
 * function:
 */
class RegisterViewModel : BaseViewModel() {
    var message = MutableLiveData<RegisterResultMessage>()
    private var mRegisterResultMessage: RegisterResultMessage? = null

    fun userRegister(user: User, password: String) {
        viewModelScope.launch(Dispatchers.IO) {
            user.signUp(object : SaveListener<User>() {
                override fun done(p0: User?, p1: BmobException?) {
                    if (p1 == null) {
                        // 开始注册环信IM服务
                        Thread(Runnable { // 环信这玩意有毒，非得起新线程才能注册成功，协程里面写都不行
                            try {
                                EMClient.getInstance()
                                    .createAccount(user.username, user.objectId) //同步方法
                                //保存用户信息
                                SPUtils.putString(
                                    MyApplication.getInstance(),
                                    StaticClass.USER_NAME,
                                    user.username
                                )
                                SPUtils.putString(
                                    MyApplication.getInstance(),
                                    StaticClass.PASSWORD,
                                    password
                                )

                                mRegisterResultMessage = RegisterResultMessage(
                                    RegisterResultMessage.CODE_SUC,
                                    RegisterResultMessage.MESSAGE_SUC
                                )
                                message.postValue(mRegisterResultMessage)
                            } catch (e: HyphenateException) {
                                mRegisterResultMessage = RegisterResultMessage(
                                    RegisterResultMessage.CODE_FAIL,
                                    RegisterResultMessage.MESSAGE_FAIL
                                )
                                message.postValue(mRegisterResultMessage)
                            }
                        }).start()
                    } else {
                        mRegisterResultMessage = RegisterResultMessage(
                            RegisterResultMessage.CODE_FAIL,
                            RegisterResultMessage.MESSAGE_FAIL
                        )
                        message.postValue(mRegisterResultMessage)
                    }
                }
            })
        }
    }
}