package com.wsg.xsybbs.module.mine.view

import com.wsg.xsybbs.module.mine.model.UserSettingInfo
import me.drakeet.multitype.MultiTypeAdapter

/**
 * Create by wangshengguo on 2021/5/11.
 * 我的Recycler Adapter
 */
class MineAdapter : MultiTypeAdapter() {

    init {
        register(UserSettingInfo::class.java, UserInfoItemViewBinder())
    }
}