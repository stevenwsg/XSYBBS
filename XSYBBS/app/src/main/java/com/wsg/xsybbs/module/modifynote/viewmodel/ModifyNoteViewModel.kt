package com.wsg.xsybbs.module.modifynote.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cn.bmob.v3.exception.BmobException
import cn.bmob.v3.listener.UpdateListener
import com.wsg.xsybbs.bean.Note
import com.wsg.xsybbs.module.modifynote.bean.ModifyNoteResultMessage
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * Created by wsg
 * on         2020/5/17
 * function:
 */
class ModifyNoteViewModel : ViewModel() {

    var liveData = MutableLiveData<ModifyNoteResultMessage>()
    private var message: ModifyNoteResultMessage? = null

    fun updateNote(note: Note) {
        viewModelScope.launch(Dispatchers.IO) {
            note.update(note.objectId, object : UpdateListener() {
                override fun done(p0: BmobException?) {
                    if (p0 == null) {
                        message = ModifyNoteResultMessage(
                            ModifyNoteResultMessage.CODE_SUCCESS,
                            ModifyNoteResultMessage.MESSAGE_SUCCESS
                        )
                        liveData.postValue(message)
                    } else {
                        message = ModifyNoteResultMessage(
                            ModifyNoteResultMessage.CODE_ERROR,
                            ModifyNoteResultMessage.MESSAGE_ERROR
                        )
                        liveData.postValue(message)
                    }
                }
            })
        }
    }
}