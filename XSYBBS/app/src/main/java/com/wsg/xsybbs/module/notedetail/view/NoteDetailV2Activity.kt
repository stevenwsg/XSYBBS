package com.wsg.xsybbs.module.notedetail.view

import android.os.Bundle
import androidx.lifecycle.ViewModelProviders
import com.wsg.xsybbs.R
import com.wsg.xsybbs.base.BaseActivity
import com.wsg.xsybbs.bean.Comment
import com.wsg.xsybbs.bean.Note
import com.wsg.xsybbs.module.notedetail.viewmodel.NoteDetailViewModel

/**
 * 帖子详情页面
 */

class NoteDetailV2Activity : BaseActivity() {

    private var note: Note? = null
    private var viewModel: NoteDetailViewModel? = null
    private var likeState: Boolean? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_note_detail)

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

    }

    private fun initViewModel() {
        viewModel = ViewModelProviders.of(this).get(NoteDetailViewModel::class.java)
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