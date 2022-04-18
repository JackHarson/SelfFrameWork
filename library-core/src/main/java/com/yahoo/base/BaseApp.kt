package com.yahoo.base

import android.app.Application
import android.content.Context
import android.content.IntentFilter
import android.net.ConnectivityManager
import androidx.multidex.MultiDex
import com.blankj.utilcode.util.Utils
import com.scwang.smart.refresh.footer.ClassicsFooter
import com.scwang.smart.refresh.header.MaterialHeader
import com.scwang.smart.refresh.layout.SmartRefreshLayout
import com.scwang.smart.refresh.layout.api.RefreshLayout
import com.yahoo.core.R
import com.yahoo.network.manager.NetworkStateReceiver

/**

 * Created by ZhangJin on 2022/4/15 11:14 上午.
 * Describe:
 */
val appContext: Application by lazy { BaseApp.context }

class BaseApp : Application() {

    companion object {
        lateinit var context: Application
    }

    init {
        //设置全局的Header构建器
        SmartRefreshLayout.setDefaultRefreshHeaderCreator { context, _ ->
            MaterialHeader(context).setColorSchemeResources(R.color.purple_200)
        }

        //设置全局的Footer构建器
        SmartRefreshLayout.setDefaultRefreshFooterCreator { context: Context, layout: RefreshLayout ->
            layout.setEnableFooterFollowWhenNoMoreData(true)
            return@setDefaultRefreshFooterCreator ClassicsFooter(context).setDrawableSize(20f)
        }
    }

    override fun attachBaseContext(context: Context?) {
        super.attachBaseContext(context)
        MultiDex.install(this)
    }

    override fun onCreate() {
        super.onCreate()
        context = this
        Utils.init(this)

        //注册网络变化的广播接收者
        val mNetworkStateReceive = NetworkStateReceiver()
        context.registerReceiver(
            mNetworkStateReceive,
            IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION)
        )


    }
}