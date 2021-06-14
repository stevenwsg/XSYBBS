package com.wsg.xsybbs.module.mine.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import cn.bmob.v3.BmobUser
import com.wsg.xsybbs.R
import com.wsg.xsybbs.activity.usercenter.MyMessageActivity
import com.wsg.xsybbs.bean.User
import com.wsg.xsybbs.databinding.FragmentMineV2Binding
import com.wsg.xsybbs.module.mine.model.UserSettingCollection
import com.wsg.xsybbs.module.modifyinfo.view.ModifyUserInfoActivity
import com.wsg.xsybbs.util.UtilTools

/**
 * Create by wangshengguo on 2021/5/11.
 */
class MineFragmentV2 : Fragment() {

    companion object {
        const val TAG = "MineFragmentV2"
    }

    private lateinit var mineAdapter: MineAdapter

    private var _binding: FragmentMineV2Binding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMineV2Binding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d(TAG, "onViewCreated")
        binding.mineRv.layoutManager = LinearLayoutManager(context)
        mineAdapter = MineAdapter()

        initHeader()
        initData()
    }

    private fun initHeader() {
        BmobUser.getCurrentUser(User::class.java)?.let {
            it.image?.apply {
                UtilTools.getImage(context, binding.userInfo.profileImage, this)
            }
            binding.userInfo.userName.text = it.username
            val age = it.age.toString() + "岁"
            binding.userInfo.userAge.text = age
            binding.userInfo.userGender
                .setImageResource(if (it.isSex) R.drawable.female else R.drawable.male)

            binding.userInfo.ivMessage.setOnClickListener {
                context?.startActivity(Intent(context, MyMessageActivity::class.java))
            }
            binding.userInfo.ivEdit.setOnClickListener {
                context?.startActivity(Intent(context, ModifyUserInfoActivity::class.java))
            }
        } ?: let {
            binding.userInfo.header.visibility = View.GONE
        }
    }

    private fun initData() {
        val list = mutableListOf<Any>()
        list.addAll(UserSettingCollection.getUserSettingCollection())
        mineAdapter.items = list
        binding.mineRv.adapter = mineAdapter
    }
}