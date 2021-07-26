package com.wsg.xsybbs.module.notedetail.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.wsg.xsybbs.base.BaseActivity
import com.wsg.xsybbs.bean.Comment
import com.wsg.xsybbs.bean.Note
import com.wsg.xsybbs.databinding.ActivityNoteDetailV2Binding
import com.wsg.xsybbs.module.notedetail.viewmodel.NoteDetailViewModel

/**
 * 帖子详情页面
 */

class NoteDetailV2Activity : BaseActivity() {

    companion object {
        private const val TAG = "NoteDetailV2Activity"
        private const val KEY_NOT = "note"

        @JvmStatic
        fun launch(context: Context, note: Note) {
            val intent = Intent(context, NoteDetailV2Activity::class.java)
            intent.putExtra(KEY_NOT, note)
            context.startActivity(intent)
        }
    }

    private lateinit var binding: ActivityNoteDetailV2Binding
    private lateinit var adapter: NoteDetailAdapter

    private var note: Note? = null
    private var viewModel: NoteDetailViewModel? = null
    private var list = mutableListOf<Any?>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNoteDetailV2Binding.inflate(layoutInflater)
        setContentView(binding.root)
        initNoteData()
        initView()
        initViewModel()
        initObservable()
        initLikeState()
        getCommentData()
    }

    private fun initNoteData() {
        note = intent?.getSerializableExtra("note") as Note?
    }

    fun initView() {
        binding.topBar.setTitle("帖子详情")
        binding.noteDetail.layoutManager = LinearLayoutManager(this)
        list.add(0, note)
        adapter = NoteDetailAdapter()
        adapter.items = list
        binding.noteDetail.adapter = adapter
    }

    private fun initViewModel() {
        viewModel = ViewModelProvider(this).get(NoteDetailViewModel::class.java)
    }

    private fun initObservable() {

    }

    // 获取用户点赞状态
    private fun initLikeState() {
        viewModel?.getLikeState()
    }

    // 获取评论信息
    private fun getCommentData() {
        viewModel?.getCommentInfo()
    }

    // 更新点赞状态
    private fun updateLikeState(like: Boolean) {
        viewModel?.updateLikeState(like)
    }

    // 添加评论
    private fun addComment(comment: Comment) {
        viewModel?.addComment(comment)
    }

}