package com.wsg.xsybbs.module.mine.view

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.hyphenate.chat.EMClient
import com.wsg.xsybbs.MainActivity
import com.wsg.xsybbs.R
import com.wsg.xsybbs.activity.usercenter.AboutActivity
import com.wsg.xsybbs.activity.usercenter.UpDateActivity
import com.wsg.xsybbs.bean.User
import com.wsg.xsybbs.module.feedback.view.FeedBackActivity
import com.wsg.xsybbs.module.login.view.LoginActivity
import com.wsg.xsybbs.module.mine.model.UserSetting
import com.wsg.xsybbs.module.mine.model.UserSettingInfo
import com.wsg.xsybbs.module.modifypassword.view.ModifyPasswordActivity
import com.wsg.xsybbs.module.mynote.view.MyNoteActivity
import com.wsg.xsybbs.threadpool.MyThreadPool
import com.wsg.xsybbs.util.SPUtils
import com.wsg.xsybbs.util.StaticClass
import me.drakeet.multitype.ItemViewBinder
import kotlin.system.exitProcess

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
        holder.rootView?.setOnClickListener { view ->
            when (item.setting) {
                UserSetting.MY_NOTE -> {
                    view.context?.let {
                        it.startActivity(Intent(it, MyNoteActivity::class.java))
                    }
                }
                UserSetting.MODIFY_PASSWORD -> {
                    view.context?.let {
                        it.startActivity(Intent(it, ModifyPasswordActivity::class.java))
                    }
                }
                UserSetting.FEED_BACK -> {
                    view.context?.let {
                        it.startActivity(Intent(it, FeedBackActivity::class.java))
                    }
                }
                UserSetting.ABOUT -> {
                    view.context?.let {
                        it.startActivity(Intent(it, AboutActivity::class.java))
                    }
                }
                UserSetting.UPDATE -> {
                    view.context?.let {
                        it.startActivity(Intent(it, UpDateActivity::class.java))
                    }
                }
                UserSetting.UNREGISTER -> {
                    User.logOut()
                    EMClient.getInstance().logout(true)

                    SPUtils.putBoolean(view.context, StaticClass.SHARE_IS_LOGIN, true)
                    view.context.apply {
                        startActivity(Intent(this, LoginActivity::class.java))
                    }
                    (view.context as? MainActivity)?.finish()
                }
                UserSetting.Exit -> {
                    //关闭线程池
                    MyThreadPool.shutDown()
                    exitProcess(0)
                }
            }
        }
    }
}