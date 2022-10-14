package base.utils

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Build
import android.os.Environment
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi

import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.math.BigDecimal
import java.text.DecimalFormat


/**
 * Created by iamwxy on 2020/6/24 16:49
 * description:
 */
fun isPhone(str: String): Boolean {
    return !str.isEmpty() && str.matches(Regex("[1][0-9]{10}"))
}

fun isEmail(str: String?): Boolean {
    val regex = "^[a-zA-Z0-9_.-]+@[a-zA-Z0-9-]+(\\.[a-zA-Z0-9-]+)*\\.[a-zA-Z0-9]{2,6}\$"
    return str?.matches(Regex(regex)) ?: false
}

fun setViewVisibility(view: View?, visibility: Int) {
    view?.let {
        if (view.visibility != visibility) {
            view.visibility = visibility
        }
    }
}

/**
 * dp 转化为 px
 */
fun dp2px(context: Context, dpValue: Float): Int {
    val scale = context.resources.displayMetrics.density
    return (dpValue * scale + 0.5f).toInt()
}

/**
 * 保存图片到指定路径
 *
 * @param context
 * @param bitmap   要保存的图片
 * @param fileName 自定义图片名称  getString(R.string.app_name) + "" + System.currentTimeMillis()+".png"
 * @param filePath 文件夹名字 (文件夹会创建于Environment.getExternalStorageDirectory().absolutePath 目录下)
 * @return true 成功 false失败
 */
fun saveImageToGallery(
    context: Context,
    bitmap: Bitmap,
    fileName: String,
    folderName: String,
): Boolean {
    // 保存图片至指定路径
    val storePath =
        Environment.getExternalStorageDirectory().absolutePath + File.separator + folderName
    val appDir = File(storePath)
    if (!appDir.exists()) {
        appDir.mkdir()
    }
    val file = File(appDir, fileName)
    try {
        val fos = FileOutputStream(file)
        //通过io流的方式来压缩保存图片(80代表压缩20%)
        val isSuccess = bitmap.compress(Bitmap.CompressFormat.JPEG, 80, fos)
        fos.flush()
        fos.close()

        //发送广播通知系统图库刷新数据
        val uri = Uri.fromFile(file)
        context.sendBroadcast(Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, uri))
        return isSuccess
    } catch (e: IOException) {
        e.printStackTrace()
    }

    return false
}

@RequiresApi(3)
fun hideKeyboard(view: View) {
    val manager = view.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    manager.hideSoftInputFromWindow(view.windowToken, 0)
}

@RequiresApi(3)
fun showKeyboard(editText: EditText) {
    editText.requestFocus()
    val manager = editText.context.getSystemService(
        Context.INPUT_METHOD_SERVICE
    ) as InputMethodManager
    manager.showSoftInput(editText, InputMethodManager.SHOW_IMPLICIT)
}

fun showToast(context: Context, msg: String) {
//    makeTextCenter(context, msg)
}

/**
 * 返回app版本名字
 * 对应build.gradle中的versionName
 *
 * @param context
 * @return
 */
fun getAppVersionName(context: Context): String {
    var versionName: String? = ""
    try {
        val packageManager = context.packageManager
        val packInfo = packageManager.getPackageInfo(context.packageName, 0)
        versionName = packInfo.versionName
    } catch (e: Exception) {
        e.printStackTrace()
    }

    return versionName ?: ""
}

/**
 * 返回app版本号
 * 对应build.gradle中的versionCode
 *
 * @param context
 * @return
 */
fun getAppVersionCode(context: Context): String {
    var versionCode: String? = ""
    try {
        val packageManager = context.packageManager
        val packInfo = packageManager.getPackageInfo(context.packageName, 0)
        versionCode = packInfo.versionCode.toString()
    } catch (e: Exception) {
        e.printStackTrace()
    }

    return versionCode ?: ""
}


/**
 * 获取一个 View 的缓存视图
 * (前提是这个View已经渲染完成显示在页面上)
 * @param view
 * @return
 */
@RequiresApi(Build.VERSION_CODES.DONUT)
fun getCacheBitmapFromView(view: View): Bitmap? {
    val drawingCacheEnabled = true
    view.isDrawingCacheEnabled = drawingCacheEnabled
    view.buildDrawingCache(drawingCacheEnabled)
    val drawingCache = view.drawingCache
    val bitmap: Bitmap?
    if (drawingCache != null) {
        bitmap = Bitmap.createBitmap(drawingCache)
        view.isDrawingCacheEnabled = false
    } else {
        bitmap = null
    }
    return bitmap
}

fun getDecStr(price: Any?): String {
    val number = BigDecimal(price.toString())
    return DecimalFormat("#.##").format(number)
}
