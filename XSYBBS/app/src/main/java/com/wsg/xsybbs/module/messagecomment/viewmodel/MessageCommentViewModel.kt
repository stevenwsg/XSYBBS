package com.wsg.xsybbs.module.messagecomment.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cn.bmob.v3.BmobQuery
import cn.bmob.v3.BmobUser
import cn.bmob.v3.exception.BmobException
import cn.bmob.v3.listener.FindListener
import com.wsg.xsybbs.bean.Comment
import com.wsg.xsybbs.bean.Note
import com.wsg.xsybbs.module.messagecomment.bean.MessageCommentMessage
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * Created by wsg
 * on         2020/5/30
 * function:
 */
class MessageCommentViewModel : ViewModel() {

    val commentList = MutableLiveData<List<Comment>>()
    val commentMessage = MutableLiveData<MessageCommentMessage>()
    private var message: MessageCommentMessage? = null

    fun getCommentInfo() {
        viewModelScope.launch(Dispatchers.IO) {
            val query = BmobQuery<Note>()
            query.addWhereEqualTo("userid", BmobUser.getCurrentUser().objectId)
            query.setLimit(50)
            query.findObjects(object : FindListener<Note>() {
                override fun done(p0: MutableList<Note>?, p1: BmobException?) {
                    if (p1 == null) {
                        if (p0?.size!! > 0) {
                            val notIds = ArrayList<String>()
                            for (note in p0) {
                                notIds.add(note.objectId)
                            }
                            val queryComment = BmobQuery<Comment>()
                            queryComment.addWhereContainedIn("noteid", notIds)
                            queryComment.setLimit(50)
                            queryComment.findObjects(object : FindListener<Comment>() {
                                override fun done(p0: MutableList<Comment>?, p1: BmobException?) {
                                    if (p1 == null) {
                                        if (p0?.size!! > 0) {
                                            commentList.postValue(p0)
                                        } else {
                                            message = MessageCommentMessage(
                                                MessageCommentMessage.CODE_NO_COMMENT,
                                                MessageCommentMessage.MESSAGE_NO_COMMENT
                                            )
                                            commentMessage.postValue(message)
                                        }

                                    } else {
                                        message = MessageCommentMessage(
                                            MessageCommentMessage.CODE_FAIL,
                                            MessageCommentMessage.MESSAGE_FAIL
                                        )
                                        commentMessage.postValue(message)
                                    }
                                }
                            })

                        } else {
                            message = MessageCommentMessage(
                                MessageCommentMessage.CODE_NO_NOTE,
                                MessageCommentMessage.MESSAGE_NO_NOTE
                            )
                            commentMessage.postValue(message)
                        }
                    } else {
                        message = MessageCommentMessage(
                            MessageCommentMessage.CODE_FAIL,
                            MessageCommentMessage.MESSAGE_FAIL
                        )
                        commentMessage.postValue(message)
                    }
                }
            })
        }
    }
}