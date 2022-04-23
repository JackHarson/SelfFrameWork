package com.yahoo.fxw.app.global

/**

 * 描述　: 账户信息
 */

data class UserInfo(
    var admin: Boolean = false,

    var collectIds: MutableList<String> = mutableListOf(),
    var email: String = "",
    var icon: String = "",
    var id: String = "",
    var nickname: String = "",
    var password: String = "",
    var token: String = "",
    var type: Int = 0,
    var username: String = ""
)
