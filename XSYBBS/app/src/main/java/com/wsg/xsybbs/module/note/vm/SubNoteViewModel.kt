package com.wsg.xsybbs.module.note.vm

import android.util.Log
import androidx.lifecycle.MutableLiveData
import cn.bmob.v3.BmobQuery
import cn.bmob.v3.exception.BmobException
import cn.bmob.v3.listener.FindListener
import com.wsg.xsybbs.base.BaseViewModel
import com.wsg.xsybbs.bean.Note

/**
 * Create by wangshengguo on 2021/6/14.
 */
class SubNoteViewModel : BaseViewModel() {
    companion object {
        const val TAG = "SubNoteViewModel"
    }

    val notes = MutableLiveData<List<Note>>()

    fun getNotes(type: String) {
        val query = BmobQuery<Note>()
        query.setLimit(10)
        query.order("-updatedAt")
        query.addWhereEqualTo("typeid", type)

        query.findObjects(object : FindListener<Note>() {
            override fun done(p0: MutableList<Note>?, p1: BmobException?) {
                if (p1 == null) {
                    Log.d(TAG, "getNotes suc, size: ${p0?.size}")
                    notes.postValue(p0)
                } else {
                    Log.e(TAG, "getNotes error: $p1")
                }

            }
        })
    }
}