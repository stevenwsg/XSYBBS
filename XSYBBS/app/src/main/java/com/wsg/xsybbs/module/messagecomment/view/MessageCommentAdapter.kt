package com.wsg.xsybbs.module.messagecomment.view

import com.wsg.xsybbs.bean.Comment
import me.drakeet.multitype.MultiTypeAdapter

/**
 * Created by wsg
 * on         2020/5/30
 * function:   RecyclerView Adapter
 */
class MessageCommentAdapter : MultiTypeAdapter() {

    init {
        register(Comment::class.java, MessageCommentItemViewBinder())
    }
}