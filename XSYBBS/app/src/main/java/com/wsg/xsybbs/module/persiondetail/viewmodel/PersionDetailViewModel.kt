package com.wsg.xsybbs.module.persiondetail.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.wsg.xsybbs.bean.User
import com.wsg.xsybbs.module.persiondetail.bean.AddFriendRequestMessage
import com.wsg.xsybbs.module.persiondetail.bean.PersionDetailRequestMessage
import com.wsg.xsybbs.module.persiondetail.model.PersionDetailModel

/**
 * Created by wsg
 * on         2020/5/31
 * function:
 */
class PersionDetailViewModel : ViewModel() {

    val mUser = MutableLiveData<User>()
    val mRequestMessage = MutableLiveData<PersionDetailRequestMessage>()
    val mAddFriendRequestMessage = MutableLiveData<AddFriendRequestMessage>()
    private val model = PersionDetailModel()

    fun getUserInfo(uid: String) {
        model.getUserInfo(uid, mUser, mRequestMessage)
    }

    fun addFriend(userName: String) {
        model.addFriend(userName, mAddFriendRequestMessage)
    }
}