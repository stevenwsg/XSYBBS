package com.wsg.xsybbs.module.feedback.bean

import cn.bmob.v3.BmobObject
import cn.bmob.v3.BmobUser
import com.wsg.xsybbs.bean.User

/**
 * Created by wsg
 * on         2018/7/1.
 * function:  反馈实体
 */
data class Feedback (var Content : String,
                    var deviceType : String = "android",
                    var userid : String = BmobUser.getCurrentUser(User::class.java).objectId) : BmobObject()