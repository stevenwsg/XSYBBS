package com.wsg.base

import android.util.Log
import androidx.lifecycle.ViewModel

/**
 * Create on 2022/3/5.
 *
 * @author wangshengguo.
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