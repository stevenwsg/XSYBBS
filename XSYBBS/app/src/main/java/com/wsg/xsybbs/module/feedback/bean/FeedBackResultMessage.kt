package com.wsg.xsybbs.module.feedback.bean

/**
 * Created by wsg
 * on         2020/5/5
 * function:  反馈结果实体
 */

/*
 * code 0 代表成功
 * code 1 代表失败
 */
data class FeedBackResultMessage (val code : Int, val message: String) {

    companion object {
        const val CODE_SUCCESS : Int = 0
        const val CODE_ERROR : Int = 1

        const val MESSAGE_SUCCESS : String = "反馈成功~~~"
        const val MESSAGE_ERROR : String = "反馈失败~~~,请检查网络"
    }
}
