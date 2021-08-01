package com.wsg.xsybbs.module.notedetail.view.adapter

import com.wsg.xsybbs.bean.Comment
import com.wsg.xsybbs.bean.Note
import com.wsg.xsybbs.module.notedetail.bean.CommentEmptyData
import com.wsg.xsybbs.module.notedetail.bean.CommentTitleData
import com.wsg.xsybbs.module.notedetail.bean.DividerData
import com.wsg.xsybbs.module.notedetail.view.binder.*
import me.drakeet.multitype.MultiTypeAdapter

/**
 * Create by wangshengguo on 2021/7/26.
 */
class NoteDetailAdapter : MultiTypeAdapter() {

    init {
        register(Note::class.java, EntireNoteItemViewBinder())
        register(DividerData::class.java, DividerViewBinder())
        register(CommentTitleData::class.java, CommentTitleViewBinder())
        register(CommentEmptyData::class.java, CommentEmptyViewBinder())
        register(Comment::class.java, CommentViewBinder())
    }
}