package com.yahoo.fxw.app.net

import com.yahoo.network.ApiResponse
import retrofit2.http.GET

/**

 * Created by ZhangJin on 2022/4/20 10:13 上午.
 * Describe: 网络API
 */
interface ApiService {

    companion object {
        const val BASE_URL = "https://wanandroid.com/"
    }

    /**
     * 获取banner数据
     */
    @GET("banner/json")
    suspend fun getBanner(): ApiResponse<Any>


}