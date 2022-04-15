package com.yahoo.base

import android.app.Application

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



    }

    override fun onCreate() {
        super.onCreate()
        context = this
    }
}