package com.wsg.xsybbs.module.notedetail.view

import com.wsg.xsybbs.bean.Note
import me.drakeet.multitype.MultiTypeAdapter

/**
 * Create by wangshengguo on 2021/7/26.
 */
class NoteDetailAdapter : MultiTypeAdapter() {

    init {
        register(Note::class.java, EntireNoteItemViewBinder())
    }
}