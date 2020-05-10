package com.wsg.xsybbs.module.modifypassword.model

import androidx.lifecycle.MutableLiveData
import cn.bmob.v3.exception.BmobException
import cn.bmob.v3.listener.UpdateListener
import com.wsg.xsybbs.bean.User
import com.wsg.xsybbs.module.modifypassword.bean.ModifyPassWordResultMessage
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

/**
 * Created by wsg
 * on         2020/5/10
 * function:  修改密码Model
 */
class ModifyPassWordModel {

    var modifyPassWordResultMessage : ModifyPassWordResultMessage? = null

     fun modifyPassWord(oldPw : String, newPw : String, result : MutableLiveData<ModifyPassWordResultMessage>) {
        GlobalScope.launch(Dispatchers.IO) {
            User.updateCurrentUserPassword(oldPw, newPw, object : UpdateListener() {
                override fun done(p0: BmobException?) {
                    if (p0 === null) {
                        modifyPassWordResultMessage = ModifyPassWordResultMessage(ModifyPassWordResultMessage.CODE_SUCCESS, ModifyPassWordResultMessage.MESSAGE_SUCCESS)
                        result.postValue(modifyPassWordResultMessage)
                    } else {
                        modifyPassWordResultMessage = ModifyPassWordResultMessage(ModifyPassWordResultMessage.CODE_ERROR, ModifyPassWordResultMessage.MESSAGE_ERROR)
                        result.postValue(modifyPassWordResultMessage)
                    }
                }

            })
        }
    }
}