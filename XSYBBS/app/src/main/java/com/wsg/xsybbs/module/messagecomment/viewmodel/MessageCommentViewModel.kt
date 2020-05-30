package com.wsg.xsybbs.module.messagecomment.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.wsg.xsybbs.bean.Comment
import com.wsg.xsybbs.module.messagecomment.bean.MessageCommentMessage
import com.wsg.xsybbs.module.messagecomment.model.MessageCommentModel

/**
 * Created by wsg
 * on         2020/5/30
 * function:
 */
class MessageCommentViewModel : ViewModel() {

    val commentList = MutableLiveData<List<Comment>>()
    val commentMessage = MutableLiveData<MessageCommentMessage>()
    private val model = MessageCommentModel()

    fun getCommentInfo() {
        model.getCommentInfo(commentList, commentMessage)
    }
}