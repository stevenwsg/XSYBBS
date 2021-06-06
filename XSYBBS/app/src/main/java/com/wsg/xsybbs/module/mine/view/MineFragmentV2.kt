package com.wsg.xsybbs.module.mine.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import cn.bmob.v3.BmobUser
import com.wsg.xsybbs.R
import com.wsg.xsybbs.bean.User
import com.wsg.xsybbs.module.mine.model.UserSettingCollection

/**
 * Create by wangshengguo on 2021/5/11.
 */
class MineFragmentV2 : Fragment() {

    private lateinit var mRecyclerView: RecyclerView
    private lateinit var mineAdapter: MineAdapter


    companion object {
        const val TAG = "MineFragmentV2"
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_mine_v2, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d(TAG, "onViewCreated")
        mRecyclerView = view.findViewById(R.id.mineRv)
        mRecyclerView.layoutManager = LinearLayoutManager(context)
        mineAdapter = MineAdapter()

        initData()
    }

    private fun initData() {
        val list = mutableListOf<Any>()
        BmobUser.getCurrentUser(User::class.java)?.let {
            list.add(it)
        }
        list.addAll(UserSettingCollection.getUserSettingCollection())
        mineAdapter.items = list
        mRecyclerView.adapter = mineAdapter
    }
}