package com.wsg.xsybbs.module.tourist.model

import androidx.lifecycle.MutableLiveData
import cn.bmob.v3.BmobQuery
import cn.bmob.v3.exception.BmobException
import cn.bmob.v3.listener.FindListener
import com.wsg.xsybbs.bean.Banne
import com.wsg.xsybbs.bean.Note
import com.wsg.xsybbs.module.tourist.bean.TouristRequestMessage
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

/**
 * Created by wsg
 * on         2020/5/31
 * function:
 */
class TouristModel {
    private var mTouristRequestMessage: TouristRequestMessage? = null

    fun getNotes(mNotes: MutableLiveData<List<Note>>, message: MutableLiveData<TouristRequestMessage>) {
        GlobalScope.launch(Dispatchers.IO) {
            val query = BmobQuery<Note>()
            query.setLimit(10)
            //按时间降序
            query.order("-top")
            query.findObjects(object : FindListener<Note>() {
                override fun done(p0: MutableList<Note>?, p1: BmobException?) {
                    if (p1 == null) {
                        mNotes.postValue(p0)
                    } else {
                        mTouristRequestMessage = TouristRequestMessage(TouristRequestMessage.CODE_FAIL, TouristRequestMessage.MESSAGE_FAIL)
                        message.postValue(mTouristRequestMessage)
                    }
                }
            })
        }
    }

    fun getBannes(mBannes: MutableLiveData<List<Banne>>, message: MutableLiveData<TouristRequestMessage>) {
        GlobalScope.launch(Dispatchers.IO) {
            val query = BmobQuery<Banne>()
            query.setLimit(50)
            //按时间降序
            query.order("-createdAt")
            query.findObjects(object : FindListener<Banne>() {
                override fun done(p0: MutableList<Banne>?, p1: BmobException?) {
                    if (p1 == null) {
                        mBannes.postValue(p0)
                    } else {
                        mTouristRequestMessage = TouristRequestMessage(TouristRequestMessage.CODE_FAIL, TouristRequestMessage.MESSAGE_FAIL)
                        message.postValue(mTouristRequestMessage)
                    }
                }
            })
        }
    }
}