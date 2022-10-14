package cn.fanzhe.net


import base.ui.BaseView
import com.google.gson.Gson
import com.tools.tvproject.net.Summary
import com.tools.tvproject.net.okHttpClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.HttpUrl
import okhttp3.HttpUrl.Companion.toHttpUrlOrNull
import okhttp3.Request
import org.json.JSONObject


fun getPodcastDailySummary(
    baseView: BaseView?,
    rechargeTime: Long?,
    consumptionTime: Long?,
    callback: (Summary) -> Unit,
) {


    GlobalScope.launch {

        val str = ("https://api.fanzhe.cn/api/v1/broadcast/get/daily_summary")
        val httpBuilder: HttpUrl.Builder = str.toHttpUrlOrNull()!!.newBuilder()
        val params = mutableMapOf<String, String>();


        if (rechargeTime != null) {
            params["rechargeTime"] = rechargeTime.toString()

        }

        if (consumptionTime != null) {
            params["consumptionTime"] = consumptionTime.toString()
        }



        params["Authorization"] =
            "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJVU0VSOjMiLCJsb2dpblNpZ24iOiI5ODE1NjYiLCJuYmYiOjE2NTc3OTI2MTQsInVzZXJUeXBlIjoiVVNFUiIsImV4cCI6NDc3OTg1NjYxNCwidXNlcklkIjoiMyIsImlhdCI6MTY1Nzc5MjYxNH0.uo99c58YQksMnkM6-nDIMlz1yS40gGYwvsmWp3aZUN4"





        if (params.isNotEmpty()) {
            for ((key, value) in params.entries) {
                httpBuilder.addQueryParameter(key, value)
            }
        }

        val request: Request = Request.Builder()
            .get()
            .url(httpBuilder.build())
            .addHeader(
                "Authorization",
                "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJVU0VSOjMiLCJsb2dpblNpZ24iOiI5ODE1NjYiLCJuYmYiOjE2NTc3OTI2MTQsInVzZXJUeXBlIjoiVVNFUiIsImV4cCI6NDc3OTg1NjYxNCwidXNlcklkIjoiMyIsImlhdCI6MTY1Nzc5MjYxNH0.uo99c58YQksMnkM6-nDIMlz1yS40gGYwvsmWp3aZUN4"
            )
            .build()


        try {
            val body = okHttpClient.newCall(request).execute().body?.string();


            val json = JSONObject(body.toString())["data"]


            if (json != "null") {


                val entity: Summary =
                    Gson().fromJson(
                        json.toString(),
                        Summary::class.java
                    )

                withContext(Dispatchers.Main) {
                    callback(entity)
                }

            }

        } catch (e: Exception) {
            e.printStackTrace();

        } finally {
            // optional finally block
        }


    }

}


//fun getPodcastDailySummary(
//    baseView: BaseView?,
//    rechargeTime: Long?,
//    consumptionTime:Long?,
//    callback: (GetPodcastDailySummaryQuery.GetPodcastDailySummary) -> Unit,
//) {
//
//    GlobalScope.launch {
//        val response = try {
//            apolloClient.query(
//                GetPodcastDailySummaryQuery(
//                    Input.optional(rechargeTime),
//                    Input.optional(consumptionTime),
//                )
//            ).await()
//        } catch (e: ApolloException) {
//            Log.e("error",e.toString())
//
//            parseError(e, baseView = baseView)
//            return@launch
//        }
//        val data = response.data
//        withContext(Dispatchers.Main) {
//            if (data == null || response.hasErrors()) {
//                parseResponse(response, baseView)
//                return@withContext
//            }
//
//            data.let {
//                it.podcastDailySummary?.let { it1 -> callback(it1) }
//            }
//        }
//    }
//
//}

