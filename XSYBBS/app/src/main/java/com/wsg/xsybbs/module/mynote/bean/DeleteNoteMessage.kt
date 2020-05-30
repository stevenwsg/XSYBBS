package com.wsg.xsybbs.module.mynote.bean

/**
 * Created by wsg
 * on         2020/5/30
 * function:  删除帖子结果
 */
data class DeleteNoteMessage(val code: Int, val message: String) {

    companion object {
        const val CODE_SUC = 0;
        const val CODE_SUC_NO_DATA = 1;
        const val CODE_FAIL = 2;

        const val MESSAGE_SUC = "删除成功"
        const val MESSAGE_FAIL = "删除失败，请检查网络"
    }
}