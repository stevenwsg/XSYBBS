package com.wsg.xsybbs.module.searchnote.model

import androidx.lifecycle.MutableLiveData
import cn.bmob.v3.BmobQuery
import cn.bmob.v3.exception.BmobException
import cn.bmob.v3.listener.FindListener
import com.wsg.xsybbs.bean.Note
import com.wsg.xsybbs.module.searchnote.bean.SearchNoteResultMessage
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

/**
 * Created by wsg
 * on         2020/5/17
 * function:  搜索帖子Model
 */
class SearchNoteModel {

    fun getSearchNote(text: String, liveData: MutableLiveData<List<Note>>, message: MutableLiveData<SearchNoteResultMessage>) {
        val query = BmobQuery<Note>()
        query.addWhereEqualTo("title", text)
        query.setLimit(10)

        GlobalScope.launch(Dispatchers.IO) {
            query.findObjects(object : FindListener<Note>() {
                override fun done(p0: MutableList<Note>?, p1: BmobException?) {
                    if (p1 == null) {
                        if (p0?.size!! > 0) {
                            liveData.postValue(p0)
                        } else {
                            val searchNoteRM = SearchNoteResultMessage(SearchNoteResultMessage.CODE_SUC_NO_DATA, SearchNoteResultMessage.MESSAGE_SUC_NO_DATA)
                            message.postValue(searchNoteRM)
                        }
                    } else {
                        val searchNoteRM = SearchNoteResultMessage(SearchNoteResultMessage.CODE_FAIL, SearchNoteResultMessage.MESSAGE_FAIL)
                        message.postValue(searchNoteRM)
                    }
                }
            })
        }
    }
}