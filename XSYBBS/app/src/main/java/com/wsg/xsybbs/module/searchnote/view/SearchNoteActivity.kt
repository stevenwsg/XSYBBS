package com.wsg.xsybbs.module.searchnote.view

import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.wsg.xsybbs.R
import com.wsg.xsybbs.base.BaseActivity
import com.wsg.xsybbs.module.searchnote.viewmodel.SearchNoteViewModel
import es.dmoral.toasty.Toasty
import kotlinx.android.synthetic.main.activity_search_note.*

/**
 * Created by wsg
 * on         2020/5/17
 * function:  搜索帖子
 */
class SearchNoteActivity : BaseActivity() {

    private var adapter: NoteAdapter? = null
    private var vm: SearchNoteViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_note)
        initView()
        initVm()
        initObserval()
    }

    private fun initView() {
        rv_searchnote.layoutManager = LinearLayoutManager(this);
        adapter = NoteAdapter();
        rv_searchnote.adapter = adapter
        iv_searchnote.setOnClickListener {
            if (!TextUtils.isEmpty(et_searchnote.text.toString())) {
                tv_searchnote.visibility = View.VISIBLE
                tv_searchnote.text = "正在加载，请稍等..."
                adapter?.items?.clear()
                adapter?.notifyDataSetChanged()
                vm?.getSearchNote(et_searchnote.text.toString())
            } else {
                Toasty.info(this, "亲，输入框不能为空", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun initVm() {
        vm = ViewModelProviders.of(this).get(SearchNoteViewModel::class.java)
    }

    private fun initObserval() {
        vm?.liveData?.observe(this, Observer {
            if (it != null && it.isNotEmpty()) {
                tv_searchnote.visibility = View.GONE
                adapter?.items = it
                adapter?.notifyDataSetChanged()
            }
        })
        vm?.message?.observe(this, Observer {
            tv_searchnote.visibility = View.VISIBLE
            tv_searchnote.text = it?.message
        })
    }
}