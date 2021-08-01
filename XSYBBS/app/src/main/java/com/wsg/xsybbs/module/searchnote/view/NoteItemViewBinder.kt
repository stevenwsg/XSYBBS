package com.wsg.xsybbs.module.searchnote.view

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.wsg.xsybbs.R
import com.wsg.xsybbs.activity.note.NoteDetailActivity
import com.wsg.xsybbs.bean.Note
import com.wsg.xsybbs.flutter.MomentDetailActivity
import com.wsg.xsybbs.module.notedetail.view.NoteDetailV2Activity
import com.wsg.xsybbs.util.FlutterRoutes
import com.wsg.xsybbs.util.UtilTools
import de.hdodenhof.circleimageview.CircleImageView
import me.drakeet.multitype.ItemViewBinder
import java.util.Random

/**
 * Created by wsg
 * on         2020/5/24
 * function:
 */
open class NoteItemViewBinder : ItemViewBinder<Note, NoteItemViewBinder.NoteHolder>() {

    companion object {
        private const val TAG = "NoteItemViewBinder"
    }

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
            /**
             * 生成一个随机数， 随机跳转三个版本的帖子详情页面
             * 1、Flutter版
             * 2、JAVA版
             * 3、Kotlin版
             */
            val random = Random()
            when (random.nextInt(3)) {
                0 -> {
                    Log.d(TAG, "enter flutter version")
                    val intent =
                        Intent(viewHolder.containerView?.context, MomentDetailActivity::class.java)
                    intent.action = Intent.ACTION_RUN
                    intent.putExtra(
                        FlutterRoutes.ROUTE,
                        "${FlutterRoutes.MOMENT}?${FlutterRoutes.KEY_NOTE_ID}=${note.objectId}"
                    )
                    viewHolder.containerView?.context?.startActivity(intent)
                }
                1 -> {
                    Log.d(TAG, "enter Java version")
                    val intent =
                        Intent(viewHolder.containerView?.context, NoteDetailActivity::class.java)
                    intent.putExtra("note", note)
                    viewHolder.containerView?.context?.startActivity(intent)
                }
                2 -> {
                    Log.d(TAG, "enter kotlin version")
                    viewHolder.containerView?.context?.let {
                        NoteDetailV2Activity.launch(it, note)
                    }
                }
            }
        }
    }
}