package com.wsg.xsybbs.module.mine.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.wsg.xsybbs.R
import com.wsg.xsybbs.module.mine.model.UserSettingInfo
import me.drakeet.multitype.ItemViewBinder

/**
 * Create by wangshengguo on 2021/5/11.
 * 用户信息Item
 */
class UserInfoItemViewBinder :
    ItemViewBinder<UserSettingInfo, UserInfoItemViewBinder.UserInfoViewHolder>() {

    class UserInfoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var rootView: View? = null
        var settingTitle: TextView? = null
        var settingIcon: ImageView? = null

        init {
            rootView = itemView.findViewById(R.id.setting_container)
            settingTitle = itemView.findViewById(R.id.tv_setting)
            settingIcon = itemView.findViewById(R.id.iv_setting)
        }

    }

    override fun onCreateViewHolder(
        inflater: LayoutInflater,
        parent: ViewGroup
    ): UserInfoViewHolder {
        return UserInfoViewHolder((inflater.inflate(R.layout.item_setting_info, parent, false)))
    }

    override fun onBindViewHolder(holder: UserInfoViewHolder, item: UserSettingInfo) {
        holder.settingTitle?.text = item.title
        holder.settingIcon?.setImageResource(item.iconId)
        holder.rootView?.setOnClickListener {

        }
    }
}