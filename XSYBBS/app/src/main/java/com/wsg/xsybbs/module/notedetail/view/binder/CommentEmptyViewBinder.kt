package com.wsg.xsybbs.module.notedetail.view.binder

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.wsg.xsybbs.R
import com.wsg.xsybbs.module.notedetail.bean.CommentEmptyData
import me.drakeet.multitype.ItemViewBinder

/**
 * Create by wangshengguo on 2021/8/1.
 */
class CommentEmptyViewBinder :
    ItemViewBinder<CommentEmptyData, CommentEmptyViewBinder.CommentEmptyHolder>() {

    companion object {
        private const val TAG = "CommentEmptyViewBinder"
    }

    class CommentEmptyHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }

    override fun onCreateViewHolder(
        inflater: LayoutInflater,
        parent: ViewGroup
    ): CommentEmptyHolder {
        return CommentEmptyHolder(inflater.inflate(R.layout.layout_comment_empty, parent, false))
    }

    override fun onBindViewHolder(holder: CommentEmptyHolder, item: CommentEmptyData) {
        Log.d(TAG, "show comment empty view")
    }

}