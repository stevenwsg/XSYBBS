package com.wsg.xsybbs.module.mynote.bean

/**
 * Created by wsg
 * on         2020/5/24
 * function:  请求我的帖子结果
 */
data class MyNoteRequestMessage(val code: Int, val message: String) {

    companion object {
        const val CODE_SUC = 0;
        const val CODE_SUC_NO_DATA = 1;
        const val CODE_FAIL = 2;

        const val MESSAGE_SUC_NO_DATA = "亲，你暂时还没发布帖子"
        const val MESSAGE_FAIL = "亲，数据获取失败，请检查网络"
    }
}