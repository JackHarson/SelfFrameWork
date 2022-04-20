package com.yahoo.network


/**

 * Created by ZhangJin on 2022/4/19 2:31 下午.
 * Describe: 自定义错误信息异常
 */
class AppException : Exception {

    var errorMsg: String //错误消息
    var errCode: String = "0" //错误码
    var errorLog: String? //错误日志
    var throwable: Throwable? = null

    constructor(
        errCode: String,
        error: String?,
        errorLog: String? = "",
        throwable: Throwable? = null
    ) : super(error) {
        this.errorMsg = error ?: "请求失败，请稍后再试"
        this.errCode = errCode
        this.errorLog = errorLog ?: this.errorMsg
        this.throwable = throwable
    }

    constructor(error: Error, e: Throwable?) {
        errCode = error.getKey()
        errorMsg = error.getValue()
        errorLog = e?.message
        throwable = e
    }
}