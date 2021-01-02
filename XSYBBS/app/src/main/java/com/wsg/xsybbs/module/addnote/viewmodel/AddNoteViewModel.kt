package com.wsg.xsybbs.module.addnote.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cn.bmob.v3.exception.BmobException
import cn.bmob.v3.listener.SaveListener
import com.wsg.xsybbs.bean.Note
import com.wsg.xsybbs.module.addnote.bean.AddNoteResultMessage
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * Created by wsg
 * on         2020/5/10
 * function:
 */
class AddNoteViewModel : ViewModel() {

    var message = MutableLiveData<AddNoteResultMessage>()

    fun saveNote(note: Note) {
        viewModelScope.launch(Dispatchers.IO) {
            note.save(object : SaveListener<String>() {
                override fun done(p0: String?, p1: BmobException?) {
                    var resultMessage = if (p1 == null) {
                        AddNoteResultMessage(
                            AddNoteResultMessage.CODE_SUCCESS,
                            AddNoteResultMessage.MESSAGE_SUCCESS
                        )
                    } else {
                        AddNoteResultMessage(
                            AddNoteResultMessage.CODE_ERROR,
                            AddNoteResultMessage.MESSAGE_ERROR
                        )
                    }
                    message.postValue(resultMessage)
                }

            })
        }
    }
}