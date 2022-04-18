package com.yahoo.network.manager

import com.yahoo.callback.livedata.event.EventLiveData

/**

 * Created by ZhangJin on 2022/4/18 11:50 上午.
 * Describe: 网络变化管理者
 */
class NetworkStateManager private constructor() {

    val mNetworkStateCallback = EventLiveData<NetState>()

    companion object {
        val instance: NetworkStateManager by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
            NetworkStateManager()
        }
    }
}