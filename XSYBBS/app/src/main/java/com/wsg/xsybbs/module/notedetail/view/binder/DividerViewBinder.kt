package com.wsg.xsybbs.module.notedetail.view.binder

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.wsg.xsybbs.R
import com.wsg.xsybbs.module.notedetail.bean.DividerData
import me.drakeet.multitype.ItemViewBinder

/**
 * Create by wangshengguo on 2021/8/1.
 */
class DividerViewBinder : ItemViewBinder<DividerData, DividerViewBinder.DividerHolder>() {

    companion object {
        private const val TAG = "DividerViewBinder"
    }

    class DividerHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }

    override fun onCreateViewHolder(inflater: LayoutInflater, parent: ViewGroup): DividerHolder {
        return DividerHolder(inflater.inflate(R.layout.layout_divider_item, parent, false))
    }

    override fun onBindViewHolder(holder: DividerHolder, item: DividerData) {
        Log.d(TAG, "show divider")
    }
}