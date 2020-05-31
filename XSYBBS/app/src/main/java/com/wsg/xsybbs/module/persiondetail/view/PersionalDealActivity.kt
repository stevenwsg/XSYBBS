package com.wsg.xsybbs.module.persiondetail.view

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.hyphenate.easeui.EaseConstant
import com.wsg.xsybbs.R
import com.wsg.xsybbs.activity.ChatActivity
import com.wsg.xsybbs.base.BaseActivity
import com.wsg.xsybbs.bean.User
import com.wsg.xsybbs.module.persiondetail.viewmodel.PersionDetailViewModel
import com.wsg.xsybbs.util.UtilTools
import es.dmoral.toasty.Toasty
import kotlinx.android.synthetic.main.activity_persional_deal.*

/**
 * Created by wsg
 * on         2020/5/31
 * function:
 */
class PersionalDealActivity : BaseActivity() {

    private var mVm: PersionDetailViewModel? = null
    private var mUser: User? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_persional_deal)
        initView()
        initVm()
        initObservabl()
        initData()
    }

    private fun initView() {
        btn_add_friend.setOnClickListener {
            btn_add_friend.setOnClickListener {
                mUser?.username?.let { it1 -> mVm?.addFriend(it1) }
            }
        }

        btn_send_message.setOnClickListener {
            val chat = Intent(this, ChatActivity::class.java)
            chat.putExtra(EaseConstant.EXTRA_USER_ID, mUser?.username) //对方账号
            chat.putExtra(EaseConstant.EXTRA_CHAT_TYPE, EaseConstant.CHATTYPE_SINGLE) //单聊模式
            startActivity(chat)
        }
    }

    private fun initVm() {
        mVm = ViewModelProviders.of(this).get(PersionDetailViewModel::class.java)
    }

    private fun initObservabl() {
        mVm?.mUser?.observe(this, Observer {
            mUser = it
            //开始设置数据
            if (it.image != null) {
                UtilTools.getImage(this, show_profile, it.image)
            } else {
                show_profile.setImageResource(R.mipmap.logo)
            }
            show_name.text = it.username
            if (it.isSex) {
                show_sex.setImageResource(R.drawable.male)
            } else {
                show_sex.setImageResource(R.drawable.female)
            }
            show_desc.text = it.desc
        })
        mVm?.mRequestMessage?.observe(this, Observer {
            Toasty.error(this, it.message, Toast.LENGTH_SHORT).show()
        })
        mVm?.mAddFriendRequestMessage?.observe(this, Observer {
            Toasty.info(this, it.message, Toast.LENGTH_SHORT).show()
        })

    }

    private fun initData() {
        mVm?.getUserInfo(intent.getStringExtra("id"))
    }
}