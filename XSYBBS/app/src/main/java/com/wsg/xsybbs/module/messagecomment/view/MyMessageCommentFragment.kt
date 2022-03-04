package com.wsg.xsybbs.module.messagecomment.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.wsg.base.BaseFragment
import com.wsg.xsybbs.databinding.ActivityMyMessageCommentBinding
import com.wsg.xsybbs.module.messagecomment.viewmodel.MessageCommentViewModel

/**
 * Created by wsg
 * on         2020/5/30
 * function: 我收到的评论改造
 */
class MyMessageCommentFragment : BaseFragment() {

    private var mVm: MessageCommentViewModel? = null
    private var mAdapter: MessageCommentAdapter? = null
    private var _binding: ActivityMyMessageCommentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = ActivityMyMessageCommentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initVm()
        initObserval()
        initData()
    }

    private fun initView() {
        binding.commentLv.layoutManager = LinearLayoutManager(activity)
        mAdapter = MessageCommentAdapter()
        binding.commentLv.adapter = mAdapter
    }

    private fun initVm() {
        mVm = ViewModelProvider(this).get(MessageCommentViewModel::class.java)
    }

    private fun initObserval() {
        mVm?.commentList?.observe(viewLifecycleOwner, Observer {
            binding.commentTv.visibility = View.GONE
            mAdapter?.items = it
            mAdapter?.notifyDataSetChanged()
        })
        mVm?.commentMessage?.observe(viewLifecycleOwner, Observer {
            binding.commentTv.text = it.message
        })

    }

    private fun initData() {
        mVm?.getCommentInfo()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}