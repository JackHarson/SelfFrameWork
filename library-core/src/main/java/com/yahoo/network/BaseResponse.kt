package com.yahoo.network

/**

 * Created by ZhangJin on 2022/4/19 1:57 下午.
 * Describe: 服务器返回数据的基类
 *
 * 如果需要框架帮你做脱壳处理请继承它！！请注意：
 * 2.必须实现抽象方法，根据自己的业务判断返回请求结果是否成功
 */
abstract class BaseResponse<T> {

    abstract fun isSuccessful(): Boolean

    abstract fun getResponseData(): T

    abstract fun getResponseCode(): String

    abstract fun getResponseMsg(): String

}