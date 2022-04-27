package com.yahoo.fxw.ui.home

import androidx.lifecycle.MutableLiveData
import com.blankj.utilcode.util.ToastUtils
import com.yahoo.base.BaseViewModel
import com.yahoo.ext.requestNoCheck
import com.yahoo.fxw.app.net.apiService

/**

 * Created by ZhangJin on 2022/4/25 2:29 下午.
 * Describe:
 */
class HomeViewModel : BaseViewModel() {

    val storeList = MutableLiveData<MutableList<NearByEntity>>()

    fun getNearList(city: String) {
        requestNoCheck({ apiService.nearByList(city) }, {
            if (it.isSuccessful())
                storeList.value = it.getResponseData()
        }, {
            ToastUtils.showShort(it.errorMsg)
        }, isShowDialog = true)

    }

}