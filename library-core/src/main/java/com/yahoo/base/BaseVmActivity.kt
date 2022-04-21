package com.yahoo.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.gyf.immersionbar.ImmersionBar
import com.yahoo.core.R
import com.yahoo.ext.getVmClazz
import com.yahoo.network.manager.NetState
import com.yahoo.network.manager.NetworkStateManager
import com.yahoo.ui.ext.dismissLoadingExt
import com.yahoo.ui.ext.showLoadingExt

/**

 * Created by ZhangJin on 2022/4/18 10:31 上午.
 * Describe: ViewModelActivity基类，把ViewModel注入进来了
 */
abstract class BaseVmActivity<VM : BaseViewModel> : AppCompatActivity() {

    /**
     * 是否需要使用DataBinding 供子类BaseVmDbActivity修改，用户请慎动
     */
    private var isUserDb = false

    lateinit var mViewModel: VM

    abstract fun layoutId(): Int

    abstract fun initView(savedInstanceState: Bundle?)

    /**
     * 创建LiveData数据观察者
     */
    abstract fun createObserver()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (!isUserDb) {
            setContentView(layoutId())
        } else {
            initDataBind()
        }

        //是否是沉浸式
        if (isTransparentStatusBar()) {
            transparentStatusBar()
        } else {
            immersionBar()
        }
        init(savedInstanceState)

    }

    open fun isTransparentStatusBar(): Boolean {
        return false
    }


    /**
     * 初始化沉浸式
     * Init immersion bar.
     */
    open fun immersionBar() {
        ImmersionBar.with(this)
            .statusBarColor(R.color.white)
            .statusBarDarkFont(true).init()
    }

    open fun transparentStatusBar() {
        ImmersionBar.with(this).transparentStatusBar().init()

    }

    private fun init(savedInstanceState: Bundle?) {
        mViewModel = createViewModel()
        registerUIChange()
        initView(savedInstanceState)
        createObserver()
        NetworkStateManager.instance.mNetworkStateCallback.observeInActivity(this, Observer {
            onNetworkStateChanged(it)
        })


    }

    /**
     * 创建viewModel
     */
    private fun createViewModel(): VM {
        return ViewModelProvider(this).get(getVmClazz(this))
    }


    /**
     * 注册UI 事件
     */
    private fun registerUIChange() {
        //显示弹窗
        mViewModel.loadingChange.showDialog.observeInActivity(this, Observer {
            showLoadingExt(it)
        })
        //关闭弹窗
        mViewModel.loadingChange.dismissDialog.observeInActivity(this, Observer {
            dismissLoadingExt()
        })
    }


    /**
     * 供子类BaseVmDbActivity 初始化DataBinding操作
     */
    open fun initDataBind() {}



    /**
     * 网络变化监听 子类重写
     */
    open fun onNetworkStateChanged(netState: NetState) {}

}