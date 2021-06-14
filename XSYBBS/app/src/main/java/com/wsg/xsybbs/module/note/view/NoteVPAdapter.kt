package com.wsg.xsybbs.module.note.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

/**
 * Create by wangshengguo on 2021/6/14.
 */
class NoteVPAdapter(activity: FragmentActivity, private val typeList: List<String>) :
    FragmentStateAdapter(activity) {

    override fun getItemCount(): Int {
        return typeList.size
    }

    override fun createFragment(position: Int): Fragment {
        val fragment = SubNoteFragment()
        val arguments = Bundle()
        arguments.putString(SubNoteFragment.TAG, typeList[position])
        fragment.arguments = arguments
        return fragment
    }
}