package com.wsg.xsybbs.activity.usercenter

import android.os.Bundle
import android.widget.Toast
import cn.bmob.v3.update.BmobUpdateAgent
import com.wsg.xsybbs.R
import com.wsg.xsybbs.base.BaseActivity
import com.wsg.xsybbs.util.UtilTools
import es.dmoral.toasty.Toasty
import kotlinx.android.synthetic.main.activity_update.*

/**
 * Created by wsg
 * on         2018/6/29.
 * function:
 */
class UpDateActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update)
        initView()
    }

    //初始化控件
    private fun initView() {
        update_tv.text = "目前版本" + UtilTools.getVersion(this)
        update_bt.setOnClickListener {
            //检查更新
            BmobUpdateAgent.setUpdateOnlyWifi(false)
            BmobUpdateAgent.update(this)
            Toasty.success(this, "暂时没新版本，敬请期待哦~", Toast.LENGTH_SHORT, true).show()
        }
    }
}