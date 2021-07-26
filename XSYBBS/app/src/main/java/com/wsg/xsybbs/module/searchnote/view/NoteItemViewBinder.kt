package com.wsg.xsybbs.module.searchnote.view

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.wsg.xsybbs.R
import com.wsg.xsybbs.bean.Note
import com.wsg.xsybbs.flutter.MomentDetailActivity
import com.wsg.xsybbs.util.FlutterRoutes
import com.wsg.xsybbs.util.UtilTools
import de.hdodenhof.circleimageview.CircleImageView
import me.drakeet.multitype.ItemViewBinder

/**
 * Created by wsg
 * on         2020/5/24
 * function:
 */
open class NoteItemViewBinder : ItemViewBinder<Note, NoteItemViewBinder.NoteHolder>() {

    class NoteHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var containerView: View? = null
        var note_profile: CircleImageView? = null
        var title: TextView? = null
        var type: TextView? = null
        var time: TextView? = null
        var content: TextView? = null
        var zan: ImageView? = null
        var zancount: TextView? = null
        var replay: ImageView? = null
        var replaycount: TextView? = null

        init {
            containerView = itemView
            note_profile = itemView.findViewById(R.id.item_note_profile)
            title = itemView.findViewById(R.id.item_note_title)
            type = itemView.findViewById(R.id.item_note_type)
            time = itemView.findViewById(R.id.item_note_time)
            content = itemView.findViewById(R.id.item_note_content)
            zan = itemView.findViewById(R.id.item_note_zan)
            zancount = itemView.findViewById(R.id.item_note_zancount)
            replay = itemView.findViewById(R.id.item_note_replay)
            replaycount = itemView.findViewById(R.id.item_note_replaycount)
        }
    }

    override fun onCreateViewHolder(inflater: LayoutInflater, parent: ViewGroup): NoteHolder {
        return NoteHolder(inflater.inflate(R.layout.item_note, parent, false))
    }

    override fun onBindViewHolder(viewHolder: NoteHolder, note: Note) {
        if (note.getImage() != null) {
            UtilTools.getImage(
                viewHolder.note_profile?.context,
                viewHolder.note_profile,
                note.getImage()
            )
        } else {
            viewHolder.note_profile?.setImageResource(R.mipmap.logo)
        }
        viewHolder.title?.text = note.title
        viewHolder.type?.text = note.typeid
        viewHolder.time?.text = note.updatedAt.substring(0, 10)
        viewHolder.content?.text = note.content
        viewHolder.zancount?.text = note.zancount.toString()
        viewHolder.replaycount?.text = note.replaycount.toString()

        viewHolder.containerView?.setOnClickListener {
//            val intent = Intent(viewHolder.containerView?.context, NoteDetailActivity::class.java)
//            intent.putExtra("note", note)
//            viewHolder.containerView?.context?.startActivity(intent)

            val intent = Intent(viewHolder.containerView?.context, MomentDetailActivity::class.java)
            intent.action = Intent.ACTION_RUN
            intent.putExtra(
                FlutterRoutes.ROUTE,
                "${FlutterRoutes.MOMENT}?${FlutterRoutes.KEY_NOTE_ID}=${note.objectId}"
            )
            viewHolder.containerView?.context?.let {
                it.startActivity(intent)
            }
        }
    }

}