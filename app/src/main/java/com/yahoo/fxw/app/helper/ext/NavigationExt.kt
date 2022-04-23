package com.yahoo.fxw.app.helper.ext

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import com.yahoo.fxw.app.constant.JConstants
import com.yahoo.fxw.R


/**

 * Created by ZhangJin on 2021/1/21 1:58 PM.
PackageName: com.jm.fxwfast.helper
 * Describe:Navigation 帮助类
 */

fun Fragment.nav(): NavController {
    return NavHostFragment.findNavController(this)
}

fun nav(view: View): NavController {
    return Navigation.findNavController(view)
}

var lastNavTime = 0L

/**
 * 防止短时间内多次快速跳转Fragment出现的bug
 * @param resId 跳转的action Id
 * @param bundle 传递的参数
 * @param interval 多少毫秒内不可重复点击 默认0.5秒
 */
fun NavController.navigateAction(resId: Int, bundle: Bundle? = null, interval: Long = 500) {
    val currentTime = System.currentTimeMillis()
    if (currentTime >= lastNavTime + interval) {
        lastNavTime = currentTime
        navigate(resId, bundle)
    }
}

/**
 * @param title webView 的标题
 * @param url  webView 的链接
 */
fun NavController.jumpWebFragment(title: String, url: String) {
    navigateAction(R.id.action_to_webFragment, Bundle().apply {
        putString(JConstants.ARG_WEB_TITLE, title)
        putString(JConstants.ARG_WEB_URL, url)
    })

}

/**
 * 拦截登录操作，如果没有登录跳转登录，登录过了就执行你的方法
 */
//fun NavController.jumpByLogin(action: (NavController) -> Unit) {
//    if (LoginHelper.isLogin()) {
//        action(this)
//    } else {
//        this.navigateAction(R.id.action_to_loginFragment)
//    }
//
//}