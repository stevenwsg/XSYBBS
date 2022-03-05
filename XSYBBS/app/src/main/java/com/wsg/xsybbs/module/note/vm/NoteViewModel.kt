package com.wsg.xsybbs.module.note.vm

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import cn.bmob.v3.BmobQuery
import cn.bmob.v3.exception.BmobException
import cn.bmob.v3.listener.FindListener
import com.wsg.base.BaseViewModel
import com.wsg.xsybbs.bean.Banne
import com.wsg.xsybbs.bean.Type
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * Create by wangshengguo on 2021/6/14.
 */
class NoteViewModel : BaseViewModel() {
    companion object {
        const val TAG = "NoteViewModel"
    }

    val banes = MutableLiveData<List<Banne>>()
    val typeList = MutableLiveData<List<Type>>()

    fun getBannes() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val query = BmobQuery<Banne>()
                query.setLimit(5)
                query.order("-createdAt")
                query.findObjects(object : FindListener<Banne>() {
                    override fun done(p0: MutableList<Banne>?, p1: BmobException?) {
                        if (p1 == null) {
                            Log.d(TAG, "getBannes suc, size: ${p0?.size}")
                            banes.postValue(p0)
                        } else {
                            Log.e(TAG, "getBannes error: $p1")
                        }
                    }

                })
            }
        }
    }

    fun getTypeList() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val query = BmobQuery<Type>()
                query.setLimit(5)
                query.order("createdAt")
                query.findObjects(object : FindListener<Type>() {
                    override fun done(p0: MutableList<Type>?, p1: BmobException?) {
                        if (p1 == null) {
                            Log.d(TAG, "getType suc, size: ${p0?.size}")
                            typeList.postValue(p0)
                        } else {
                            Log.e(TAG, "getTypeList error: $p1")
                        }
                    }

                })
            }
        }
    }
}