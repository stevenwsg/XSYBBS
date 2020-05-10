package com.wsg.xsybbs.module.modifypassword.bean

import com.wsg.xsybbs.R;
/**
 * Created by wsg
 * on         2020/5/10
 * function:  修改密码信息
 */
data class ModifyPassWordResultMessage (val code : Int, val message: Int){

    companion object {
        const val CODE_SUCCESS : Int = 0
        const val CODE_ERROR : Int = 1

        const val MESSAGE_SUCCESS : Int = R.string.reset_successfully
        const val MESSAGE_ERROR : Int = R.string.reset_failed
    }
}