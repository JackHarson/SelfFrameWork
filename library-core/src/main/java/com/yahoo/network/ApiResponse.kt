package com.yahoo.network

/**

 * Created by ZhangJin on 2022/4/20 10:25 上午.
 * Describe:
 */
data class ApiResponse<T>(val code: String, val msg: String, val data: T) : BaseResponse<T>() {

    override fun isSuccessful() = code == "101"

    override fun getResponseCode() = code

    override fun getResponseData() = data

    override fun getResponseMsg() = msg
}