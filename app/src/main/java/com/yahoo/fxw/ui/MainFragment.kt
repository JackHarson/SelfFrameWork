package com.yahoo.fxw.ui

import android.os.Bundle
import com.yahoo.base.BaseViewModel
import com.yahoo.base.BaseVmFragment
import com.yahoo.fxw.R
import com.yahoo.fxw.ext.init
import com.yahoo.fxw.ext.initMain
import kotlinx.android.synthetic.main.fragment_main.*

/**

 * Created by ZhangJin on 2022/4/21 11:36 上午.
 * Describe: 项目主页Fragment
 */
class MainFragment : BaseVmFragment<BaseViewModel>() {
    override fun layoutId(): Int {
        return R.layout.fragment_main
    }

    override fun initView(savedInstanceState: Bundle?) {
        //初始化viewpager2
        mainViewpager.initMain(this)

        mainBottom.init(requireContext()) {
            when (it) {
                R.id.menu_main -> currentItemChanged(0)
                R.id.menu_project -> currentItemChanged(1)
                R.id.menu_system -> currentItemChanged(2)
                R.id.menu_public -> currentItemChanged(3)
                R.id.menu_me -> currentItemChanged(4)
            }
        }
    }

    private fun currentItemChanged(position: Int) {
        mainViewpager.setCurrentItem(position, false)

    }

    override fun createObserver() {

    }

    override fun onLazyInitView() {

    }


}