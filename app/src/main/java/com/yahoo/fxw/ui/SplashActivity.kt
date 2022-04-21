package com.yahoo.fxw.ui

import android.content.Intent
import android.os.Bundle
import com.yahoo.base.BaseViewModel
import com.yahoo.base.BaseVmActivity
import com.yahoo.fxw.R
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashActivity : BaseVmActivity<BaseViewModel>() {
    override fun layoutId(): Int {
        return R.layout.activity_splash
    }

    override fun initView(savedInstanceState: Bundle?) {
        GlobalScope.launch {
            delay(2000)
            startActivity(Intent(this@SplashActivity, MainActivity::class.java))
            finish()
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
        }
    }

    override fun createObserver() {

    }

    override fun isTransparentStatusBar() = true

}