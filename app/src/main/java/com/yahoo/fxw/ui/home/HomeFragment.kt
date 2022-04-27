package com.yahoo.fxw.ui.home

import android.os.Bundle
import androidx.lifecycle.Observer
import com.blankj.utilcode.util.ToastUtils
import com.kingja.loadsir.core.LoadService
import com.yahoo.base.BaseVmFragment
import com.yahoo.fxw.R
import com.yahoo.fxw.app.helper.ext.BannerData
import com.yahoo.fxw.app.helper.ext.show
import com.yahoo.network.manager.NetState
import com.yahoo.ui.ext.loadServiceInit
import com.yahoo.ui.ext.showLoading
import com.youth.banner.Banner
import com.youth.banner.adapter.BannerImageAdapter
import kotlinx.android.synthetic.main.fragment_home.*

/**

 * Created by ZhangJin on 2022/4/21 11:36 上午.
 * Describe: 项目首页
 */
class HomeFragment : BaseVmFragment<HomeViewModel>() {

    //界面状态管理者
    private lateinit var loadsir: LoadService<Any>

    override fun layoutId(): Int {
        return R.layout.fragment_home
    }

    override fun initView(savedInstanceState: Bundle?) {
        //状态页配置
        loadsir = loadServiceInit(textView) {
            //点击重试时触发的操作
            loadsir.showLoading()
            mViewModel.getNearList("合肥市")


        }
    }

    override fun createObserver() {
        mViewModel.storeList.observe(viewLifecycleOwner, Observer {

            loadsir.showSuccess()
            textView.text = it.toString()

        })
    }

    var imageUrls = mutableListOf(
        BannerData("https://img.zcool.cn/community/01b72057a7e0790000018c1bf4fce0.png", "1"),
        BannerData("https://img.zcool.cn/community/016a2256fb63006ac7257948f83349.jpg"),
        BannerData("https://img.zcool.cn/community/01233056fb62fe32f875a9447400e1.jpg"),
        BannerData("https://img.zcool.cn/community/01700557a7f42f0000018c1bd6eb23.jpg")
    )

    override fun onLazyInitView() {

        statusBarColorWhite()


        mViewModel.getNearList("合肥市")
        val banner = (bannerLayout as Banner<BannerData, BannerImageAdapter<BannerData>>)
        banner.show(this, requireContext(), imageUrls) { data, position ->
            ToastUtils.showShort("position---" + position)
        }


    }

    override fun initImmersionBar() {
        super.initImmersionBar()
        statusBarColorWhite()
    }

    override fun onNetworkStateChanged(netState: NetState) {
        super.onNetworkStateChanged(netState)
        if (netState.isSuccess) {
            ToastUtils.showShort("终于有网啦！！")
        } else {
            ToastUtils.showShort("终于没网啦！！")
        }
    }
}