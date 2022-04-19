package com.yahoo.ui.loadsir

import android.content.Context
import android.view.View
import com.kingja.loadsir.callback.Callback
import com.yahoo.ui.R

/**

 * Created by ZhangJin on 2022/4/19 9:30 上午.
 * Describe:
 */
class LoadingCallback : Callback() {
    override fun onCreateView(): Int {

        return R.layout.layout_loading
    }


    /**
     *  当前Callback的点击事件，如果返回true则覆盖注册时的onReload()，
     *  如果返回false则两者都执行，先执行onReloadEvent()。
     */
    override fun onReloadEvent(context: Context?, view: View?): Boolean {
        return true
    }
}