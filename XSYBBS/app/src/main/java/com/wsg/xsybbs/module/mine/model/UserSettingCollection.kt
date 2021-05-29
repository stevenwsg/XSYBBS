package com.wsg.xsybbs.module.mine.model

import com.wsg.xsybbs.R

/**
 * Create by wangshengguo on 2021/5/16.
 */
class UserSettingCollection {
    companion object {
        @JvmStatic
        fun getUserSettingCollection(): ArrayList<UserSettingInfo> {
            var list = arrayListOf<UserSettingInfo>()

            list.add(UserSettingInfo("我的帖子", R.drawable.note_pressed, UserSetting.MY_NOTE))
            list.add(UserSettingInfo("修改密码", R.drawable.modify_password_pressed, UserSetting.MODIFY_PASSWORD))
            list.add(UserSettingInfo("意见反馈", R.drawable.feedback_pressed, UserSetting.FEED_BACK))
            list.add(UserSettingInfo("关于", R.drawable.about_pressed, UserSetting.ABOUT))
            list.add(UserSettingInfo("检查更新", R.drawable.update_pressed, UserSetting.UPDATE))
            list.add(UserSettingInfo("注销登录", R.drawable.sign_out_pressed, UserSetting.UNREGISTER))
            list.add(UserSettingInfo("退出程序", R.drawable.exit_pressed, UserSetting.Exit))

            return list
        }
    }
}