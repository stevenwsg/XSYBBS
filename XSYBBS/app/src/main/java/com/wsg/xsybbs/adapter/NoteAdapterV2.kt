package com.wsg.xsybbs.adapter

import com.wsg.xsybbs.bean.BanneData
import com.wsg.xsybbs.bean.Note
import com.wsg.xsybbs.module.searchnote.view.NoteItemViewBinder
import com.wsg.xsybbs.module.tourist.view.BannerItemViewBinder
import me.drakeet.multitype.MultiTypeAdapter

/**
 * Create by wangshengguo on 2021/1/17.
 */
class NoteAdapterV2 : MultiTypeAdapter() {
    init {
        register(BanneData::class.java, BannerItemViewBinder())
        register(Note::class.java, NoteItemViewBinder())
    }
}