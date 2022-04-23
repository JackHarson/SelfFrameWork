package com.yahoo.base

import android.content.Context
import android.content.res.Configuration
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.gyf.immersionbar.ImmersionBar

import com.yahoo.core.R
import com.yahoo.ext.getVmClazz
import com.yahoo.network.manager.NetState
import com.yahoo.network.manager.NetworkStateManager
import com.yahoo.statusbar.SimpleImmersionOwner
import com.yahoo.statusbar.SimpleImmersionProxy
import com.yahoo.ui.ext.dismissLoadingExt
import com.yahoo.ui.ext.showLoadingExt

/**

 * Created by ZhangJin on 2022/4/18 3:05 下午.
 * Describe:ViewModelFragment基类，自动把ViewModel注入Fragment
 */
abstract class BaseVmFragment<VM : BaseViewModel> : Fragment(), SimpleImmersionOwner {

    /**
     * ImmersionBar代理类
     */
    private val mSimpleImmersionProxy = SimpleImmersionProxy(this)

    private val handler = Handler()

    //是否第一次加载
    private var isFirst: Boolean = true

    lateinit var mViewModel: VM

    lateinit var mActivity: AppCompatActivity


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(layoutId(), container, false)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mActivity = context as AppCompatActivity
    }

    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        mSimpleImmersionProxy.isUserVisibleHint = isVisibleToUser

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        mSimpleImmersionProxy.onActivityCreated(savedInstanceState)
    }

    override fun onHiddenChanged(hidden: Boolean) {
        super.onHiddenChanged(hidden)
        mSimpleImmersionProxy.onHiddenChanged(hidden)

    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        mSimpleImmersionProxy.onConfigurationChanged(newConfig)
    }


    /**
     * 初始化沉浸式
     * Init immersion bar.
     */
    open fun statusBarColorWhite() {
        ImmersionBar.with(this)
            .statusBarColor(R.color.white)
            .statusBarDarkFont(true).init()
    }

    open fun transparentStatusBar() {
        ImmersionBar.with(this).transparentStatusBar().init()

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        isFirst = true
        mViewModel = createViewModel()
        initView(savedInstanceState)

        createObserver()
        registerDefUIChange()
        initData()
    }

    override fun onResume() {
        super.onResume()

        initImmersionBar()

        onVisible()
    }

    /**
     * 是否需要懒加载
     */
    private fun onVisible() {
        if (lifecycle.currentState == Lifecycle.State.STARTED && isFirst) {
            // 延迟加载 防止 切换动画还没执行完毕时数据就已经加载好了，这时页面会有渲染卡顿
            handler.postDelayed({
                onLazyInitView()
                //在Fragment中，只有懒加载过了才能开启网络变化监听
                NetworkStateManager.instance.mNetworkStateCallback.observeInFragment(
                    this,
                    Observer {
                        //不是首次订阅时调用方法，防止数据第一次监听错误
                        if (!isFirst) {
                            onNetworkStateChanged(it)
                        }
                    })
                isFirst = false
            }, lazyLoadTime())

        }


    }

    /**
     * 延迟加载 防止 切换动画还没执行完毕时数据就已经加载好了，这时页面会有渲染卡顿  bug
     * 这里传入你想要延迟的时间，延迟时间可以设置比转场动画时间长一点 单位： 毫秒
     * 不传默认 300毫秒
     * @return Long
     */
    open fun lazyLoadTime(): Long {
        return 300
    }

    override fun onDestroy() {
        super.onDestroy()
        mSimpleImmersionProxy.onDestroy()
        handler.removeCallbacksAndMessages(null)
    }

    /**
     * 网络变化监听 子类重写
     */
    open fun onNetworkStateChanged(netState: NetState) {}

    /**
     * Fragment执行onCreate后触发的方法
     */
    open fun initData() {}

    /**
     * 创建viewModel
     */
    private fun createViewModel(): VM {
        return ViewModelProvider(this).get(getVmClazz(this))
    }

    /**
     * 注册 UI 事件
     */
    private fun registerDefUIChange() {
        mViewModel.loadingChange.showDialog.observeInFragment(this, Observer {
            showLoadingExt()
        })
        mViewModel.loadingChange.dismissDialog.observeInFragment(this, Observer {
            dismissLoadingExt()
        })
    }


    /**
     * 当前Fragment绑定的视图布局
     */
    abstract fun layoutId(): Int

    /**
     * 初始化view
     */
    abstract fun initView(savedInstanceState: Bundle?)


    /**
     * 创建观察者
     */
    abstract fun createObserver()

    /**
     * 懒加载
     */
    abstract fun onLazyInitView()


    /**
     * 是否可以实现沉浸式，当为true的时候才可以执行initImmersionBar方法
     * Immersion bar enabled boolean.
     *
     * @return the boolean
     */
    override fun immersionBarEnabled(): Boolean {
        return true
    }


    override fun initImmersionBar() {
        ImmersionBar.with(this).keyboardEnable(true).init()
    }
}