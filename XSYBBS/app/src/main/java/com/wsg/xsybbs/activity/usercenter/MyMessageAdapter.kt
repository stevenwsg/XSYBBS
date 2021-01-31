package com.wsg.xsybbs.activity.usercenter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.wsg.xsybbs.module.messagecomment.view.MyMessageCommentFragment
import com.wsg.xsybbs.module.messagezan.view.MyMessageZanFragment

/**
 * Create by wangshengguo on 2021/1/31.
 */
class MyMessageAdapter(activity: FragmentActivity) : FragmentStateAdapter(activity) {

    private val ITEM_COUNT = 2

    override fun getItemCount(): Int {
        return ITEM_COUNT
    }

    override fun createFragment(position: Int): Fragment {
        return if (position == 0) {
            MyMessageZanFragment()
        } else {
            MyMessageCommentFragment()
        }
    }
}