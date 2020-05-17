package com.wsg.xsybbs.module.modifynote.bean

/**
 * Created by wsg
 * on         2020/5/17
 * function:  修改帖子结果
 */
data class ModifyNoteResultMessage(val code : Int,  val message: String) {

    companion object {
        const val CODE_SUCCESS : Int = 0
        const val CODE_ERROR : Int = 1

        const val MESSAGE_SUCCESS : String = "发布成功"
        const val MESSAGE_ERROR : String = "发布失败，请检查网络"
    }
}