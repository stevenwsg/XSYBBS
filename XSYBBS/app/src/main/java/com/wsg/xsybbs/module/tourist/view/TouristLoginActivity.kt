package com.wsg.xsybbs.module.tourist.view

import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.wsg.xsybbs.R
import com.wsg.xsybbs.base.BaseActivity
import com.wsg.xsybbs.bean.BanneData
import com.wsg.xsybbs.module.tourist.viewmodel.TouristViewModel
import es.dmoral.toasty.Toasty
import kotlinx.android.synthetic.main.activity_tourist_login.*

/**
 * Created by wsg
 * on         2020/5/31
 * function:
 */
class TouristLoginActivity : BaseActivity() {

    private var mVm: TouristViewModel? = null
    private var mAdapter: TlNoteAdapter? = null
    private val dataList = ArrayList<Any>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tourist_login)
        initView()
        initVm()
        initObserval()
        initData()
    }

    private fun initView() {
        lv_tl_note.layoutManager = LinearLayoutManager(this)
        mAdapter = TlNoteAdapter()
        lv_tl_note.adapter = mAdapter
    }

    private fun initVm() {
        mVm = ViewModelProvider(this).get(TouristViewModel::class.java)
    }

    private fun initObserval() {
        mVm?.mBanes?.observe(this, Observer {
            dataList.add(0, BanneData(it))
            mAdapter?.items = dataList
            mAdapter?.notifyItemInserted(0)
        })
        mVm?.mNotes?.observe(this, Observer {
            dataList.addAll(it)
            mAdapter?.items = dataList
            mAdapter?.notifyDataSetChanged()
        })
        mVm?.message?.observe(this, Observer {
            Toasty.error(this, it.message, Toast.LENGTH_LONG).show()
        })
    }

    private fun initData() {
        mVm?.getNotes()
        mVm?.getBannes()
    }
}