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
class Home4Fragment : BaseVmFragment<BaseViewModel>() {
    override fun layoutId(): Int {
        return R.layout.fragment_home4
    }

    override fun initView(savedInstanceState: Bundle?) {

    }

    override fun createObserver() {

    }

    override fun onLazyInitView() {
        ToastUtils.showShort("初始化开始----Home4Fragment")
    }

}