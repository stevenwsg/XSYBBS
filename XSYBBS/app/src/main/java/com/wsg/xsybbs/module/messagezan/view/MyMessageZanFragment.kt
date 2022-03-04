package com.wsg.xsybbs.module.messagezan.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.wsg.base.BaseFragment
import com.wsg.xsybbs.databinding.ActivityMyMessageZanBinding
import com.wsg.xsybbs.module.messagezan.viewmodel.MessageZanViewModel

/**
 * Created by wsg
 * on         2020/5/30
 * function:
 */
class MyMessageZanFragment : BaseFragment() {

    private var mVm: MessageZanViewModel? = null
    private var mAdapter: MessageZanAdapter? = null
    private var _binding: ActivityMyMessageZanBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = ActivityMyMessageZanBinding.inflate(inflater, container, false)
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
        binding.zanLv.layoutManager = LinearLayoutManager(activity)
        mAdapter = MessageZanAdapter()
        binding.zanLv.adapter = mAdapter
    }

    private fun initVm() {
        mVm = ViewModelProvider(this).get(MessageZanViewModel::class.java)

    }

    private fun initObserval() {
        mVm?.mZanList?.observe(viewLifecycleOwner, Observer {
            binding.zanTv.visibility = View.GONE
            mAdapter?.items = it
            mAdapter?.notifyDataSetChanged()
        })
        mVm?.mZanMessage?.observe(viewLifecycleOwner, Observer {
            binding.zanTv.text = it.message
        })
    }

    private fun initData() {
        mVm?.getZanInfo()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}