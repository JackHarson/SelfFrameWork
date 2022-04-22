package com.yahoo.web

import android.os.Bundle
import android.widget.LinearLayout
import androidx.activity.OnBackPressedCallback
import com.gyf.immersionbar.ImmersionBar

import com.just.agentweb.AgentWeb
import com.yahoo.base.BaseViewModel
import com.yahoo.base.BaseVmFragment
import com.yahoo.constant.JConstants
import com.yahoo.fxw.R
import com.yahoo.fxw.ext.nav

import kotlinx.android.synthetic.main.fragment_webview.*
import kotlinx.android.synthetic.main.top_bar.*

/**

 * Created by ZhangJin on 2021/1/21 1:35 PM.
 * Describe: webView 界面
 */
class WebViewFragment : BaseVmFragment<BaseViewModel>() {

    private var mAgentWeb: AgentWeb? = null

    override fun layoutId(): Int {
        return R.layout.fragment_webview
    }

    override fun initView(savedInstanceState: Bundle?) {

        initAgentWeb()

        ivLeft.setOnClickListener {
            mAgentWeb?.let { web ->
                if (web.webCreator.webView.canGoBack()) {
                    web.webCreator.webView.goBack()
                } else {
                    nav().navigateUp()
                }
            }
        }
    }

    private fun initAgentWeb() {

        tvTitle.text = arguments?.getString(JConstants.ARG_WEB_TITLE)
        val url = arguments?.getString(JConstants.ARG_WEB_URL)

        mAgentWeb = AgentWeb.with(this)
            .setAgentWebParent(layout_parent, LinearLayout.LayoutParams(-1, -1))
            .useDefaultIndicator()
            .setMainFrameErrorView(R.layout.agentweb_error_page, -1)
            .createAgentWeb()
            .ready()
            .go(url)

    }


    override fun onPause() {
        mAgentWeb?.webLifeCycle?.onPause()
        super.onPause()
    }

    override fun onResume() {
        mAgentWeb?.webLifeCycle?.onResume()
        super.onResume()
    }


    override fun onDestroy() {
        mAgentWeb?.webLifeCycle?.onDestroy()
        mActivity.setSupportActionBar(null)
        super.onDestroy()
    }

    override fun createObserver() {

    }

    override fun onLazyInitView() {
        /**
         * 物理返回键的监听
         */
        mActivity.onBackPressedDispatcher.addCallback(this,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    mAgentWeb?.let { web ->
                        if (web.webCreator.webView.canGoBack()) {
                            web.webCreator.webView.goBack()
                        } else {
                            nav().navigateUp()
                        }
                    }
                }
            })
    }

    override fun initImmersionBar() {
        super.initImmersionBar()
        ImmersionBar.with(this)
            .statusBarColor(R.color.white)
//            .titleBar(R.id.top_bar)
            .fitsSystemWindows(true)
            .statusBarDarkFont(true).init()
    }
}