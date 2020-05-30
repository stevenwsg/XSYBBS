package com.wsg.xsybbs.module.mynote.model

import androidx.lifecycle.MutableLiveData
import cn.bmob.v3.BmobQuery
import cn.bmob.v3.BmobUser
import cn.bmob.v3.exception.BmobException
import cn.bmob.v3.listener.FindListener
import cn.bmob.v3.listener.UpdateListener
import com.wsg.xsybbs.bean.Note
import com.wsg.xsybbs.module.mynote.bean.DeleteNoteMessage
import com.wsg.xsybbs.module.mynote.bean.MyNoteRequestMessage
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

/**
 * Created by wsg
 * on         2020/5/24
 * function:
 */
class MyNoteModel {

    fun getMyNoteRequestData(liveData: MutableLiveData<List<Note>>, message: MutableLiveData<MyNoteRequestMessage>) {
        val query = BmobQuery<Note>()
        query.addWhereEqualTo("userid", BmobUser.getCurrentUser().objectId)
        query.setLimit(50)
        //按照时间降序
        query.order("-createdAt")

        GlobalScope.launch(Dispatchers.IO) {
            query.findObjects(object : FindListener<Note>() {
                override fun done(p0: MutableList<Note>?, p1: BmobException?) {
                    if (p1 == null) {
                        if (p0?.size!! > 0) {
                            liveData.postValue(p0)
                        } else {
                            message.postValue(MyNoteRequestMessage(MyNoteRequestMessage.CODE_SUC_NO_DATA, MyNoteRequestMessage.MESSAGE_SUC_NO_DATA))
                        }
                    } else {
                        message.postValue(MyNoteRequestMessage(MyNoteRequestMessage.CODE_FAIL, MyNoteRequestMessage.MESSAGE_FAIL))
                    }
                }
            })
        }
    }

    fun deleteNote(note: Note, deleteNoteMessage: MutableLiveData<DeleteNoteMessage>) {
        GlobalScope.launch(Dispatchers.IO) {
            note.delete(object : UpdateListener() {
                override fun done(p0: BmobException?) {
                    if (p0 == null) {
                        val message = DeleteNoteMessage(DeleteNoteMessage.CODE_SUC, DeleteNoteMessage.MESSAGE_SUC)
                        deleteNoteMessage.postValue(message)
                    } else {
                        val message = DeleteNoteMessage(DeleteNoteMessage.CODE_FAIL, DeleteNoteMessage.MESSAGE_FAIL)
                        deleteNoteMessage.postValue(message)
                    }
                }
            })
        }
    }
}