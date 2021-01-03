package com.wsg.xsybbs.module.modifyinfo.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import cn.bmob.v3.exception.BmobException
import cn.bmob.v3.listener.UpdateListener
import com.wsg.xsybbs.base.BaseViewModel
import com.wsg.xsybbs.bean.User
import com.wsg.xsybbs.module.modifyinfo.ModifyInfoResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * Created by wsg
 * on         2020/6/25
 * function:
 */
class ModifyInfoViewModel : BaseViewModel() {

    val modifyResult = MutableLiveData<ModifyInfoResult>()

    fun updateUserInfo(user: User) {
        viewModelScope.launch(Dispatchers.IO) {
            user.update(user.objectId, object : UpdateListener() {
                override fun done(p0: BmobException?) {
                    var message: ModifyInfoResult?
                    if (p0 == null) {
                        message = ModifyInfoResult(
                            ModifyInfoResult.CODE_SUCCESS,
                            ModifyInfoResult.MESSAGE_SUCCESS
                        )
                        modifyResult.postValue(message)
                    } else {
                        message = ModifyInfoResult(
                            ModifyInfoResult.CODE_ERROR,
                            ModifyInfoResult.MESSAGE_ERROR
                        )
                        modifyResult.postValue(message)
                    }
                }
            })
        }
    }
}