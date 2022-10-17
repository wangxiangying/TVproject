package com.tools.tvproject

import android.annotation.SuppressLint
import android.content.Context
import android.media.MediaPlayer
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import base.ui.BaseView
import com.tools.tvproject.net.getPodcastDailySummary
//import com.tools.tvproject.net.getUserDetail
//import com.amap.api.maps.AMap
//import com.amap.api.maps.CameraUpdateFactory
//import com.amap.api.maps.MapView
//import com.amap.api.maps.MapsInitializer
//import com.amap.api.maps.model.*
//import com.amap.api.services.district.DistrictItem
//import com.baidu.tts.client.SpeechSynthesizer
//import com.baidu.tts.client.TtsMode
import java.math.BigDecimal
import java.text.DecimalFormat
import java.text.SimpleDateFormat
import java.util.*

open class Page : AppCompatActivity(), BaseView {

    var isADDed = false

    private val char1 = "(";
    private val char2 = "（";

    private val unit = "元"

    var day: Int = -1

    var decimalFormat = DecimalFormat("0.00")

    lateinit var todayPanel: LinearLayout

    lateinit var rechargeList: RecyclerView

    lateinit var consumptionsList: RecyclerView

    lateinit var merchantList: RecyclerView

    lateinit var merchantTopList: RecyclerView

    lateinit var timeView: TextView

    lateinit var consumerCount: TextView

    @Volatile
    var rechargeTime: Long? = null

    @Volatile
    var consumptionTime: Long? = null

    private val list: MutableList<NavLayout> = mutableListOf()

    var listPeople: MutableList<Consume> = mutableListOf()  //储值

    var listMerchant: MutableList<Consume> = mutableListOf()  //消费

    var listMerchCount: MutableList<Consume> = mutableListOf()  //店铺消费数量

    var countlistMerch: MutableList<String> = mutableListOf()

    var listMerchTopCount: MutableList<Consume> = mutableListOf()  //店铺消费总榜

    var listTopMap: HashMap<String, Int> = hashMapOf()

    var listTopLogoMap: HashMap<String, String> = hashMapOf()


    val t: Long = 1640261632L

//    var mSpeechSynthesizer: SpeechSynthesizer? = null

    var i0: Int = 0

    var i1: Int = 0

    var i2: Float = 0.00f

    var i3: Int = 0

    var lastPeopleCount = 0

//    private val mapView: MapView? = null
//
//    private var aMap: AMap? = null

//    private val mapStyleOptions = CustomMapStyleOptions()


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        this.requestWindowFeature(Window.FEATURE_NO_TITLE)

        this.window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        setContentView(R.layout.layout_show_info)
        this.window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
        supportActionBar?.hide()
        initTTs(this)
        initView()
//        MapsInitializer.updatePrivacyShow(this, true, true)
//        MapsInitializer.updatePrivacyAgree(this, true)
//        val mapView = findViewById<View>(R.id.map) as MapView
//        mapView.onCreate(savedInstanceState) // 此方法必须重写
//        aMap = mapView.map?.apply {
//            uiSettings.isZoomControlsEnabled = true //隐藏 放大缩小
//            uiSettings.setZoomInByScreenCenter(true)
//            isTrafficEnabled = true // 显示实时交通状况
//            mapType = AMap.MAP_TYPE_NORMAL // 卫星地图模式
////            val search = DistrictSearch(this@Page)
////            val query = DistrictSearchQuery()
////            query.keywords = "西城区" //传入关键字
////            query.isShowBoundary = true //是否返回边界值
////            search.query = query
////            search.setOnDistrictSearchListener { result ->
////                val district = result.district
////                for (districtItem in district) {
////                    val listDistrict = parseData(districtItem)
////                    val polylineOption = PolylineOptions()
////                    polylineOption.addAll(listDistrict)
////                    polylineOption.width(6f).color(Color.BLUE)
////
////                    addPolyline(polylineOption)
////
////                }
////            }
////            search.searchDistrictAsyn()
//        }

    }

