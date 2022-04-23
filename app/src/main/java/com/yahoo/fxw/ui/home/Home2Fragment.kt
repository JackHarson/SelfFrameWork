package com.yahoo.fxw.ui.home

import android.os.Bundle
import com.blankj.utilcode.util.ToastUtils
import com.yahoo.base.BaseViewModel
import com.yahoo.base.BaseVmFragment
import com.yahoo.fxw.R
import com.yahoo.fxw.app.helper.ext.jumpWebFragment
import com.yahoo.fxw.app.helper.ext.nav
import kotlinx.android.synthetic.main.fragment_home2.*

/**

 * Created by ZhangJin on 2022/4/21 11:36 上午.
 * Describe: 项目首页
 */
class Home2Fragment : BaseVmFragment<BaseViewModel>() {
    override fun layoutId(): Int {
        return R.layout.fragment_home2
    }

    override fun initView(savedInstanceState: Bundle?) {
        tv_go_jd.setOnClickListener {
            nav().jumpWebFragment("京东", "https://www.baidu.com/")
        }

    }

    override fun createObserver() {

    }

    override fun onLazyInitView() {
        ToastUtils.showShort("懒加载开始----Home2Fragment")
    }


    override fun initImmersionBar() {
        super.initImmersionBar()
        transparentStatusBar()

    }
}