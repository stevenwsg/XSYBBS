package com.wsg.xsybbs.module.modifypassword.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.wsg.xsybbs.module.modifypassword.bean.ModifyPassWordResultMessage
import com.wsg.xsybbs.module.modifypassword.model.ModifyPassWordModel

/**
 * Created by wsg
 * on         2020/5/10
 * function:  修改密码ViewModel
 */
class ModifyPassWordViewModel : ViewModel() {
    var result = MutableLiveData<ModifyPassWordResultMessage>()
    var model = ModifyPassWordModel()

    fun modifyPassWord(oldPw: String, newPw : String) {
        model.modifyPassWord(oldPw, newPw, result)
    }
}