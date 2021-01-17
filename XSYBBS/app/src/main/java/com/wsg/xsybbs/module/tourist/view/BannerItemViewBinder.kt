package com.wsg.xsybbs.module.tourist.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.wsg.xsybbs.R
import com.wsg.xsybbs.adapter.GlideImageLoader
import com.wsg.xsybbs.bean.BanneData
import com.youth.banner.Banner
import me.drakeet.multitype.ItemViewBinder

/**
 * Create by wangshengguo on 2021/1/15.
 * 首页Banner
 */
class BannerItemViewBinder : ItemViewBinder<BanneData, BannerItemViewBinder.BannerViewHolder>() {

    class BannerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var banner: Banner? = null

        init {
            banner = itemView.findViewById(R.id.banner)
        }
    }

    override fun onCreateViewHolder(inflater: LayoutInflater, parent: ViewGroup): BannerViewHolder {
        return BannerViewHolder(inflater.inflate(R.layout.item_banner, parent, false))
    }

    override fun onBindViewHolder(holder: BannerViewHolder, item: BanneData) {
        holder.banner?.setImageLoader(GlideImageLoader())
        val urls = item.list.map { it.photo }
        holder.banner?.setImages(urls)
        holder.banner?.start()
    }
}