package com.wsg.xsybbs.activity.usercenter

import android.os.Bundle
import com.wsg.base.BaseActivity
import com.wsg.xsybbs.databinding.ActivityChooseProfileBinding

/**
 * Created by wsg
 * on         2018/7/1.
 * function:
 */
class ChooseProfileActivity : BaseActivity() {

    private lateinit var binding: ActivityChooseProfileBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChooseProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}