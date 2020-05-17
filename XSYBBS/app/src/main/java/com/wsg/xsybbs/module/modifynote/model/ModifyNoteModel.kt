package com.wsg.xsybbs.module.modifynote.model

import androidx.lifecycle.MutableLiveData
import cn.bmob.v3.exception.BmobException
import cn.bmob.v3.listener.UpdateListener
import com.wsg.xsybbs.bean.Note
import com.wsg.xsybbs.module.modifynote.bean.ModifyNoteResultMessage
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

/**
 * Created by wsg
 * on         2020/5/17
 * function:  修改帖子Model
 */
class ModifyNoteModel {

    var message: ModifyNoteResultMessage? = null

    fun updateNote(note: Note, liveData: MutableLiveData<ModifyNoteResultMessage>) {
        GlobalScope.launch(Dispatchers.IO) {
            note.update(note.objectId, object : UpdateListener() {
                override fun done(p0: BmobException?) {
                    if (p0 == null) {
                        message = ModifyNoteResultMessage(ModifyNoteResultMessage.CODE_SUCCESS, ModifyNoteResultMessage.MESSAGE_SUCCESS)
                        liveData.postValue(message)
                    } else {
                        message = ModifyNoteResultMessage(ModifyNoteResultMessage.CODE_ERROR, ModifyNoteResultMessage.MESSAGE_ERROR)
                        liveData.postValue(message)
                    }
                }
            })
        }
    }
}