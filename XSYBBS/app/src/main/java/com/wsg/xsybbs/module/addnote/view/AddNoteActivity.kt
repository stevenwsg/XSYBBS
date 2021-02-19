package com.wsg.xsybbs.module.addnote.view

import android.os.Bundle
import android.text.TextUtils
import android.widget.RadioButton
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import cn.bmob.v3.BmobUser
import com.wsg.xsybbs.R
import com.wsg.xsybbs.base.BaseActivity
import com.wsg.xsybbs.bean.Note
import com.wsg.xsybbs.bean.User
import com.wsg.xsybbs.module.addnote.viewmodel.AddNoteViewModel
import es.dmoral.toasty.Toasty
import kotlinx.android.synthetic.main.activity_new_note.*

/**
 * Created by wsg
 * on         2020/5/10
 * function:  发表帖子Activity
 */
class AddNoteActivity : BaseActivity() {

    private var viewModel : AddNoteViewModel? = null
    private var type : String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_note)
        newnote_post.setOnClickListener { postNote() }
        initView()
        initViewModel()
        initObserval()
    }

    private fun initView() {
        type = "吐槽"
        topBar.setTitle(getString(R.string.text_add_note))
        radioGroup.setOnCheckedChangeListener { group, checkedId ->
            type = findViewById<RadioButton>(checkedId).getText().toString()
        }
    }

    private fun initViewModel() {
        viewModel = ViewModelProvider(this).get(AddNoteViewModel::class.java)
    }

    private fun initObserval() {
        viewModel?.message?.observe(this, Observer {
            if (it.code == 0) {
                Toasty.success(this, it.message, Toast.LENGTH_SHORT).show()
                finish()
            } else {
                Toasty.error(this, it.message, Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun postNote() {
        if (!TextUtils.isEmpty(newnote_title.text) && !TextUtils.isEmpty(newnote_content.text)) {
            val note = Note()
            note.userid = BmobUser.getCurrentUser(User::class.java).objectId
            note.image = BmobUser.getCurrentUser(User::class.java).image
            note.typeid = type
            note.top = 0
            note.title = newnote_title.text.toString()
            note.content = newnote_content.text.toString()
            note.zancount = 0
            note.replaycount = 0

            viewModel?.saveNote(note)
        } else {
            Toasty.info(this, "亲，输入框不能为空", Toast.LENGTH_SHORT).show()
        }
    }
}