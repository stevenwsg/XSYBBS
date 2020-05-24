package com.wsg.xsybbs.module.searchnote.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.wsg.xsybbs.bean.Note
import com.wsg.xsybbs.module.searchnote.bean.SearchNoteResultMessage
import com.wsg.xsybbs.module.searchnote.model.SearchNoteModel

/**
 * Created by wsg
 * on         2020/5/17
 * function:  搜索帖子ViewModel
 */
class SearchNoteViewModel : ViewModel() {

    var liveData = MutableLiveData<List<Note>>()
    var message = MutableLiveData<SearchNoteResultMessage>()

    private var model = SearchNoteModel()

    fun getSearchNote(text : String) {
        model.getSearchNote(text, liveData, message)
    }
}