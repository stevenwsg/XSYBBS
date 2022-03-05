package com.wsg.xsybbs.module.modifypassword.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import cn.bmob.v3.exception.BmobException
import cn.bmob.v3.listener.UpdateListener
import com.wsg.base.BaseViewModel
import com.wsg.xsybbs.bean.User
import com.wsg.xsybbs.module.modifypassword.bean.ModifyPassWordResultMessage
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * Created by wsg
 * on         2020/5/10
 * function:  修改密码ViewModel
 */
class ModifyPassWordViewModel : BaseViewModel() {
    var result = MutableLiveData<ModifyPassWordResultMessage>()

    fun modifyPassWord(oldPw: String, newPw: String) {
        viewModelScope.launch(Dispatchers.IO) {
            User.updateCurrentUserPassword(oldPw, newPw, object : UpdateListener() {
                var modifyPassWordResultMessage: ModifyPassWordResultMessage? = null
                override fun done(p0: BmobException?) {
                    modifyPassWordResultMessage = if (p0 === null) {
                        ModifyPassWordResultMessage(
                            ModifyPassWordResultMessage.CODE_SUCCESS,
                            ModifyPassWordResultMessage.MESSAGE_SUCCESS
                        )
                    } else {
                        ModifyPassWordResultMessage(
                            ModifyPassWordResultMessage.CODE_ERROR,
                            ModifyPassWordResultMessage.MESSAGE_ERROR
                        )
                    }
                    result.postValue(modifyPassWordResultMessage)
                }
            })
        }
    }
}