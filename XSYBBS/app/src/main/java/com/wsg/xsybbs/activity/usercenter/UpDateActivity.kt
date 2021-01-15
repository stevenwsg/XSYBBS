package com.wsg.xsybbs.activity.usercenter

import android.os.Bundle
import android.widget.Toast
import cn.bmob.v3.update.BmobUpdateAgent
import com.wsg.xsybbs.base.BaseActivity
import com.wsg.xsybbs.databinding.ActivityUpdateBinding
import com.wsg.xsybbs.util.UtilTools
import es.dmoral.toasty.Toasty

/**
 * Created by wsg
 * on         2018/6/29.
 * function:
 */
class UpDateActivity : BaseActivity() {

    private lateinit var binding: ActivityUpdateBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUpdateBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initView()
    }

    //初始化控件
    private fun initView() {
        binding.apply {
            updateTv.text = "目前版本" + UtilTools.getVersion(this@UpDateActivity)
            updateBt.setOnClickListener {
                //检查更新
                BmobUpdateAgent.setUpdateOnlyWifi(false)
                BmobUpdateAgent.update(this@UpDateActivity)
                Toasty.success(this@UpDateActivity, "暂时没新版本，敬请期待哦~", Toast.LENGTH_SHORT, true)
                    .show()
            }
        }
    }
}