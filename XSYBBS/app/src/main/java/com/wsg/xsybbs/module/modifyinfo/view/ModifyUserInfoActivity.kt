package com.wsg.xsybbs.module.modifyinfo.view

import android.graphics.Color
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import cn.bmob.v3.BmobUser
import com.bumptech.glide.Glide
import com.wsg.xsybbs.R
import com.wsg.xsybbs.base.BaseActivity
import com.wsg.xsybbs.bean.User
import com.wsg.xsybbs.module.modifyinfo.ModifyInfoResult
import com.wsg.xsybbs.module.modifyinfo.viewmodel.ModifyInfoViewModel
import com.wsg.xsybbs.util.UtilTools
import com.wyp.avatarstudio.AvatarStudio
import es.dmoral.toasty.Toasty
import kotlinx.android.synthetic.main.activity_modify_persional_information.*
import java.io.File

/**
 * Created by wsg
 * on         2020/6/25
 * function:
 */
class ModifyUserInfoActivity : BaseActivity() {

    private var mUser: User? = null
    private var mVm: ModifyInfoViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_modify_persional_information)
        initView()
        initVm()
        initObserve()
    }

    private fun initView() {
        topBar.setTitle(getString(R.string.edit_information))
        mUser = BmobUser.getCurrentUser(User::class.java)
        et_username.setText(mUser?.username)
        et_sex.setText(if (mUser!!.isSex) getString(R.string.text_boy) else getString(R.string.text_girl_f))
        et_age.setText(mUser?.age.toString())
        et_desc.setText(mUser?.desc)
        if (mUser?.image != null) {
            UtilTools.getImage(this, modify_profile_image, mUser!!.image)
        }

        modify_profile_image.setOnClickListener {
            AvatarStudio.Builder(this)
                    .needCrop(true) //是否裁剪，默认裁剪
                    .setTextColor(Color.BLUE)
                    .dimEnabled(true) //背景是否dim 默认true
                    .setAspect(1, 1) //裁剪比例 默认1：1
                    .setOutput(200, 200) //裁剪大小 默认200*200
                    .setText("打开相机", "从相册中选取", "取消")
                    .show { uri -> //uri为图片路径
                        Glide.with(this).load(File(uri))
                                .into(modify_profile_image)
                    }
        }

        btn_update_ok.setOnClickListener {
            if (!TextUtils.isEmpty(et_username.text)
                    && !TextUtils.isEmpty(et_sex.text)
                    && !TextUtils.isEmpty(et_desc.text)) {
                mUser?.username = et_username.text.toString()
                val stringAge = et_age.text.toString()
                mUser?.age = Integer.parseInt(stringAge)
                mUser?.isSex = et_sex.text.toString() == getString(R.string.text_boy)
                mUser?.desc = (if (!TextUtils.isEmpty(et_desc.text.toString())) et_desc.text.toString() else getString(R.string.text_nothing))
                mVm?.updateUserInfo(mUser!!)
            } else {
                Toasty.info(this, getString(R.string.text_tost_empty), Toast.LENGTH_SHORT, true).show()

            }
        }
    }

    private fun initVm() {
        mVm = ViewModelProvider(this).get(ModifyInfoViewModel::class.java)
    }

    private fun initObserve() {
        mVm?.modifyResult?.observe(this, Observer {
            if (it.code == ModifyInfoResult.CODE_SUCCESS) {
                //修改成功
                UtilTools.putImageToShare(this, modify_profile_image)
                Toasty.success(this, getString(R.string.text_editor_success), Toast.LENGTH_SHORT, true).show()
                finish()
            } else {
                Toasty.error(this, getString(R.string.text_editor_failure), Toast.LENGTH_SHORT, true).show()
            }
        })
    }
}