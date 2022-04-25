package com.yahoo.fxw.ui.home

import android.os.Bundle
import com.blankj.utilcode.util.ToastUtils
import com.yahoo.base.BaseViewModel
import com.yahoo.base.BaseVmFragment
import com.yahoo.fxw.R
import com.yahoo.fxw.app.helper.ext.BannerData
import com.yahoo.fxw.app.helper.ext.show
import com.youth.banner.Banner
import com.youth.banner.adapter.BannerImageAdapter
import kotlinx.android.synthetic.main.fragment_home.*

/**

 * Created by ZhangJin on 2022/4/21 11:36 上午.
 * Describe: 项目首页
 */
class Home1Fragment : BaseVmFragment<BaseViewModel>() {

    override fun layoutId(): Int {
        return R.layout.fragment_home1
    }

    override fun initView(savedInstanceState: Bundle?) {

    }

    override fun createObserver() {

    }

    var imageUrls = mutableListOf(
        BannerData("https://img.zcool.cn/community/01b72057a7e0790000018c1bf4fce0.png", "1"),
        BannerData("https://img.zcool.cn/community/016a2256fb63006ac7257948f83349.jpg"),
        BannerData("https://img.zcool.cn/community/01233056fb62fe32f875a9447400e1.jpg"),
        BannerData("https://img.zcool.cn/community/01700557a7f42f0000018c1bd6eb23.jpg")
    )

    override fun onLazyInitView() {
        ToastUtils.showShort("懒加载开始----Home1Fragment")

        val banner = (bannerLayout as Banner<BannerData, BannerImageAdapter<BannerData>>)
        banner.show(this, requireContext(), imageUrls) { data, position ->
            ToastUtils.showShort("position---" + position)
        }
    }

    override fun initImmersionBar() {
        super.initImmersionBar()
        statusBarColorWhite()
    }


}