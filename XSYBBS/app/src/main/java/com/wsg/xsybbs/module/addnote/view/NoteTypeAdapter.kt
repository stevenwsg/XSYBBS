package com.wsg.xsybbs.module.addnote.view

import com.wsg.xsybbs.bean.Type
import com.wsg.xsybbs.module.addnote.bean.TypeWrap
import me.drakeet.multitype.MultiTypeAdapter

/**
 * Create by wangshengguo on 2021/6/27.
 */
class NoteTypeAdapter : MultiTypeAdapter() {

    var noteTypeBinder: NoteTypeBinder? = null

    init {
        noteTypeBinder = NoteTypeBinder()
        register(TypeWrap::class.java, noteTypeBinder!!)
    }

    fun getCheckType(): String? {
        return noteTypeBinder?.getCheckString()
    }
}