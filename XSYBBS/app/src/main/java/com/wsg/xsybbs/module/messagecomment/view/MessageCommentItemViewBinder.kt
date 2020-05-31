package com.wsg.xsybbs.module.messagecomment.view

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.wsg.xsybbs.R
import com.wsg.xsybbs.module.persiondetail.view.PersionalDealActivity
import com.wsg.xsybbs.bean.Comment
import me.drakeet.multitype.ItemViewBinder

/**
 * Created by wsg
 * on         2020/5/30
 * function:
 */
class MessageCommentItemViewBinder : ItemViewBinder<Comment, MessageCommentItemViewBinder.CommentViewHolder>() {

    class CommentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var name: TextView? = null
        var content: TextView? = null

        init {
            name = itemView.findViewById(R.id.item_message_comment_tvname)
            content = itemView.findViewById(R.id.item_message_comment_tvcontent)
        }

    }

    override fun onCreateViewHolder(inflater: LayoutInflater, parent: ViewGroup): CommentViewHolder {
        return CommentViewHolder(inflater.inflate(R.layout.item_message_comment, parent, false))
    }

    override fun onBindViewHolder(holder: CommentViewHolder, item: Comment) {
        holder.name?.text = item.username
        holder.content?.text = item.content

        holder.name?.setOnClickListener {
            val intent = Intent(holder.name?.context, PersionalDealActivity::class.java)
            intent.putExtra("id", item.userid)
            holder.name?.context?.startActivity(intent)
        }
    }
}