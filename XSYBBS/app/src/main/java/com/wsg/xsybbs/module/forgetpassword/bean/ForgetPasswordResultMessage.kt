package com.wsg.xsybbs.module.forgetpassword.bean

/**
 * Created by wsg
 * on         2020/5/17
 * function:
 */
class ForgetPasswordResultMessage(val code : Int, val message: String) {

    companion object {
        const val CODE_SUCCESS: Int = 0
        const val CODE_ERROR: Int = 1

        const val MESSAGE_SUCCESS: String = "重置密码请求成功，请到邮箱进行密码重置操作"
        const val MESSAGE_ERROR: String = "邮箱发送失败,请检查邮件输入是否正确或者该邮件是否注册过此应用"
    }
}