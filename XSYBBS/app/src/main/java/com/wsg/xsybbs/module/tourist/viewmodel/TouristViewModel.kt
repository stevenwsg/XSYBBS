package com.wsg.xsybbs.module.tourist.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.wsg.xsybbs.bean.Banne
import com.wsg.xsybbs.bean.Note
import com.wsg.xsybbs.module.tourist.bean.TouristRequestMessage
import com.wsg.xsybbs.module.tourist.model.TouristModel

/**
 * Created by wsg
 * on         2020/5/31
 * function:
 */
class TouristViewModel : ViewModel() {

    val mNotes = MutableLiveData<List<Note>>()
    val mBannes = MutableLiveData<List<Banne>>()
    val message = MutableLiveData<TouristRequestMessage>()
    private val model = TouristModel()

    fun getNotes() {
        model.getNotes(mNotes, message)
    }

    fun getBannes() {
        model.getBannes(mBannes, message)
    }
}