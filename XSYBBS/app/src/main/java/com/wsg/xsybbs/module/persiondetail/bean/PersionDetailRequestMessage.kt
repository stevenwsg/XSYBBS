package com.wsg.xsybbs.module.persiondetail.bean

/**
 * Created by wsg
 * on         2020/5/31
 * function:
 */
data class PersionDetailRequestMessage(val code: Int, val message: String) {

    companion object {
        const val CODE_SUC = 0
        const val CODE_FAIL = 2

        const val MESSAGE_FAIL = "数据获取失败，请检查网络"
    }
}