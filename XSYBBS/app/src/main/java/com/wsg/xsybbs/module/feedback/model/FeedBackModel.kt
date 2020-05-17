package com.wsg.xsybbs.module.feedback.model

import androidx.lifecycle.MutableLiveData
import cn.bmob.v3.exception.BmobException
import cn.bmob.v3.listener.SaveListener
import com.wsg.xsybbs.module.feedback.bean.FeedBackResultMessage
import com.wsg.xsybbs.module.feedback.bean.Feedback
import kotlinx.coroutines.*

/**
 * Created by wsg
 * on         2020/5/5
 * function:  反馈模块MVVM改造
 */
class FeedBackModel {
    var feedBackResultMessage: FeedBackResultMessage? = null

    fun feedBack(content: String, liveData: MutableLiveData<FeedBackResultMessage>) {
        GlobalScope.launch(Dispatchers.IO) {
            saveFeedBack(Feedback(content), liveData)
        }
    }

    private suspend fun saveFeedBack(feedback: Feedback, liveData: MutableLiveData<FeedBackResultMessage>) = withContext(Dispatchers.IO) {
        feedback.save(object : SaveListener<String>() {
            override fun done(p0: String?, p1: BmobException?) {
                if (p1 === null) {
                    feedBackResultMessage = FeedBackResultMessage(FeedBackResultMessage.CODE_SUCCESS, FeedBackResultMessage.MESSAGE_SUCCESS)
                    liveData.postValue(feedBackResultMessage)
                } else {
                    feedBackResultMessage = FeedBackResultMessage(FeedBackResultMessage.CODE_ERROR, FeedBackResultMessage.MESSAGE_ERROR)
                    liveData.postValue(feedBackResultMessage)
                }
            }
        })
    }
}