package com.yahoo.fxw.ui.home

import android.os.Bundle
import com.blankj.utilcode.util.ToastUtils
import com.yahoo.base.BaseViewModel
import com.yahoo.base.BaseVmFragment
import com.yahoo.fxw.R

/**

 * Created by ZhangJin on 2022/4/21 11:36 上午.
 * Describe: 项目首页
 */
class Home2Fragment : BaseVmFragment<BaseViewModel>() {
    override fun layoutId(): Int {
        return R.layout.fragment_home2
    }

    override fun initView(savedInstanceState: Bundle?) {

    }

    override fun createObserver() {

    }

    override fun onLazyInitView() {
        ToastUtils.showShort("初始化开始----Home2Fragment")
    }

    /**
     *   设置沉浸式状态栏 默认为false
     */
    override fun isTransparentStatusBar(): Boolean {
        return true
    }
}