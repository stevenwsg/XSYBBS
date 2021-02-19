package com.wsg.xsybbs.module.mynote.view

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.wsg.xsybbs.R
import com.wsg.xsybbs.base.BaseActivity
import com.wsg.xsybbs.bean.Note
import com.wsg.xsybbs.module.mynote.bean.DeleteNoteMessage
import com.wsg.xsybbs.module.mynote.viewmodel.MyNoteViewModel
import es.dmoral.toasty.Toasty
import kotlinx.android.synthetic.main.activity_mynote.*

/**
 * Created by wsg
 * on         2020/5/24
 * function:  我的帖子改造
 */
class MyNoteActivity : BaseActivity() {

    private var vm: MyNoteViewModel? = null
    private var adapter: MyNoteAdapter? = null
    private var note: Note? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mynote)
        initView()
        initVm()
        initObserval()
        initData()
    }

    private fun initView() {
        tv_mynote.setText("正在加载，请稍等...")
        topBar.setTitle(getString(R.string.text_mynote))
        rv_my_note.layoutManager = LinearLayoutManager(this)
        adapter = MyNoteAdapter(object : MyNoteItemViewBinder.DeleteNoteListener {
            override fun onDelete(deleteNote: Note) {
                note = deleteNote
                vm?.deleteNote(deleteNote)
            }

        })
        rv_my_note.adapter = adapter
    }

    private fun initVm() {
        vm = ViewModelProvider(this).get(MyNoteViewModel::class.java)
    }

    private fun initData() {
        vm?.getMyNoteData()
    }

    private fun initObserval() {
        vm?.liveData?.observe(this, Observer {
            tv_mynote.visibility = View.GONE
            adapter?.items = it
            adapter?.notifyDataSetChanged()
        })
        vm?.message?.observe(this, Observer {
            tv_mynote.text = it.message
        })
        vm?.deleteNoteMessage?.observe(this, Observer {
            if (it.code == DeleteNoteMessage.CODE_SUC) {
                val deletePosition = adapter?.items?.indexOf(note)
                adapter?.items?.removeAt(deletePosition!!)
                adapter?.notifyItemRemoved(deletePosition!!)
                if (adapter?.itemCount == 0) {
                    tv_mynote.visibility = View.VISIBLE
                    tv_mynote.text = "亲，你已经没有帖子了"
                }
            }
            Toasty.info(this, it?.message.toString(), Toast.LENGTH_SHORT).show()
        })

    }
}