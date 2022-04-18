package com.yahoo.ext

import java.lang.reflect.ParameterizedType

/**

 * Created by ZhangJin on 2022/4/18 11:14 上午.
 * Describe:
 */

/**
 * 获取当前类绑定的泛型ViewModel-clazz
 */
@Suppress("UNCHECKED_CAST")
fun <VM> getVmClazz(obj: Any): VM {
    return (obj.javaClass.genericSuperclass as ParameterizedType).actualTypeArguments[0] as VM
}