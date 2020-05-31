package com.wsg.xsybbs.module.tourist.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.wsg.xsybbs.R
import com.wsg.xsybbs.bean.Note
import com.wsg.xsybbs.util.UtilTools
import de.hdodenhof.circleimageview.CircleImageView
import me.drakeet.multitype.ItemViewBinder

/**
 * Created by wsg
 * on         2020/5/31
 * function:
 */
class TlNoteItemViewBinder : ItemViewBinder<Note, TlNoteItemViewBinder.TlNoteHolder>() {

    class TlNoteHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var note_profile: CircleImageView? = null
        var title: TextView? = null
        var type: TextView? = null
        var time: TextView? = null
        var content: TextView? = null

        init {
            note_profile = itemView.findViewById(R.id.item_tl_note_profile)
            title = itemView.findViewById(R.id.item_tl_note_title)
            type = itemView.findViewById(R.id.item_tl_note_type)
            time = itemView.findViewById(R.id.item_tl_note_time)
            content = itemView.findViewById(R.id.item_tl_note_content)
        }

    }

    override fun onCreateViewHolder(inflater: LayoutInflater, parent: ViewGroup): TlNoteHolder {
        return TlNoteHolder(inflater.inflate(R.layout.item_tl_note, parent, false))
    }

    override fun onBindViewHolder(viewHolder: TlNoteHolder, note: Note) {
        if (note.image != null) {
            UtilTools.getImage(viewHolder.note_profile?.context, viewHolder.note_profile, note.image)
        } else {
            viewHolder.note_profile?.setImageResource(R.mipmap.logo)
        }
        viewHolder.title?.text = note.title
        viewHolder.type?.text = note.typeid
        viewHolder.time?.text = note.updatedAt.substring(0, 10)
        viewHolder.content?.text = note.content
    }
}