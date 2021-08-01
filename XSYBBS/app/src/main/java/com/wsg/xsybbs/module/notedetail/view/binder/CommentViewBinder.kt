package com.wsg.xsybbs.module.notedetail.view.binder

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.wsg.xsybbs.R
import com.wsg.xsybbs.bean.Comment
import com.wsg.xsybbs.module.persiondetail.view.PersionalDealActivity
import me.drakeet.multitype.ItemViewBinder

/**
 * Create by wangshengguo on 2021/8/1.
 */
class CommentViewBinder : ItemViewBinder<Comment, CommentViewBinder.CommentHolder>() {

    class CommentHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var userName: TextView? = null
        var userComment: TextView? = null

        init {
            userName = itemView.findViewById(R.id.item_comment_name)
            userComment = itemView.findViewById(R.id.item_comment_content)
        }

    }

    override fun onCreateViewHolder(inflater: LayoutInflater, parent: ViewGroup): CommentHolder {
        return CommentHolder(inflater.inflate(R.layout.item_comment, parent, false))
    }

    override fun onBindViewHolder(holder: CommentHolder, item: Comment) {
        holder.userName?.text = item.username
        holder.userComment?.text = item.content

        holder.userName?.setOnClickListener {
            val intent = Intent(it.context, PersionalDealActivity::class.java)
            intent.putExtra("id", item.userid)
            it.context.startActivity(intent)
        }
    }
}