//    open fun parseData(districtItem: DistrictItem): List<LatLng>? {
//        val polyStr = districtItem.districtBoundary()
//        if (polyStr == null || polyStr.isEmpty()) {
//            return null
//        }
//        val list: MutableList<LatLng> = ArrayList()
//        for (str in polyStr) {
//            val lat = str.split(";").toTypedArray()
//            var isFirst = true
//            var firstLatLng: LatLng? = null
//            for (latStr in lat) {
//                val lats = latStr.split(",").toTypedArray()
//                if (isFirst) {
//                    isFirst = false
//                    firstLatLng = LatLng(lats[1].toDouble(), lats[0].toDouble())
//
//                }
//                list.add(LatLng(lats[1].toDouble(), lats[0].toDouble()))
//            }
//
//            if (firstLatLng != null) {
//                list.add(firstLatLng)
//            }
//
//        }
//        return list
//    }


    /**
     * 方法必须重写
     */
    override fun onResume() {
        super.onResume()
//        mapView?.onResume()
        initTrueData()
    }

    /**
     * 方法必须重写
     */
    override fun onPause() {
        super.onPause()
//        mapView?.onPause()
        pauseCountDown()
    }

    /**
     * 方法必须重写
     */
    override fun onDestroy() {
        super.onDestroy()
//        mapView?.onDestroy()
        pauseCountDown()
    }

    private fun initTTs(context: Context?) {
//        mSpeechSynthesizer = SpeechSynthesizer.getInstance()
//        mSpeechSynthesizer?.let { mSpeechSynthesizer ->
//            mSpeechSynthesizer.setAppId("25376764" /*这里只是为了让Demo运行使用的APPID,请替换成自己的id。*/)
//            mSpeechSynthesizer.setApiKey(
//                "9PK6GOhXWgiH0L0fB8kanGBC",
//                "E3NGc3o4rYEysa72Hu2UnEGZ2T2eR0aF"
//            )
//            mSpeechSynthesizer.setContext(context) // this 是Context的之类，如Activity
//            mSpeechSynthesizer.setParam(
//                SpeechSynthesizer.PARAM_AUDIO_ENCODE,
//                SpeechSynthesizer.AUDIO_ENCODE_PCM
//            )
//            mSpeechSynthesizer.setParam(
//                SpeechSynthesizer.PARAM_AUDIO_RATE,
//                SpeechSynthesizer.AUDIO_BITRATE_PCM
//            )
//            mSpeechSynthesizer.auth(TtsMode.ONLINE) // 纯在线
//            mSpeechSynthesizer.setParam(SpeechSynthesizer.PARAM_VOLUME, "3")
//            mSpeechSynthesizer.setParam(SpeechSynthesizer.PARAM_SPEAKER, "5") // 设置发声的人声音，在线生效
//            mSpeechSynthesizer.initTts(TtsMode.ONLINE)
//        }
    }


    @Synchronized
    fun Speak(name: String?, money: Int) {

//        MediaPlayer.create(this, R.raw.dingdong).apply {
//            start()
//        }

        var id = R.raw.bianpao2
        when {
            money < 300 -> {
                id = R.raw.sound
            }
            money < 600 -> {
                id = R.raw.sound4
            }
            money > 1000 -> {
                id = R.raw.dong
            }
        }

        MediaPlayer.create(this, id).apply {
            start()
        }
//        mSpeechSynthesizer?.speak(name + "储值" + money + "元")
    }

    private fun playSound(id: Int) {
        MediaPlayer.create(this, id).apply {
            start()
        }
    }

    var rechargeAdapter = RechargeAdapter(listPeople)
    var consumptionsAdapter = ConsumptionsAdapter(this, listMerchant)
    var merchantAdapter = MerchantAdapter(listMerchCount)
    var merchantTopListAdapter = MerchantAdapter(listMerchTopCount);

    @SuppressLint("SimpleDateFormat")
    private fun initView() {
        todayPanel = findViewById(R.id.todayPanel)
        rechargeList = findViewById(R.id.rechargeList)
        consumptionsList = findViewById(R.id.consumptionsList)
        merchantList = findViewById(R.id.merchantList)
        timeView = findViewById(R.id.timeTextView)
        consumerCount = findViewById(R.id.consumerCount)
        merchantTopList = findViewById(R.id.merchantTopList);

        val time =
            System.currentTimeMillis() //long now = android.os.SystemClock.uptimeMillis();
        val format = SimpleDateFormat("yyyy年MM月dd日")
        val d1 = Date(time)
        val t1: String = format.format(d1)
        timeView.text = t1


//        getUserDetail("88137") {
//
//
//        }

    }

