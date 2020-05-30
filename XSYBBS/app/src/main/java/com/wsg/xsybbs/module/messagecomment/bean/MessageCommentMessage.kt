package com.wsg.xsybbs.module.messagecomment.bean

/**
 * Created by wsg
 * on         2020/5/30
 * function:
 */
data class MessageCommentMessage(val code: Int, val message: String) {

    companion object {
        const val CODE_SUC = 0;
        const val CODE_SUC_NO_DATA = 1;
        const val CODE_FAIL = 2;
        const val CODE_NO_NOTE = 3;
        const val CODE_NO_COMMENT = 4;

        const val MESSAGE_SUC = "你暂时未收到赞"
        const val MESSAGE_FAIL = "数据获取失败，请检查网络"
        const val MESSAGE_NO_NOTE = "你还没有发帖，暂无评论"
        const val MESSAGE_NO_COMMENT = "你暂时未收到评论"
    }
}