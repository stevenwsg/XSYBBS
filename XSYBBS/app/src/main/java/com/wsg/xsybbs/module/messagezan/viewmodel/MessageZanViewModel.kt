package com.wsg.xsybbs.module.messagezan.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.wsg.xsybbs.bean.Zan
import com.wsg.xsybbs.module.messagezan.bean.MessageZanMessage
import com.wsg.xsybbs.module.messagezan.model.MessageZanModel

/**
 * Created by wsg
 * on         2020/5/30
 * function:
 */
class MessageZanViewModel: ViewModel() {

    val mZanList = MutableLiveData<List<Zan>>()
    val mZanMessage = MutableLiveData<MessageZanMessage>()
    private val mModel = MessageZanModel()

    fun getZanInfo() {
        mModel.getZanInfo(mZanList, mZanMessage)
    }
}