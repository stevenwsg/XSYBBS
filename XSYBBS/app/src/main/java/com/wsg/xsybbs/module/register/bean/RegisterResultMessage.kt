package com.wsg.xsybbs.module.register.bean

/**
 * Created by wsg
 * on         2020/5/31
 * function:
 */
data class RegisterResultMessage(val code : Int, val message: String) {

    companion object {
        const val CODE_SUC = 0
        const val CODE_FAIL = 1

        const val MESSAGE_SUC = "注册成功,请登录"
        const val MESSAGE_FAIL = "注册失败，请修改用户名重试或检查邮箱格式是否正确"
    }
}