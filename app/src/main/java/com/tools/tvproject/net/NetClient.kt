package com.tools.tvproject.net

import android.os.Build
import android.util.Log
import base.ui.BaseView

import com.apollographql.apollo.ApolloClient
import com.apollographql.apollo.api.Response
import com.apollographql.apollo.exception.ApolloException
import com.apollographql.apollo.exception.ApolloNetworkException
//import com.facebook.stetho.okhttp3.StethoInterceptor
import com.tools.tvproject.BaseApplication
import com.tools.tvproject.BuildConfig
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import java.util.*

val BASE_URL =
    if (BuildConfig.DEBUG) "https://api.fanzhe.cn/graphql" else "https://api.fanzhe.cn/graphql"  //测试环境 "https://dapi.fanzhe.cn/graphql"

object STORE {
    val DeviceId = "deviceId"

    val TOKEN = "token"
}
fun getPhoneSerial(): String {
    var serial: String? = Pref.getString(STORE.DeviceId)

    if (serial == null) {
        serial = UUID.randomUUID().toString()
        Pref.putString(STORE.DeviceId, serial)
    }

    return serial
}

var okHttpClient = OkHttpClient.Builder()
    .addInterceptor(Interceptor { chain: Interceptor.Chain ->
        val original: Request = chain.request()
        val builder: Request.Builder =
            original.newBuilder().method(original.method, original.body)

        Pref.getString(STORE.TOKEN)?.let {
            builder.header("Authorization", "Bearer         builder.header(\"Authorization\", \"Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJVU0VSOjMiLCJsb2dpblNpZ24iOiIxMTE5NzUiLCJuYmYiOjE2NDAwMTIxMjcsInVzZXJUeXBlIjoiVVNFUiIsImV4cCI6NDc2MjA3NjEyNywidXNlcklkIjoiMyIsImlhdCI6MTY0MDAxMjEyN30.sFCuOR4_cvu7W-oJCI4_bsOansg-RZzFQtyuz60FQEc\")\n")
        }
        builder.header("device", "Android")
        builder.header("X-DeviceId",  getPhoneSerial())
        builder.header("X-DeviceInfo", Build.MODEL + " " + Build.BOARD)
        builder.header("X-Platform", "Android")
        builder.header("X-ClientInfo",
            BaseApplication.context.packageName + "  " + BuildConfig.VERSION_NAME)
        builder.header("X-App-Platform", "APP")
        chain.proceed(builder.build())
    })
//    .addNetworkInterceptor(StethoInterceptor())
    .build()




val apolloClient: ApolloClient = ApolloClient.builder()
    .okHttpClient(okHttpClient)
    .serverUrl(BASE_URL)
    .build()

fun parseError(e: ApolloException, baseView: BaseView?) {

    GlobalScope.launch {
        withContext(Dispatchers.Main) {
            if (e is ApolloNetworkException) {
                baseView?.onError("0", "请检查网络")
            }
        }
    }


}


fun parseResponse(res: Response<*>, baseView: BaseView?) {
    res.errors?.get(0)?.run {
        var result = customAttributes["extensions"] as LinkedHashMap<*, *>
        var code = result["code"].toString()

        baseView?.onError(code, message)
    }
}

class NetClient {
    companion object {

//        fun getNetData(operation: Operation<Operation.Data, *, Operation.Variables>) {
//            GlobalScope.launch {
//                val response = try {
//                    if (operation is Mutation) {
//                        apolloClient.mutate(operation).await()
//                    }  else if(operation is Query) {
//                        apolloClient.query(operation).await()
//                    } else{
//
//                    }
//                } catch (e: ApolloException) {
//                    return@launch
//                }
//                val data = response
//                withContext(Dispatchers.Main) {
//                    if (data == null || response.hasErrors()) {
//                        return@withContext
//                    }
//                }
//            }
//        }


    }
}