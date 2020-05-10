package com.wsg.xsybbs.module.addnote.model

import androidx.lifecycle.MutableLiveData
import cn.bmob.v3.exception.BmobException
import cn.bmob.v3.listener.SaveListener
import com.wsg.xsybbs.bean.Note
import com.wsg.xsybbs.module.addnote.bean.AddNoteResultMessage
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

/**
 * Created by wsg
 * on         2020/5/10
 * function:  发表帖子Model
 */
class AddNoteModel {

    fun saveNote(note : Note, liveData: MutableLiveData<AddNoteResultMessage>) {
        GlobalScope.launch(Dispatchers.IO) {
            note.save(object : SaveListener<String>() {
                override fun done(p0: String?, p1: BmobException?) {
                    var message : AddNoteResultMessage? = null
                    if (p1 == null) {
                        message = AddNoteResultMessage(AddNoteResultMessage.CODE_SUCCESS, AddNoteResultMessage.MESSAGE_SUCCESS)
                    } else {
                        message = AddNoteResultMessage(AddNoteResultMessage.CODE_ERROR, AddNoteResultMessage.MESSAGE_ERROR)
                    }
                    liveData.postValue(message)
                }
            })
        }
    }
}