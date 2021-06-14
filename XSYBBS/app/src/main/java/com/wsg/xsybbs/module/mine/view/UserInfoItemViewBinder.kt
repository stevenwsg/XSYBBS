package com.wsg.xsybbs.module.mine.view

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.wsg.xsybbs.R
import com.wsg.xsybbs.activity.usercenter.MyMessageActivity
import com.wsg.xsybbs.bean.User
import com.wsg.xsybbs.module.modifyinfo.view.ModifyUserInfoActivity
import com.wsg.xsybbs.util.UtilTools
import de.hdodenhof.circleimageview.CircleImageView
import me.drakeet.multitype.ItemViewBinder

/**
 * Create by wangshengguo on 2021/5/11.
 * 用户信息Item
 */
@Deprecated("用户信息不放在Rv中")
class UserInfoItemViewBinder : ItemViewBinder<User, UserInfoItemViewBinder.UserInfoHolder>() {
    class UserInfoHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var profileImage: CircleImageView? = null
        var userName: TextView? = null
        var userAge: TextView? = null
        var userGender: ImageView? = null
        var userDesc: TextView? = null
        var ivMessage: ImageView? = null
        var ivEdit: ImageView? = null

        init {
            profileImage = itemView.findViewById(R.id.profile_image)
            userName = itemView.findViewById(R.id.userName)
            userAge = itemView.findViewById(R.id.userAge)
            userGender = itemView.findViewById(R.id.userGender)
            userDesc = itemView.findViewById(R.id.userDesc)

            ivMessage = itemView.findViewById(R.id.ivMessage)
            ivEdit = itemView.findViewById(R.id.ivEdit)
        }
    }

    override fun onCreateViewHolder(inflater: LayoutInflater, parent: ViewGroup): UserInfoHolder {
        return UserInfoHolder(
            (inflater.inflate(
                R.layout.item_user_info,
                parent,
                false
            ))
        )
    }

    override fun onBindViewHolder(holder: UserInfoHolder, item: User) {
        if (item.image != null) {
            UtilTools.getImage(holder.profileImage?.context, holder.profileImage, item.image)
        }

        holder.userName?.text = item.username
        holder.userAge?.text = item.age.toString() + "岁"
        if (item.isSex) {
            holder.userGender?.setImageResource(R.drawable.female)
        } else {
            holder.userGender?.setImageResource(R.drawable.male)
        }
        holder.userDesc?.text = item.desc

        holder.ivMessage?.setOnClickListener {
            it.context.startActivity(Intent(it.context, MyMessageActivity::class.java))
        }
        holder.ivEdit?.setOnClickListener {
            it.context.startActivity(Intent(it.context, ModifyUserInfoActivity::class.java))
        }
    }
}