//    private var oldListMerchantTemp = HashMap<String, Int>()
//    private fun setMapMarker(consumer: Consume) {
//
//        consumer.merchantName?.let {
//            val count = oldListMerchantTemp[it]
//            if (oldListMerchantTemp.containsKey(it)) {
//                if (count == consumer.totalConsumption) {
//                    return
//                }
//            }
//            oldListMerchantTemp[consumer.merchantName!!] = consumer.totalConsumption!!
//        } ?: let {
//            return
//        }
//
//
//        val markView = MarkView(this@Page)
//        consumer.merchantLogo?.let {
//            if (!this.isDestroyed) {
//                markView.setImage(it)
//            }
//        }
//
//        consumer.totalConsumption?.let {
//            if (!this.isDestroyed) {
//                markView.setCount(it)
//            }
//        }
//
//        consumer.merchantName?.let {
//            if (!this.isDestroyed) {
//                markView.setName(it)
//            }
//        }
//
//        //绘制marker
//        aMap?.addMarker(
//            MarkerOptions()
//                .position(consumer.getLatLng())
//                .icon(
//                    BitmapDescriptorFactory.fromView(markView)
//                ).anchor(0.5f, 0.5f)
//        )
//
////       val center = getCenterOfGravityPoint(list)
////       var position1= CameraPosition( consume.getLatLng(),
////            10f,12f,0f)
//
//        val position2 = CameraPosition.fromLatLngZoom(
//            consumer.getLatLng(),
//            10f
//        )
//        val cp = CameraUpdateFactory.newCameraPosition(position2)
//
//        aMap?.animateCamera(cp)
//    }

    /**
     * 获取不规则多边形重心点
     *
     * @param mPoints
     * @return
     */
