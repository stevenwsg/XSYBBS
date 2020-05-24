package com.wsg.xsybbs.module.searchnote.view

import com.wsg.xsybbs.bean.Note
import me.drakeet.multitype.MultiTypeAdapter

/**
 * Created by wsg
 * on         2020/5/24
 * function:  搜索帖子Adapter
 */
class NoteAdapter : MultiTypeAdapter() {

    init {
        register(Note::class.java, NoteItemViewBinder())
    }
}