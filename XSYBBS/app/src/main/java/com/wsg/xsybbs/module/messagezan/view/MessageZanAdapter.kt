package com.wsg.xsybbs.module.messagezan.view

import com.wsg.xsybbs.bean.Zan
import me.drakeet.multitype.MultiTypeAdapter

/**
 * Created by wsg
 * on         2020/5/30
 * function:
 */
class MessageZanAdapter : MultiTypeAdapter() {
    init {
        register(Zan::class.java, MessageZanItemViewBinder())
    }
}