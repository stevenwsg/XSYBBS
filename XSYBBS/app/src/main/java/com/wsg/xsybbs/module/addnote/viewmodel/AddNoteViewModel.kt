package com.wsg.xsybbs.module.addnote.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.wsg.xsybbs.bean.Note
import com.wsg.xsybbs.module.addnote.bean.AddNoteResultMessage
import com.wsg.xsybbs.module.addnote.model.AddNoteModel

/**
 * Created by wsg
 * on         2020/5/10
 * function:
 */
class AddNoteViewModel : ViewModel() {

    var message = MutableLiveData<AddNoteResultMessage>()
    var model = AddNoteModel()

    fun saveNote(note : Note) {
        model.saveNote(note, message)
    }
}