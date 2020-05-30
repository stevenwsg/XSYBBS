package com.wsg.xsybbs.module.mynote.view

import com.wsg.xsybbs.bean.Note
import me.drakeet.multitype.MultiTypeAdapter

/**
 * Created by wsg
 * on         2020/5/24
 * function:
 */
class MyNoteAdapter(mDeleteNoteListener : MyNoteItemViewBinder.DeleteNoteListener) : MultiTypeAdapter() {

    init {
        val viewBinder = MyNoteItemViewBinder()
        viewBinder.mDeleteNoteListener = mDeleteNoteListener
        register(Note::class.java, viewBinder)
    }
}