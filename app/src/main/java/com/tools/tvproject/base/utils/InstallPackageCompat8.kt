package base.utils

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.provider.Settings
import androidx.annotation.RequiresApi

class InstallPackageCompat8 {

    private var callback: CallBack? = null

    @RequiresApi(api = Build.VERSION_CODES.O)
    private fun isHasInstallPermissionWithO(context: Context?): Boolean {
        return context?.packageManager?.canRequestPackageInstalls() ?: false
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private fun startInstallPermissionSettingActivity(context: Context?) {
        if (context == null) {
            return
        }
        showToast(context, "版本升级需要允许安装未知来源权限")
        val packageURI = Uri.parse("package:" + context.packageName)
        val intent = Intent(Settings.ACTION_MANAGE_UNKNOWN_APP_SOURCES, packageURI)
        (context as Activity).startActivityForResult(intent, REQUEST_CODE_APP_INSTALL)
    }

    fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent, context: Context?) {
        if (requestCode == REQUEST_CODE_APP_INSTALL) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                val hasInstallPermission = isHasInstallPermissionWithO(context)
                if (hasInstallPermission) {
                    if (callback != null) {
                        callback!!.success()
                    }
                } else {
                    if (callback != null) {
                        callback!!.fail()
                    }
                }
            }
        }
    }


    fun requestPermission(context: Context, callBack: CallBack) {

        this.callback = callBack

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val hasInstallPermission = isHasInstallPermissionWithO(context)
            if (!hasInstallPermission) {
                startInstallPermissionSettingActivity(context)
                return
            }
        }
        if (callback != null) {
            callback!!.success()
        }

    }

    interface CallBack {
        fun success()

        fun fail()
    }

    companion object {

        private val REQUEST_CODE_APP_INSTALL = 200
    }

}
