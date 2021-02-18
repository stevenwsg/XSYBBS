package com.wsg.xsybbs.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import com.wsg.xsybbs.R
import com.wsg.xsybbs.base.BaseActivity

/**
 * Create by wangshengguo on 2021/2/5.
 */
class DefaultTopBar : RelativeLayout {

    private var mContext: Context
    private var mRootView: View? = null
    private var mImageBack: ImageView? = null
    private var mTvTitle: TextView? = null

    constructor(ctx: Context) : this(ctx, null)

    constructor(ctx: Context, attrs: AttributeSet?) : this(ctx, attrs, 0)

    constructor(ctx: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        ctx,
        attrs,
        defStyleAttr
    ) {
        mContext = ctx
        initView()
    }

    private fun initView() {
        val layoutInflater = LayoutInflater.from(mContext)
        mRootView = layoutInflater.inflate(R.layout.layout_default_topbar, this, true)

        mTvTitle = mRootView?.findViewById(R.id.tvTitle)
        mImageBack = mRootView?.findViewById(R.id.imageBack)

        mImageBack?.setOnClickListener {
            (mContext as? BaseActivity)?.finish()
        }
    }

    fun setTitle(title: String) {
        mTvTitle?.text = title
    }

}