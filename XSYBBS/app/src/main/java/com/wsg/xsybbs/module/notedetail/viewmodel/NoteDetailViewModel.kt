package com.wsg.xsybbs.module.notedetail.viewmodel


import androidx.lifecycle.MutableLiveData
import com.wsg.xsybbs.base.BaseViewModel
import com.wsg.xsybbs.bean.Comment
import com.wsg.xsybbs.module.notedetail.bean.AddCommentResult

class NoteDetailViewModel : BaseViewModel() {

    // 评论数据
    var commentLists = MutableLiveData<List<Comment>>()

    // 点赞状态
    var likeState = MutableLiveData<Boolean>()

    // 发表评论
    var addCommentResult = MutableLiveData<AddCommentResult>()

    fun getCommentInfo() {

    }

    fun getLikeState() {

    }

    fun updateLikeState(like: Boolean) {

    }

    fun addComment(comment: Comment) {

    }
}