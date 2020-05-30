package com.wsg.xsybbs.module.messagezan.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.wsg.xsybbs.R
import com.wsg.xsybbs.bean.Zan
import me.drakeet.multitype.ItemViewBinder

/**
 * Created by wsg
 * on         2020/5/31
 * function:
 */
class MessageZanItemViewBinder : ItemViewBinder<Zan, MessageZanItemViewBinder.MessageZanViewHolder>() {

    class MessageZanViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var name: TextView? = null
        var content: TextView? = null

        init {
            name = itemView.findViewById(R.id.item_message_zan_tvname)
            content = itemView.findViewById(R.id.item_message_zan_title)
        }
    }

    override fun onCreateViewHolder(inflater: LayoutInflater, parent: ViewGroup): MessageZanViewHolder {
        return MessageZanViewHolder(inflater.inflate(R.layout.item_message_zan, parent, false))
    }

    override fun onBindViewHolder(holder: MessageZanViewHolder, item: Zan) {
        holder.name?.text = item.username
        holder.content?.text = item.notename
    }

}