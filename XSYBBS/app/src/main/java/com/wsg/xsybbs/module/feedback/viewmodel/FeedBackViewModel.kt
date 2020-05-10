package com.wsg.xsybbs.module.feedback.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.wsg.xsybbs.module.feedback.bean.FeedBackResultMessage
import com.wsg.xsybbs.module.feedback.model.FeedBackModel
/**
 * Created by wsg
 * on         2020/5/5
 * function:  反馈MVVM模块改造
 */
class FeedBackViewModel : ViewModel() {

    var messageLiveData = MutableLiveData<FeedBackResultMessage>()
    val mFeedBackModel = FeedBackModel()

    fun getFeedBackMessage(content: String) {
        mFeedBackModel.feedBack(content, messageLiveData)
    }
}