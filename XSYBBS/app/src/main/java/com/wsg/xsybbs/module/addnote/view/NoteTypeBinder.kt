package com.wsg.xsybbs.module.addnote.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import androidx.recyclerview.widget.RecyclerView
import com.wsg.xsybbs.R
import com.wsg.xsybbs.module.addnote.bean.TypeWrap
import me.drakeet.multitype.ItemViewBinder

/**
 * Create by wangshengguo on 2021/6/27.
 */
class NoteTypeBinder : ItemViewBinder<TypeWrap, NoteTypeBinder.NoteTypeHolder>() {
    private var checkType: String = ""
    private var firstInit = true

    class NoteTypeHolder(rootView: View) : RecyclerView.ViewHolder(rootView) {
        var radioButton: RadioButton? = null

        init {
            radioButton = rootView.findViewById(R.id.btType)
        }
    }

    override fun onCreateViewHolder(inflater: LayoutInflater, parent: ViewGroup): NoteTypeHolder {
        return NoteTypeHolder(inflater.inflate(R.layout.item_note_type, parent, false))
    }

    override fun onBindViewHolder(holder: NoteTypeHolder, item: TypeWrap) {
        // 默认选中第0个
        if (adapter.items.indexOf(item) == 0 && firstInit) {
            (adapter.items[0] as TypeWrap).checked = true
            checkType = (adapter.items[0] as TypeWrap).type.content
            firstInit = false
        }

        holder.radioButton?.isChecked = item.checked
        holder.radioButton?.text = item.type.content
        holder.radioButton?.setOnClickListener {
            val checkPosition = adapter.items.indexOf(item)
            checkType = item.type.content
            for (i in 0 until adapter.itemCount) {
                (adapter.items[i] as TypeWrap).checked = i == checkPosition
            }
            it.post {
                adapter.notifyDataSetChanged();
            }
        }
    }

    fun getCheckString(): String {
        return checkType
    }
}