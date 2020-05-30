package com.wsg.xsybbs.module.mynote.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.wsg.xsybbs.bean.Note
import com.wsg.xsybbs.module.mynote.bean.DeleteNoteMessage
import com.wsg.xsybbs.module.mynote.bean.MyNoteRequestMessage
import com.wsg.xsybbs.module.mynote.model.MyNoteModel

/**
 * Created by wsg
 * on         2020/5/24
 * function:
 */
class MyNoteViewModel : ViewModel() {

    val liveData = MutableLiveData<List<Note>>()
    val message = MutableLiveData<MyNoteRequestMessage>()
    val deleteNoteMessage = MutableLiveData<DeleteNoteMessage>()
    private val model = MyNoteModel()

    fun getMyNoteData() {
        model.getMyNoteRequestData(liveData, message)
    }

    fun deleteNote(note : Note) {
        model.deleteNote(note, deleteNoteMessage)
    }
}