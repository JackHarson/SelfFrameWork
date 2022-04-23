package com.yahoo.fxw.app.global

import com.kunminx.architecture.ui.callback.UnPeekLiveData
import com.yahoo.base.BaseViewModel
import com.yahoo.fxw.app.helper.LoginHelper

/**

 * Created by ZhangJin on 2022/4/23 1:22 下午.
 * Describe: APP全局的ViewModel，可以存放公共数据，当他数据改变时，
 * 所有监听他的地方都会收到回调,也可以做发送消息。
 * 比如 全局可使用的 地理位置信息，账户信息,App的基本配置等等，
 */
class AppViewModel : BaseViewModel() {

    //App的账户信息
    var userInfo = UnPeekLiveData.Builder<UserInfo>().setAllowNullValue(true).create()

    init {
        userInfo.value = LoginHelper.userInfo()
    }

}