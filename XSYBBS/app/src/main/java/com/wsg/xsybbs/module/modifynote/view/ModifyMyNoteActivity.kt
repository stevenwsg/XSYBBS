package com.wsg.xsybbs.module.modifynote.view

import android.os.Bundle
import android.text.TextUtils
import android.widget.RadioButton
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.wsg.base.BaseActivity
import com.wsg.xsybbs.R
import com.wsg.xsybbs.bean.Note
import com.wsg.xsybbs.module.modifynote.viewmodel.ModifyNoteViewModel
import es.dmoral.toasty.Toasty
import kotlinx.android.synthetic.main.activity_modify_my_note.*

/**
 * Created by wsg
 * on         2020/5/17
 * function:  修改帖子MVVM改造
 */
class ModifyMyNoteActivity : BaseActivity() {

    var viewModel: ModifyNoteViewModel? = null
    var note: Note? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_modify_my_note)
        initData()
        initView()
        initVM()
        initObsearVal()
    }

    private fun initData() {
        note = intent?.getSerializableExtra("id") as Note
        modify_note_title.setText(note?.title)
        modify_note_content.setText(note?.content)
        for (i in 0..modify_radioGroup.childCount) {
            if ((modify_radioGroup.getChildAt(i) as RadioButton).text.toString().trim() == note?.typeid.toString().trim()) {
                (modify_radioGroup.getChildAt(i) as RadioButton).isChecked = true
                break
            }
        }
    }

    private fun initView() {
        topBar.setTitle(getString(R.string.text_modify_my_note))
        modify_radioGroup.setOnCheckedChangeListener { group, checkedId ->
            note?.setTypeid((findViewById<RadioButton>(checkedId)).text.toString())
        }
        modify_note.setOnClickListener {
            if (!TextUtils.isEmpty(modify_note_title.text) && !TextUtils.isEmpty(modify_note_content.text)) {
                note?.title = modify_note_title.text.toString()
                note?.content = modify_note_content.text.toString()
                viewModel?.updateNote(note!!)
            } else {
                Toasty.info(this, "亲，输入框不能为空", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun initVM() {
        viewModel = ViewModelProvider(this).get(ModifyNoteViewModel::class.java)
    }

    private fun initObsearVal() {
        viewModel?.liveData?.observe(this, Observer { t ->
            if (t?.code == 0) {
                Toasty.success(this, t.message, Toast.LENGTH_SHORT).show()
                finish()
            } else {
                t?.message?.let { Toasty.error(this, it, Toast.LENGTH_SHORT).show() }
            }
        })
    }
}