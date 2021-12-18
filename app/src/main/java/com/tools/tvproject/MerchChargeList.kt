package com.tools.tvproject


data class Consume(
    /**
     * 支付金额
     */
    var amount: Float?=0.0f,

    /**
     * 充值
     */
    var rechage:Int=0,
    /**
     * 商户Logo
     */
    var merchantLogo: String?="",
    /**
     * 商户名称
     */
    var merchantName: String?="",
    /**
     * 创建时间
     */
    var createTime: Any?="",

    /** 商户Logo */
    var userLogo: String?="",
    /**
     * 商户名称
     */
    var userName: String?="",


    var imageId:Int?=0

)


data class OrderBean(
    val __typename: String = "OrderVo",
    /**
     * 支付金额
     */
    val amount: String?,
    /**
     * 消耗卡余额
     */
    val cardAmount: String?,
    /**
     * 购卡数量
     */
    val cardCount: Int?,
    /**
     * 饭折卡储值 才有卡号 原型图显示只要后4位
     */
    val cardNumber: String?,
    /**
     * 创建时间
     */
    val createTime: Any?,
    /**
     * 优惠金额
     */
    val discountAmount: String?,
    /**
     * 过期时间
     */
    val expiredTime: Any?,
    /**
     * 商户Logo
     */
    val merchantLogo: String?,
    /**
     * 商户名称
     */
    val merchantName: String?,
    /**
     * 商户操作人员ID
     */
    val merchantOperatorId: Any?,

    /**
     * 1-现金 2-刷卡 3-饭折卡 4-支付宝 5-微信
     */
    val payTypeStr: String?,

    /**
     * 状态 处理中(10)  交易成功(20) 退款(30) 已过期(40) 已删除(90)
     */
    val statusStr: String?,

    /**
     * 修改时间
     */
    val updateTime: Any?,
    /**
     * 用户ID
     */
    val userId: Any?,
    /**
     * 订单ID
     */
    val orderId: String?,
    /**
     * 饭折卡消费才有 消费的店家ID
     */
    val merchantId: Any?
)

data class MerchantBean(
    val __typename: String = "Merchant",
    /**
     * 加密ID
     */
    val id: String,
    /**
     * 商户Logo
     */
    val logo: String?,
    /**
     * 商户名称
     */
    val name: String,
    /**
     * 评分
     */
    val star: Int?,
    /**
     * 人均
     */
    val avg: Double?,
    /**
     * 商户地址
     */
    val address: String,
    /**
     * 联系人
     */
    val contact: String?,
    /**
     * 纬度
     */
    val lat: String?,
    /**
     * 经度
     */
    val lng: String?,
    /**
     * 品牌
     */
    val brand: String?,
    /**
     * 市
     */
    val city: String?,
    /**
     * 省
     */
    val province: String?,
    /**
     * 是否连锁
     */
    val chain: Boolean?,
    /**
     * 是否收藏(登陆状态会有这个属性)
     */
    val collected: Boolean?,
    /**
     * 商户类型
     */
    val category: String?,
    /**
     * 商编
     */
    val merchantNumber: String,
    /**
     * 收款二维码
     */
    val qrCodeContent: String,
    /**
     * 营业时间
     */
    val businessTime: String?,
    /**
     * 商圈
     */
    val region: String?,
    /**
     * 标签
     */
    val tags: String?,
//    /**
//     * 营业状态
//     */
//    val businessStatus: BusinessStatus?,
    /**
     * 营业状态显示字符串
     */
    val businessStatusStr: String?,
    /**
     * 距离，单位米
     */
    val distance: Any?,
    /**
     * 联系电话
     */
    val telephone: String?,
//    /**
//     * 商户折扣信息
//     */
//    val discounts: List<Discount?>?
)