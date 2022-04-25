package com.yahoo.fxw.app.net

import com.yahoo.fxw.ui.home.NearByEntity
import com.yahoo.network.ApiResponse

import retrofit2.http.POST
import retrofit2.http.Query

/**

 * Created by ZhangJin on 2022/4/20 10:13 上午.
 * Describe: 网络API
 */
interface ApiService {

    companion object {
        const val BASE_URL = "https://app.faxingwu.com/"
    }


    @POST("/v1/mt/nearbyStore")
    suspend fun nearByList(
        @Query("city") city: String
    ): ApiResponse<MutableList<NearByEntity>>


}