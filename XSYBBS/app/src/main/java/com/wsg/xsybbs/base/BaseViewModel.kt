package com.wsg.xsybbs.base

import android.util.Log
import androidx.lifecycle.ViewModel

/**
 * Create by wangshengguo on 2021/1/3.
 */
open class BaseViewModel : ViewModel() {

    companion object {
        private const val TAG = "BaseViewModel"
    }

    override fun onCleared() {
        Log.d(TAG, "${javaClass::getName} onCleared")
        super.onCleared()
    }
}