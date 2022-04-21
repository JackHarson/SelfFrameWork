package com.yahoo.fxw.ui.home

import android.os.Bundle
import com.blankj.utilcode.util.ToastUtils
import com.yahoo.base.BaseViewModel
import com.yahoo.base.BaseVmFragment
import com.yahoo.fxw.R
import kotlinx.android.synthetic.main.fragment_main.*

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

    override fun onLazyInitView() {
        ToastUtils.showShort("初始化开始----Home1Fragment")
    }


}