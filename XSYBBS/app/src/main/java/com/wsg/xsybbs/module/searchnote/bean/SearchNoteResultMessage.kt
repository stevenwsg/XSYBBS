package com.wsg.xsybbs.module.searchnote.bean

/**
 * Created by wsg
 * on         2020/5/24
 * function:  搜索帖子结果
 */
data class SearchNoteResultMessage(val code: Int, val message: String) {

    companion object {
        const val CODE_SUC = 0
        const val CODE_SUC_NO_DATA = 1
        const val CODE_FAIL = 2

        const val MESSAGE_SUC_NO_DATA = "查找成功，暂无此类记录"
        const val MESSAGE_FAIL = "搜索失败，请检查网络"
    }
}