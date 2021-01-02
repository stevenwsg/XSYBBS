package com.wsg.xsybbs.module.persiondetail.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cn.bmob.v3.BmobQuery
import cn.bmob.v3.exception.BmobException
import cn.bmob.v3.listener.QueryListener
import com.hyphenate.chat.EMClient
import com.wsg.xsybbs.bean.User
import com.wsg.xsybbs.module.persiondetail.bean.AddFriendRequestMessage
import com.wsg.xsybbs.module.persiondetail.bean.PersionDetailRequestMessage
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * Created by wsg
 * on         2020/5/31
 * function:
 */
class PersionDetailViewModel : ViewModel() {

    val mUser = MutableLiveData<User>()
    val mRequestMessage = MutableLiveData<PersionDetailRequestMessage>()
    val mAddFriendRequestMessage = MutableLiveData<AddFriendRequestMessage>()

    fun getUserInfo(uid: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val query = BmobQuery<User>()
            query.getObject(uid, object : QueryListener<User>() {
                override fun done(p0: User?, p1: BmobException?) {
                    if (p1 == null) {
                        mUser.postValue(p0)
                    } else {
                        mRequestMessage.postValue(
                            PersionDetailRequestMessage(
                                PersionDetailRequestMessage.CODE_FAIL,
                                PersionDetailRequestMessage.MESSAGE_FAIL
                            )
                        )
                    }
                }
            })
        }
    }

    fun addFriend(userName: String) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                //参数为要添加的好友的username和添加理由
                EMClient.getInstance().contactManager().addContact(userName, "你好，很高兴认识你")
                mAddFriendRequestMessage.postValue(
                    AddFriendRequestMessage(
                        AddFriendRequestMessage.CODE_SUC,
                        AddFriendRequestMessage.MESSAGE_SUC
                    )
                )
            } catch (e: Exception) {
                mAddFriendRequestMessage.postValue(
                    AddFriendRequestMessage(
                        AddFriendRequestMessage.CODE_FAIL,
                        AddFriendRequestMessage.MESSAGE_FAIL
                    )
                )
            }
        }
    }
}