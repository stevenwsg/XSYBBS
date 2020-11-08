package com.wsg.xsybbs.module.tourist.view

import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.wsg.xsybbs.R
import com.wsg.xsybbs.adapter.GlideImageLoader
import com.wsg.xsybbs.base.BaseActivity
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
    private var mBannerList = ArrayList<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tourist_login)
        initView()
        initVm()
        initObserval()
        initData()
    }

    private fun initView() {
        // 设置Banner
        banner.setImageLoader(GlideImageLoader())
        lv_tl_note.layoutManager = LinearLayoutManager(this)
        mAdapter = TlNoteAdapter()
        lv_tl_note.adapter = mAdapter
    }

    private fun initVm() {
        mVm = ViewModelProvider(this).get(TouristViewModel::class.java)
    }

    private fun initObserval() {
        mVm?.mBannes?.observe(this, Observer {
            mBannerList.clear()
            for (banne in it) {
                mBannerList.add(banne.photo)
            }
            refreshBanners()
        })
        mVm?.mNotes?.observe(this, Observer {
            mAdapter?.items = it
            mAdapter?.notifyDataSetChanged()
        })
        mVm?.message?.observe(this, Observer {
            Toasty.error(this, it.message, Toast.LENGTH_LONG).show()
        })
    }

    private fun initData() {
        mBannerList.add("http://202.200.82.150/u/cms/www/201806/27104449b14a.jpg")
        mBannerList.add("http://202.200.82.150/u/cms/www/201805/16111826e3zf.jpg")
        mBannerList.add("http://202.200.82.150/u/cms/www/201710/30114208slub.jpg")
        mBannerList.add("http://202.200.82.150/u/cms/www/201806/26174701kiz0.png")
        refreshBanners()

        mVm?.getNotes()
        mVm?.getBannes()
    }

    private fun refreshBanners() {
        //设置图片集合
        banner.setImages(mBannerList)
        //banner设置方法全部调用完毕时最后调用
        banner.start()
    }
}