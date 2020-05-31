package com.wsg.xsybbs.module.persiondetail.bean

/**
 * Created by wsg
 * on         2020/5/31
 * function:
 */
data class AddFriendRequestMessage(val code: Int, val message: String) {

    companion object {
        const val CODE_SUC = 0
        const val CODE_FAIL = 2

        const val MESSAGE_SUC = "添加成功"
        const val MESSAGE_FAIL = "添加失败，你和对方已经是好友"
    }
}