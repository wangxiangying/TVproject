package base.utils

import android.util.Log

/**
 * Created by iamwxy on 2020/6/20 14:35
 * description:
 */
object LogUtils {
    fun printLog(tag: String, msg: String) {
        Log.d(tag, msg)
    }
}
