package com.wsg.xsybbs.module.notedetail.view.binder

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.wsg.xsybbs.R
import com.wsg.xsybbs.module.notedetail.bean.CommentTitleData
import me.drakeet.multitype.ItemViewBinder

/**
 * Create by wangshengguo on 2021/8/1.
 */
class CommentTitleViewBinder :
    ItemViewBinder<CommentTitleData, CommentTitleViewBinder.CommentTitleHolder>() {

    class CommentTitleHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var commentCount: TextView? = null

        init {
            commentCount = itemView.findViewById(R.id.commentCount)
        }
    }

    override fun onCreateViewHolder(
        inflater: LayoutInflater,
        parent: ViewGroup
    ): CommentTitleHolder {
        return CommentTitleHolder(inflater.inflate(R.layout.layout_comment_title, parent, false))
    }

    override fun onBindViewHolder(holder: CommentTitleHolder, item: CommentTitleData) {
        holder.commentCount?.text = item.count.toString()
    }
}