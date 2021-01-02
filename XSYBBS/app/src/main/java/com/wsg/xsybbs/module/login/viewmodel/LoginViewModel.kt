package com.wsg.xsybbs.module.login.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cn.bmob.v3.exception.BmobException
import cn.bmob.v3.listener.SaveListener
import com.hyphenate.EMCallBack
import com.hyphenate.chat.EMClient
import com.wsg.xsybbs.application.MyApplication
import com.wsg.xsybbs.bean.User
import com.wsg.xsybbs.module.login.bean.LoginResultMessage
import com.wsg.xsybbs.util.L
import com.wsg.xsybbs.util.SPUtils
import com.wsg.xsybbs.util.StaticClass
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * Created by wsg
 * on         2020/6/14
 * function:
 */
class LoginViewModel : ViewModel() {

    val message = MutableLiveData<LoginResultMessage>()
    var mLoginMessage: LoginResultMessage? = null

    fun login(user: User, password: String) {
        viewModelScope.launch(Dispatchers.IO) {
            user.login(object : SaveListener<User>() {
                override fun done(p0: User?, p1: BmobException?) {
                    if (p1 == null) {
                        EMClient.getInstance().login(p0?.username, p0?.objectId, object :
                            EMCallBack {
                            override fun onSuccess() {
                                EMClient.getInstance().groupManager().loadAllGroups()
                                EMClient.getInstance().chatManager().loadAllConversations()
                                L.d("登录聊天服务器成功！")
                                //如果用户记得自己的密码，没有进行注册，还需要在这块保存用户信息
                                if (SPUtils.getString(
                                        MyApplication.getInstance(),
                                        StaticClass.USER_NAME,
                                        ""
                                    ) == ""
                                ) {
                                    //保存用户信息
                                    SPUtils.putString(
                                        MyApplication.getInstance(),
                                        StaticClass.USER_NAME,
                                        p0?.username
                                    )
                                    SPUtils.putString(
                                        MyApplication.getInstance(),
                                        StaticClass.PASSWORD,
                                        password
                                    )
                                }
                                //如果第一次登录成功，则以后每次闪屏页直接跳转到 主页
                                if (SPUtils.getBoolean(
                                        MyApplication.getInstance(),
                                        StaticClass.SHARE_IS_LOGIN,
                                        true
                                    )
                                ) {
                                    SPUtils.putBoolean(
                                        MyApplication.getInstance(),
                                        StaticClass.SHARE_IS_LOGIN,
                                        false
                                    )
                                }
                                mLoginMessage = LoginResultMessage(
                                    LoginResultMessage.CODE_LOGIN_SUCESS,
                                    LoginResultMessage.MESSAGE_LOGIN_SUCESS
                                )
                                message.postValue(mLoginMessage)
                            }

                            override fun onProgress(p0: Int, p1: String?) {
                            }

                            override fun onError(p0: Int, p1: String?) {
                                mLoginMessage = LoginResultMessage(
                                    LoginResultMessage.CODE_LOGIN_FAIL,
                                    LoginResultMessage.MESSAGE_LOGIN_FAIL
                                )
                                message.postValue(mLoginMessage)
                            }
                        })
                    } else {
                        mLoginMessage = LoginResultMessage(
                            LoginResultMessage.CODE_LOGIN_FAIL,
                            LoginResultMessage.MESSAGE_LOGIN_FAIL
                        )
                        message.postValue(mLoginMessage)
                    }
                }
            })
        }
    }
}