//    fun getCenterOfGravityPoint(mPoints: List<LatLng>): LatLng {
//        var area: Double = 0.0//多边形面积
//        var x: Double = 0.0
//        var y: Double = 0.0// 重心的x、y
//        for (i in 1..mPoints.size) {
//            val iLat = mPoints[i % mPoints.size].latitude
//            val iLng = mPoints[i % mPoints.size].longitude
//            val nextLat = mPoints[i - 1].latitude
//            val nextLng = mPoints[i - 1].longitude
//            val temp = (iLat * nextLng - iLng * nextLat) / 2.0f
//            area += temp
//            x += temp * (iLat + nextLat) / 3.0f
//            y += temp * (iLng + nextLng) / 3.0f
//        }
//        x /= area
//        y /= area
//        return LatLng(x, y)
//    }

    var countDownTimer2: CountDownTimer? = null
    private fun initTrueData() {
        countDownTimer2 = object : CountDownTimer(t, 10000) {
            override fun onFinish() {
            }

            @SuppressLint("NotifyDataSetChanged")
            override fun onTick(millisUntilFinished: Long) {
                refreshData()
            }
        }

        countDownTimer2?.start()
    }


    private fun pauseCountDown() {
        countDownTimer2?.cancel()
        countDownTimer2 = null
    }


    @SuppressLint("SimpleDateFormat")
    fun Long.toCNWeek(): String {
        return "星期" + when (this.fixTime().getDateWeek()) {
            0 -> "日"
            1 -> "一"
            2 -> "二"
            3 -> "三"
            4 -> "四"
            5 -> "五"
            6 -> "六"
            else -> ""
        }
    }

    /**
     * 获取时间戳是星期几
     * @return [Int] [Calendar.SUNDAY]
     */
    private fun Long.getDateWeek(): Int {
        val calendar = Calendar.getInstance()
        calendar.time = Date(this)
        return calendar.get(Calendar.DAY_OF_WEEK) - 1
    }

    private fun Long.fixTime(): Long {
        return (this.toString() + "0000000000000000").substring(0, 13).toLong()
    }

    var listMerchantAll: MutableList<Consume> = mutableListOf()

    var initIt: Boolean = false;


    @SuppressLint("SetTextI18n", "SimpleDateFormat", "NotifyDataSetChanged")
    fun refreshData() {

        val times =
            System.currentTimeMillis()

        val format = SimpleDateFormat("yyyy年MM月dd日")

        val d1 = Date(times)

        val t1: String = format.format(d1)

        timeView.text = t1 + "     " + times.toCNWeek()

        val c = Calendar.getInstance() //

        val newDay = c.get(Calendar.DAY_OF_MONTH)

        if (newDay != day) {
            rechargeTime = null
            consumptionTime = null
            day = newDay
        }

        if (rechargeTime == null && consumptionTime == null) {
            val currentTime = Date().time

            val zeroTime =
                currentTime - (currentTime + TimeZone.getDefault().rawOffset) % (1000 * 3600 * 24)

            rechargeTime = zeroTime
            consumptionTime = zeroTime

            initIt = true;
        }


        getPodcastDailySummary(
            rechargeTime,
            consumptionTime
        ) { data ->
            timeView.post {
                val newCount = data.newUsersNumber ?: 0
                if (newCount > lastPeopleCount) {  //新增用户播放声音
                    playSound(R.raw.aigei_com)
                    lastPeopleCount = newCount
                }

                i0 = newCount

                i1 = fixString(data.rechargeAmount).setScale(0, BigDecimal.ROUND_HALF_UP).toInt()

                i2 = fixString(data.consumptionAmount).setScale(2, BigDecimal.ROUND_HALF_UP)
                    .toFloat()

                i3 = data.consumptionNumber ?: 0

                if (!isADDed) {
                    isADDed = true
                    newNavData(
                        R.drawable.biaodan,
                        "新增用户",
                        i0,
                        "",
                        AutoIncrementUtil.INTTYPE
                    ).apply(::operation)

                    newNavData(
                        R.drawable.shoujinbi,
                        "充值金额",
                        i1,
                        unit,
                        AutoIncrementUtil.FLOATTYPE
                    ).apply(::operation)

                    newNavData(
                        R.drawable.mingxi,
                        "消费金额",
                        i2,
                        unit,
                        AutoIncrementUtil.FLOATTYPE
                    ).apply(::operation)

                    newNavData(
                        R.drawable.shangdian,
                        "消费笔数",
                        i3,
                        "",
                        AutoIncrementUtil.INTTYPE
                    ).apply(::operation)

                } else {

                    list[0].smallTitle.text = i0.toString()

                    list[1].smallTitle.text = i1.toString() + unit

                    list[2].smallTitle.text = decimalFormat.format(i2) + unit

                    list[3].smallTitle.text = i3.toString()

                }

                val listRechargeTemp = data.recharges

                val listConsumptionsTemp = data.consumptions

                val listMerchantTemp = data.merchants


                listMerchTopCount = getTopList(data.merchants)

                var names = ""

                listMerchTopCount.forEach {

                    names += it.totalConsumption.toString() + "  "
                }

                if (initIt) {

                    initIt = false
                    rechargeAdapter = RechargeAdapter(listRechargeTemp)
                    rechargeList.adapter = rechargeAdapter
                    rechargeList.layoutManager =
                        LinerManger(this@Page, LinearLayoutManager.VERTICAL, false)
                    rechargeAdapter.notifyDataSetChanged()

                    consumptionsAdapter = ConsumptionsAdapter(this@Page, listConsumptionsTemp)
                    consumptionsList.adapter = consumptionsAdapter
                    consumptionsList.layoutManager =
                        LinerManger(this@Page, LinearLayoutManager.VERTICAL, false)
                    consumptionsAdapter.notifyDataSetChanged()

                    merchantAdapter = MerchantAdapter(listMerchantTemp)
                    merchantList.adapter = merchantAdapter
                    merchantList.layoutManager = GridLayoutManager(this, 2)
                    merchantAdapter.notifyDataSetChanged()


                } else {

                    listRechargeTemp.forEach {
                        rechargeAdapter.addItem(it)


//                        it.userId?.let { it1 ->
//                            getUserDetail(it1) {
//
//
//                            }
//                        }

                        it.amount?.let { it1 -> Speak(it.userName, it1.toInt()) }
                    }

                    if (listRechargeTemp.size > 0) {
                        rechargeList.postDelayed({ rechargeList.scrollToPosition(0) }, 500)
                    }

                    listConsumptionsTemp.forEach {
                        consumptionsAdapter.addItem(it)
                    }

                    if (listConsumptionsTemp.size > 0) {
                        playSound(R.raw.com)
                        consumptionsList.postDelayed({ consumptionsList.scrollToPosition(0) }, 500)
                    }


                    if (listMerchantTemp.size > 0) {
                        if (listMerchantAll.containsAll(listConsumptionsTemp) && listConsumptionsTemp.containsAll(
                                listMerchantAll  //比较两个列表是否相同
                            )
                        ) {
                            merchantAdapter = MerchantAdapter(listMerchantTemp)

                            merchantList.adapter = merchantAdapter

                            merchantAdapter.notifyDataSetChanged()

                            listMerchantAll = listConsumptionsTemp

                        }
                    }


                }

                merchantTopListAdapter = MerchantAdapter(listMerchTopCount)
                merchantTopList.adapter = merchantTopListAdapter
                merchantTopList.layoutManager = GridLayoutManager(this, 2)
                merchantTopListAdapter.notifyDataSetChanged()


                listRechargeTemp.forEach {
                    val newTime = it.createTime
                    if (rechargeTime == null || rechargeTime!! < newTime) {
                        rechargeTime = newTime
                    }

                }

                listConsumptionsTemp.forEach {
                    val newTime = it.createTime
                    if (consumptionTime == null || consumptionTime!! < newTime) {
                        consumptionTime = newTime
                    }
                    it.merchantName?.let { it1 ->
                        if (!countlistMerch.contains(it1))
                            countlistMerch.add(it1)
                    }
                }

                consumerCount.text = "消费店铺 （" + countlistMerch.count() + "家）";

            }
        }
    }


    fun getTopList(merchants: List<Consume>): MutableList<Consume> {

        listTopMap.clear()

        val list: MutableList<Consume> = mutableListOf()  //店铺消费总榜

        merchants.forEach { data ->
            data.merchantName?.let { lessName ->
                val name = lessName.substringBefore(if (lessName.contains(char1)) char1 else char2)
                if (listTopMap.contains(name)) {
                    val count = listTopMap[name]
                    if (count != null) {
                        listTopMap.put(name, count + data.totalConsumption)
                    }
                } else {
                    listTopMap.put(name, data.totalConsumption)
                    data.merchantLogo?.let { listTopLogoMap.put(name, it) }
                }
            }
        }


        listTopMap.forEach {
            val consume = Consume()
            consume.totalConsumption = it.value
            consume.merchantName = it.key
            consume.merchantLogo = listTopLogoMap[it.key]


            list.add(consume)
        }

        list.sortBy { consume -> consume.totalConsumption.times(-1) }

        var count = list.count()
        if (count > 8) {
            count = 8
        }


        return list.subList(0, count)
    }


