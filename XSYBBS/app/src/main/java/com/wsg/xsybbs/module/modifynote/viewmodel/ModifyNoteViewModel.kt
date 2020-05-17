package com.wsg.xsybbs.module.modifynote.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.wsg.xsybbs.bean.Note
import com.wsg.xsybbs.module.modifynote.bean.ModifyNoteResultMessage
import com.wsg.xsybbs.module.modifynote.model.ModifyNoteModel

/**
 * Created by wsg
 * on         2020/5/17
 * function:
 */
class ModifyNoteViewModel : ViewModel() {

    var liveData = MutableLiveData<ModifyNoteResultMessage>()
    var model = ModifyNoteModel()

    fun updateNote(note : Note) {
        model.updateNote(note, liveData)
    }
}