package com.wsg.xsybbs.module.notedetail.view.binder

import com.wsg.xsybbs.bean.Note
import com.wsg.xsybbs.module.searchnote.view.NoteItemViewBinder

/**
 * Create by wangshengguo on 2021/7/26.
 * 在原有Item的基础上，添加点赞事件
 */
class EntireNoteItemViewBinder : NoteItemViewBinder() {

    override fun onBindViewHolder(viewHolder: NoteHolder, note: Note) {
        super.onBindViewHolder(viewHolder, note)
        viewHolder.containerView?.setOnClickListener(null)

        viewHolder.zan?.setOnClickListener {
            // 点赞点击事件
        }
    }
}