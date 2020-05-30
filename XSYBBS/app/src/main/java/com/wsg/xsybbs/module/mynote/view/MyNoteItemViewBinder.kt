package com.wsg.xsybbs.module.mynote.view

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.wsg.xsybbs.R
import com.wsg.xsybbs.bean.Note
import com.wsg.xsybbs.module.modifynote.view.ModifyMyNoteActivity
import com.wsg.xsybbs.util.UtilTools
import de.hdodenhof.circleimageview.CircleImageView
import me.drakeet.multitype.ItemViewBinder

/**
 * Created by wsg
 * on         2020/5/30
 * function:
 */
class MyNoteItemViewBinder : ItemViewBinder<Note, MyNoteItemViewBinder.MyNoteHolde>() {

    var mDeleteNoteListener: DeleteNoteListener? = null

    class MyNoteHolde(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var note_profile: CircleImageView? = null
        var title: TextView? = null
        var type: TextView? = null
        var time: TextView? = null
        var content: TextView? = null
        var modify: ImageView? = null
        var delete: ImageView? = null

        init {
            note_profile = itemView.findViewById(R.id.item_my_note_profile)
            title = itemView.findViewById(R.id.item_my_note_title)
            type = itemView.findViewById(R.id.item_my_note_type)
            time = itemView.findViewById(R.id.item_my_note_time)
            content = itemView.findViewById(R.id.item_my_note_content)
            modify = itemView.findViewById(R.id.item_my_note_modify)
            delete = itemView.findViewById(R.id.item_my_note_delete)
        }

    }

    override fun onCreateViewHolder(inflater: LayoutInflater, parent: ViewGroup): MyNoteHolde {
        return MyNoteHolde(inflater.inflate(R.layout.item_my_note, parent, false))
    }

    override fun onBindViewHolder(viewHolder: MyNoteHolde, note: Note) {
        if (note.image != null) {
            UtilTools.getImage(viewHolder.note_profile?.context, viewHolder.note_profile, note.getImage())
        } else {
            viewHolder.note_profile?.setImageResource(R.mipmap.logo)
        }

        viewHolder.title?.text = note.title
        viewHolder.type?.text = note.typeid
        viewHolder.time?.text = note.updatedAt.substring(0, 10)
        viewHolder.content?.text = note.content

        viewHolder.modify?.setOnClickListener {
            val intent = Intent(viewHolder.note_profile?.context, ModifyMyNoteActivity::class.java)
            intent.putExtra("id", note)
            viewHolder.note_profile?.context?.startActivity(intent)
        }

        viewHolder.delete?.setOnClickListener {
            if (mDeleteNoteListener != null) {
                mDeleteNoteListener?.onDelete(note)
            }
        }
    }

    interface DeleteNoteListener {
        fun onDelete(note: Note)
    }

}