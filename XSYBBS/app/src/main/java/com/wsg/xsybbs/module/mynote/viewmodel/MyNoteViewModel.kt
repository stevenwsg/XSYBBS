package com.wsg.xsybbs.module.mynote.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import cn.bmob.v3.BmobQuery
import cn.bmob.v3.BmobUser
import cn.bmob.v3.exception.BmobException
import cn.bmob.v3.listener.FindListener
import cn.bmob.v3.listener.UpdateListener
import com.wsg.base.BaseViewModel
import com.wsg.xsybbs.bean.Note
import com.wsg.xsybbs.bean.User
import com.wsg.xsybbs.module.mynote.bean.DeleteNoteMessage
import com.wsg.xsybbs.module.mynote.bean.MyNoteRequestMessage
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * Created by wsg
 * on         2020/5/24
 * function:
 */
class MyNoteViewModel : BaseViewModel() {

    val liveData = MutableLiveData<List<Note>>()
    val message = MutableLiveData<MyNoteRequestMessage>()
    val deleteNoteMessage = MutableLiveData<DeleteNoteMessage>()

    fun getMyNoteData() {
        val query = BmobQuery<Note>()
        query.addWhereEqualTo("userid", BmobUser.getCurrentUser(User::class.java).objectId)
        query.setLimit(50)
        //按照时间降序
        query.order("-createdAt")

        viewModelScope.launch(Dispatchers.IO) {
            query.findObjects(object : FindListener<Note>() {
                override fun done(p0: MutableList<Note>?, p1: BmobException?) {
                    if (p1 == null) {
                        if (p0?.size!! > 0) {
                            liveData.postValue(p0)
                        } else {
                            message.postValue(
                                MyNoteRequestMessage(
                                    MyNoteRequestMessage.CODE_SUC_NO_DATA,
                                    MyNoteRequestMessage.MESSAGE_SUC_NO_DATA
                                )
                            )
                        }
                    } else {
                        message.postValue(
                            MyNoteRequestMessage(
                                MyNoteRequestMessage.CODE_FAIL,
                                MyNoteRequestMessage.MESSAGE_FAIL
                            )
                        )
                    }
                }
            })
        }
    }

    fun deleteNote(note: Note) {
        viewModelScope.launch(Dispatchers.IO) {
            note.delete(object : UpdateListener() {
                override fun done(p0: BmobException?) {
                    if (p0 == null) {
                        deleteNoteMessage.postValue(
                            DeleteNoteMessage(
                                DeleteNoteMessage.CODE_SUC,
                                DeleteNoteMessage.MESSAGE_SUC
                            )
                        )
                    } else {
                        deleteNoteMessage.postValue(
                            DeleteNoteMessage(
                                DeleteNoteMessage.CODE_FAIL,
                                DeleteNoteMessage.MESSAGE_FAIL
                            )
                        )
                    }
                }
            })
        }
    }
}