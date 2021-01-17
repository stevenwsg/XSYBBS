package com.wsg.xsybbs.module.tourist.view

import com.wsg.xsybbs.bean.BanneData
import com.wsg.xsybbs.bean.Note
import me.drakeet.multitype.MultiTypeAdapter

/**
 * Created by wsg
 * on         2020/5/31
 * function:
 */
class TlNoteAdapter : MultiTypeAdapter() {

    init {
        register(Note::class.java, TlNoteItemViewBinder())
        register(BanneData::class.java, BannerItemViewBinder())
    }

}