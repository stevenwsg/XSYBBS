package com.wsg.xsybbs.module.feedback.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cn.bmob.v3.exception.BmobException
import cn.bmob.v3.listener.SaveListener
import com.wsg.xsybbs.module.feedback.bean.FeedBackResultMessage
import com.wsg.xsybbs.module.feedback.bean.Feedback
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * Created by wsg
 * on         2020/5/5
 * function:  反馈MVVM模块改造
 */
class FeedBackViewModel : ViewModel() {

    var messageLiveData = MutableLiveData<FeedBackResultMessage>()
    private var feedBackResultMessage: FeedBackResultMessage? = null

    fun getFeedBackMessage(content: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val feedBack = Feedback(content)
            feedBack.save(object : SaveListener<String>() {
                override fun done(p0: String?, p1: BmobException?) {
                    feedBackResultMessage = if (p1 == null) {
                        FeedBackResultMessage(
                            FeedBackResultMessage.CODE_SUCCESS,
                            FeedBackResultMessage.MESSAGE_SUCCESS
                        )
                    } else {
                        FeedBackResultMessage(
                            FeedBackResultMessage.CODE_ERROR,
                            FeedBackResultMessage.MESSAGE_ERROR
                        )
                    }
                    messageLiveData.postValue(feedBackResultMessage)
                }
            })
        }
    }
}