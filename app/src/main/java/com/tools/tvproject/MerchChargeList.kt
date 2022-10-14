package com.tools.tvproject

//import com.amap.api.maps.model.LatLng
import com.amap.api.maps.model.LatLng
import com.tools.tvproject.net.Consumption
import java.math.BigDecimal


fun fixData(data: BigDecimal?): BigDecimal {

    if (data == null) {
        return BigDecimal(0)
    }

    return data
}

fun fixString(str: String?): BigDecimal {

    val data = when (str) {
        "null", null -> {
            BigDecimal(0)
        }
        else -> {
            BigDecimal(str)
        }
    }

    return data
}

fun initByTrueDate(data: Consumption): Consume {
    return Consume().apply {
        merchantLogo = data.merchantLogo
        merchantName = data.merchantName
        amount = fixString(data.amount).setScale(2, BigDecimal.ROUND_HALF_UP).toFloat()
        userLogo = data.userAvatar
        userName = data.userName
        totalConsumption = data.totalConsumption
        createTime = data.createTime
        lat = data.lat?.toDouble()
        lng = data.lng?.toDouble()
    }
}

//fun trueList(datas: MutableList<Consumption>): MutableList<Consumption> {
//    return datas.map { initByTrueDate(it) }.toMutableList()
//}


data class Consume(


    /**
     * 支付金额
     */
    var amount: Float? = 0.0f,

    /**
     * 消费笔数
     */
    var totalConsumption: Int = 0,
    /**
     * 商户Logo
     */
    var merchantLogo: String? = "",
    /**
     * 商户名称
     */
    var merchantName: String? = "",
    /**
     * 创建时间
     */
    var createTime: Long = 0,

    /** 商户Logo */
    var userLogo: String? = "",
    /**
     * 商户名称
     */
    var userName: String? = "",


    var userAvatar: String? = "",

    var imageId: Int? = 0,

    var lat: Double? = null,

    var lng: Double? = null

) {
    fun getLatLng(): LatLng? {
        if (lat != null && lng != null) {
            return LatLng(lat!!, lng!!)
        }
        return null
    }

    override fun toString(): String {
        return  merchantName + "  "+ totalConsumption;
    }
}
