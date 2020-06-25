package com.wsg.xsybbs.module.modifyinfo.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.wsg.xsybbs.bean.User
import com.wsg.xsybbs.module.modifyinfo.ModifyInfoResult
import com.wsg.xsybbs.module.modifyinfo.model.ModifyInfoModel

/**
 * Created by wsg
 * on         2020/6/25
 * function:
 */
class ModifyInfoViewModel : ViewModel() {

    val modifyResult = MutableLiveData<ModifyInfoResult>()
    private val model: ModifyInfoModel = ModifyInfoModel()

    fun updateUserInfo(user: User) {
        model.updateUserInfo(user, modifyResult)
    }
}