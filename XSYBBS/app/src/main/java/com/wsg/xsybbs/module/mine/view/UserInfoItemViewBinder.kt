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
class UserInfoItemViewBinder{}