package com.wsg.xsybbs.module.messagezan.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import cn.bmob.v3.BmobQuery
import cn.bmob.v3.BmobUser
import cn.bmob.v3.exception.BmobException
import cn.bmob.v3.listener.FindListener
import com.wsg.xsybbs.base.BaseViewModel
import com.wsg.xsybbs.bean.Note
import com.wsg.xsybbs.bean.User
import com.wsg.xsybbs.bean.Zan
import com.wsg.xsybbs.module.messagezan.bean.MessageZanMessage
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * Created by wsg
 * on         2020/5/30
 * function:
 */
class MessageZanViewModel : BaseViewModel() {

    val mZanList = MutableLiveData<List<Zan>>()
    val mZanMessage = MutableLiveData<MessageZanMessage>()
    private var message: MessageZanMessage? = null

    fun getZanInfo() {
        viewModelScope.launch(Dispatchers.IO) {
            val query = BmobQuery<Note>()
            query.addWhereEqualTo("userid", BmobUser.getCurrentUser(User::class.java).objectId)
            query.setLimit(50)
            query.findObjects(object : FindListener<Note>() {
                override fun done(p0: MutableList<Note>?, p1: BmobException?) {
                    if (p1 == null) {
                        if (p0?.size!! > 0) {
                            val mNoteIds = ArrayList<String>()
                            for (note in p0) {
                                mNoteIds.add(note.objectId)
                            }
                            val queryZan = BmobQuery<Zan>()
                            queryZan.addWhereContainedIn("noteid", mNoteIds)
                            queryZan.addWhereEqualTo("status", true)
                            queryZan.setLimit(50)
                            queryZan.findObjects(object : FindListener<Zan>() {
                                override fun done(p0: MutableList<Zan>?, p1: BmobException?) {
                                    if (p1 == null) {
                                        if (p0?.size!! > 0) {
                                            mZanList.postValue(p0)
                                        } else {
                                            message = MessageZanMessage(
                                                MessageZanMessage.CODE_NO_ZAN,
                                                MessageZanMessage.MESSAGE_NO_ZAN
                                            )
                                            mZanMessage.postValue(message)
                                        }
                                    } else {
                                        message = MessageZanMessage(
                                            MessageZanMessage.CODE_FAIL,
                                            MessageZanMessage.MESSAGE_FAIL
                                        )
                                        mZanMessage.postValue(message)
                                    }
                                }
                            })
                        } else {
                            message = MessageZanMessage(
                                MessageZanMessage.CODE_NO_NOTE,
                                MessageZanMessage.MESSAGE_NO_NOTE
                            )
                            mZanMessage.postValue(message)
                        }
                    } else {
                        message = MessageZanMessage(
                            MessageZanMessage.CODE_FAIL,
                            MessageZanMessage.MESSAGE_FAIL
                        )
                        mZanMessage.postValue(message)
                    }
                }
            })
        }
    }
}