//
//    fun ShowOneConsume(consume: Consume)
//    {
//        val cp =
//            CameraUpdateFactory.newCameraPosition(
//                CameraPosition.fromLatLngZoom(
//                    center,
//                    10f
//                )
//            )
//        aMap?.animateCamera(cp)
//    }

//
//    fun showMap(listMerchantTemp: MutableList<Consume>) {
//
//        //以下为计算中心点
//        val list = ArrayList<LatLng>()
//        listMerchantTemp.reversed().onEach { item ->
//            setMapMarker(item)
//            item.lat?.let { item.lng?.let { it1 -> LatLng(it, it1) } }?.let { list.add(it) }
//        }
//        val center = getCenterOfGravityPoint(list)
//        val cp =
//            CameraUpdateFactory.newCameraPosition(
//                CameraPosition.fromLatLngZoom(
//                    center,
//                    10f
//                )
//            )
//        aMap?.animateCamera(cp)
//    }


    private fun operation(layout: NavLayout) {
        todayPanel.addView(layout)
        list.add(layout)
    }

    private fun newNavData(
        imageId: Int,
        str: String,
        value: Int,
        unit: String,
        type: String
    ): NavLayout {
        return newSimpleNav(imageId, str).apply {
            setValueWithUnit(value, unit)
        }
    }

    private fun newNavData(
        imageId: Int,
        str: String,
        value: Float,
        unit: String,
        type: String
    ): NavLayout {
        return newSimpleNav(imageId, str).apply {
            setValueWithUnit(value, unit)
        }
    }

    private fun newSimpleNav(imageId: Int, str: String): NavLayout {
        return NavLayout(this).apply {
            imageIV.setImageResource(imageId)
            bigTitle.text = str
        }
    }

    override fun showLoading() {

    }

    override fun hideLoading() {

    }

    override fun onError(errorCode: String, error: String) {
        Log.e(errorCode, error)
    }

}