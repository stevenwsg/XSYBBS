package com.wsg.xsybbs.module.mine.view

import cn.bmob.v3.BmobUser
import com.wsg.xsybbs.module.mine.model.UserSettingInfo
import me.drakeet.multitype.MultiTypeAdapter

/**
 * Create by wangshengguo on 2021/5/11.
 * 我的Recycler Adapter
 */
class MineAdapter : MultiTypeAdapter() {

    init {
//        register(BmobUser::class.java, UserInfoItemViewBinder)
        register(UserSettingInfo::class.java, UserSettingItemViewBinder())
    }
}