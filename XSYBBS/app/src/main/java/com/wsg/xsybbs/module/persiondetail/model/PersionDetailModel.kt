package com.wsg.xsybbs.module.persiondetail.model

import androidx.lifecycle.MutableLiveData
import cn.bmob.v3.BmobQuery
import cn.bmob.v3.exception.BmobException
import cn.bmob.v3.listener.QueryListener
import com.hyphenate.chat.EMClient
import com.wsg.xsybbs.bean.User
import com.wsg.xsybbs.module.persiondetail.bean.AddFriendRequestMessage
import com.wsg.xsybbs.module.persiondetail.bean.PersionDetailRequestMessage
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

/**
 * Created by wsg
 * on         2020/5/31
 * function:
 */
class PersionDetailModel {

    private var message: PersionDetailRequestMessage? = null
    private var mAddFriendRequestMessage: AddFriendRequestMessage? = null

    fun getUserInfo(uid: String, mUser: MutableLiveData<User>, requestMessage: MutableLiveData<PersionDetailRequestMessage>) {
        GlobalScope.launch(Dispatchers.IO) {
            val query = BmobQuery<User>()
            query.getObject(uid, object : QueryListener<User>() {
                override fun done(p0: User?, p1: BmobException?) {
                    if (p1 == null) {
                        mUser.postValue(p0)
                    } else {
                        message = PersionDetailRequestMessage(PersionDetailRequestMessage.CODE_FAIL, PersionDetailRequestMessage.MESSAGE_FAIL)
                        requestMessage.postValue(message)
                    }
                }
            })
        }
    }

    fun addFriend(userName: String, addFriendRequestMessage: MutableLiveData<AddFriendRequestMessage>) {
        GlobalScope.launch(Dispatchers.IO) {
            try {
                //参数为要添加的好友的username和添加理由
                EMClient.getInstance().contactManager().addContact(userName, "你好，很高兴认识你")
                mAddFriendRequestMessage = AddFriendRequestMessage(AddFriendRequestMessage.CODE_SUC, AddFriendRequestMessage.MESSAGE_SUC)
                addFriendRequestMessage.postValue(mAddFriendRequestMessage)
            } catch (e: Exception) {
                mAddFriendRequestMessage = AddFriendRequestMessage(AddFriendRequestMessage.CODE_FAIL, AddFriendRequestMessage.MESSAGE_FAIL)
                addFriendRequestMessage.postValue(mAddFriendRequestMessage)
            }
        }
    }
}