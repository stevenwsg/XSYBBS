package com.wsg.xsybbs.module.login.bean

/**
 * Created by wsg
 * on         2020/6/14
 * function:
 */
data class LoginResultMessage(val code: Int, val message: String) {

    companion object {
        const val CODE_LOGIN_SUCESS = 0
        const val CODE_LOGIN_FAIL = 2

        const val MESSAGE_LOGIN_SUCESS = ""
        const val MESSAGE_LOGIN_FAIL = "登录失败，请检查用户名或密码错误"
    }
}