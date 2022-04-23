package com.yahoo.fxw.app.helper

import com.blankj.utilcode.util.SPUtils
import com.yahoo.fxw.app.global.UserInfo

/**

 * Created by ZhangJin on 2022/4/23 10:22 AM.

 * Describe:
 */
class LoginHelper {
    companion object {

        private const val LOGIN_INFO = "LOGIN_INFO"
        fun isLogin(): Boolean {
            return false
        }

        fun setLoginInfo(info: String) {
            SPUtils.getInstance().put(LOGIN_INFO, info)
        }

        fun userInfo(): UserInfo {
            return UserInfo()
        }
    }
}