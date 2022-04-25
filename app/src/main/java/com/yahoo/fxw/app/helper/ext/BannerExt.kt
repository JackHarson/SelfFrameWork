package com.yahoo.fxw.app.helper.ext

import android.content.Context
import androidx.lifecycle.LifecycleOwner
import com.bumptech.glide.Glide
import com.youth.banner.Banner
import com.youth.banner.adapter.BannerImageAdapter
import com.youth.banner.holder.BannerImageHolder
import com.youth.banner.indicator.CircleIndicator

/**

 * Created by ZhangJin on 2022/4/25 10:49 上午.
 * Describe: Banner扩展类
 */

fun Banner<BannerData, BannerImageAdapter<BannerData>>.show(
    owner: LifecycleOwner,
    context: Context,
    bannerData: MutableList<BannerData>,
    event: (BannerData, Int) -> Unit
) {

    this.apply {
        addBannerLifecycleObserver(owner)
        setBannerRound(20f)
        indicator = CircleIndicator(context)
        setAdapter(object : BannerImageAdapter<BannerData>(bannerData) {
            override fun onBindView(
                holder: BannerImageHolder,
                data: BannerData,
                position: Int,
                size: Int
            ) {
                Glide.with(context)
                    .load(data.imageUrl)
                    .into(holder.imageView)
            }
        })
            .setOnBannerListener { data, position ->
                event.invoke(data as BannerData, position)
            }
    }


}

data class BannerData(
    var imageUrl: String = "",
    var type: String = ""
)
