package com.yahoo.ext

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.permissionx.guolindev.PermissionX


/**

 * Created by ZhangJin on 2022/4/25 9:44 上午.
 * Describe:  动态权限扩展类
 */


/**
 * 获取动态权限
 *
 * @param permissions  需要获取的权限组
 * @param granted 权限通过之后的事件
 * @param refuse 权限拒绝之后的事件
 * @param explain 是否需要关于该权限的解释弹框 默认false
 */
fun Fragment.askPermission(
    vararg permissions: String,
    granted: () -> Unit = {},
    refuse: (List<String>) -> Unit = {},
    explain: Boolean = false,
    remindMessage: String = "需要您同意以下权限才能正常使用",
) {

    val permissionXBuilder = PermissionX.init(this).permissions(*permissions)
    if (explain) {
        permissionXBuilder.explainReasonBeforeRequest()
        permissionXBuilder.onExplainRequestReason { scope, deniedList ->
            val message = remindMessage
            scope.showRequestReasonDialog(deniedList, message, "确定", "取消")
        }
    }
    // never ask again
    permissionXBuilder.onForwardToSettings { scope, deniedList ->
        scope.showForwardToSettingsDialog(deniedList, "请在设置中允许以下权限", "允许")
    }
        .request { allGranted, grantedList, deniedList ->
            if (allGranted) granted() else refuse(deniedList)

        }
}

//判断该权限是否通过
fun AppCompatActivity.isPermissionGranted(permission: String): Boolean {
    return PermissionX.isGranted(this, permission)
}


fun Fragment.isPermissionGranted(permission: String): Boolean {
    return PermissionX.isGranted(requireContext(), permission)
}


