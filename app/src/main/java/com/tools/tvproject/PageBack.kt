//package com.tools.tvproject
//
//import android.annotation.SuppressLint
//import android.content.Context
//import android.graphics.Color
//import android.media.MediaPlayer
//import android.os.Bundle
//import android.os.CountDownTimer
//import android.util.Log
//import android.view.View
//import android.view.Window
//import android.view.WindowManager
//import android.widget.LinearLayout
//import android.widget.TextView
//import android.widget.Toast
//import androidx.appcompat.app.AppCompatActivity
//import androidx.recyclerview.widget.LinearLayoutManager
//import androidx.recyclerview.widget.RecyclerView
//import base.ui.BaseView
//import cn.fanzhe.net.com.tools.tvproject.net.getPodcastDailySummary
//import com.amap.api.maps.*
//import com.amap.api.maps.model.*
//import com.amap.api.services.district.DistrictItem
//import com.baidu.tts.client.SpeechSynthesizer
//import com.baidu.tts.client.TtsMode
//import com.tools.tvproject.base.utils.utils.X5WebView
//import java.math.BigDecimal
//import java.text.DecimalFormat
//import java.text.SimpleDateFormat
//import java.util.*
//import com.amap.api.services.district.DistrictResult
//
//import com.amap.api.services.district.DistrictSearch
//import com.amap.api.services.district.DistrictSearch.OnDistrictSearchListener
//
//import com.amap.api.services.district.DistrictSearchQuery
//import com.amap.api.services.core.LatLonPoint
//import com.amap.api.maps.model.LatLng
//import com.amap.api.maps.model.PolylineOptions
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//open class PageBack : AppCompatActivity(), BaseView {
//
//    var webViews: X5WebView? = null
//
//    val isDemo = false
//
//    val unit = "元"
//
//    var day: Int = -1
//
//    var decimalFormat = DecimalFormat("0.00")
//
//    lateinit var todayPanel: LinearLayout
//
//    lateinit var rechargeList: RecyclerView
//
//    lateinit var consumptionsList: RecyclerView
//
//    lateinit var merchantList: RecyclerView
//
//    lateinit var timeView: TextView
//
//    lateinit var webView: X5WebView
//
//    val list: MutableList<NavLayout> = mutableListOf()
//
//    var listPeople: MutableList<Consume> = mutableListOf()  //储值
//
//    var listMerchant: MutableList<Consume> = mutableListOf()  //消费
//
//    var listMerchCount: MutableList<Consume> = mutableListOf()  //店铺消费数量
//
//    val t: Long = 1640261632L
//
//    var mSpeechSynthesizer: SpeechSynthesizer? = null
//
//    var i0: Int = 0
//    var i1: Int = 0
//    var i2: Float = 0.00f
//    var i3: Int = 0
//
//    var lastPeopleCount = 0
//
//    private val mapView: MapView? = null
//    private val aMap: AMap? = null
//    private val mapStyleOptions = CustomMapStyleOptions()
//
//    val imageIds = arrayOf(
//        R.drawable.tx1,
//        R.drawable.tx2,
//        R.drawable.tx3,
//        R.drawable.tx4,
//        R.drawable.tx5,
//        R.drawable.tx6,
//        R.drawable.tx7,
//        R.drawable.tx8,
//        R.drawable.tx9,
//        R.drawable.tx10,
//        R.drawable.tx11,
//        R.drawable.tx12,
//        R.drawable.tx13,
//        R.drawable.tx14,
//        R.drawable.tx15,
//        R.drawable.tx16,
//        R.drawable.tx17,
//        R.drawable.tx18,
//        R.drawable.tx19,
//        R.drawable.tx20
//    )
//
//
//    val mer0 = Consume(
//        merchantName = "咏巷老北京炸鸡（昌平悦荟店）",
//        merchantLogo = "https://yt-card-system.oss-cn-beijing.aliyuncs.com/1634786283389wyBUS.jpg"
//    )
//
//    val mer1 = Consume(
//        merchantName = "德川家日本料理（石家庄店）",
//        merchantLogo = "http://yt-card-system.oss-cn-beijing.aliyuncs.com/1597742067443MDuvY.jpg"
//    )
//
//    val mer2 = Consume(
//        merchantName = "局气（延庆店）",
//        merchantLogo = "https://yt-card-system.oss-cn-beijing.aliyuncs.com/1636527278316byyDa.jpg"
//    )
//
//    val mer3 = Consume(
//        merchantName = "呷哺呷哺（延庆亿隆店）",
//        merchantLogo = "https://yt-card-system.oss-cn-beijing.aliyuncs.com/1608276081785OkjhW.png"
//    )
//
//    val mer4 = Consume(
//        merchantName = "呷哺呷哺（延庆万达店）",
//        merchantLogo = "https://yt-card-system.oss-cn-beijing.aliyuncs.com/1608276081785OkjhW.png"
//    )
//
//    val mer5 = Consume(
//        merchantName = "大鸭梨（房山店）",
//        merchantLogo = "https://yt-card-system.oss-cn-beijing.aliyuncs.com/1602487662549QGCAt.jpg"
//    )
//
//    val mer6 = Consume(
//        merchantName = "汉拿山（门头沟龙湖店）",
//        merchantLogo = "https://yt-card-system.oss-cn-beijing.aliyuncs.com/1615282724253jnssn.jpg"
//    )
//
//    val mer7 = Consume(
//        merchantName = "茶太良品（长安龙湖天街店）",
//        merchantLogo = "https://yt-card-system.oss-cn-beijing.aliyuncs.com/1629789600849SsRbz.jpg"
//    )
//
//    val mer8 = Consume(
//        merchantName = "管氏翅吧(昌平店)",
//        merchantLogo = "http://yt-card-system.oss-cn-beijing.aliyuncs.com/1590133085361DoePq.jpg"
//    )
//
//    val mer11 = Consume(
//        merchantName = "绿茶餐厅(昌平悦荟店)",
//        merchantLogo = "https://yt-card-system.oss-cn-beijing.aliyuncs.com/1628144093306OiUMa.jpg"
//    )
//
//    val mer9 = Consume(
//        merchantName = "池田寿司(昌平万科店)",
//        merchantLogo = "https://yt-card-system.oss-cn-beijing.aliyuncs.com/1629443269842aqCPn.jpg"
//    )
//
//    val mer10 = Consume(
//        merchantName = "羲和雅苑烤鸭坊（万寿路店）",
//        merchantLogo = "https://yt-card-system.oss-cn-beijing.aliyuncs.com/1635649660541jnWrC.jpg"
//    )
//
//    val mer12 = Consume(
//        merchantName = "松本楼日式烧肉（万寿路店）",
//        merchantLogo = "https://yt-card-system.oss-cn-beijing.aliyuncs.com/1632711893140fEHBu.jpg"
//    )
//
//    val mer13 = Consume(
//        merchantName = "峨嵋酒家（凯德晶品店）",
//        merchantLogo = "https://yt-card-system.oss-cn-beijing.aliyuncs.com/1631696035243BBzUW.jpg"
//    )
//
//    val mer14 = Consume(
//        merchantName = "真功夫（城乡店）",
//        merchantLogo = "https://yt-card-system.oss-cn-beijing.aliyuncs.com/1629084867000TEMRk.jpg"
//    )
//
//    val mer15 = Consume(
//        merchantName = "煲仔皇（公主坟城乡店）",
//        merchantLogo = "https://yt-card-system.oss-cn-beijing.aliyuncs.com/1621840562811miCgp.jpg"
//    )
//
//    val mer16 = Consume(
//        merchantName = "巴蜀王婆大虾（莲花桥店）",
//        merchantLogo = "https://yt-card-system.oss-cn-beijing.aliyuncs.com/1620791051271Hkzwj.jpg"
//    )
//    val mer17 = Consume(
//        merchantName = "苏小牛精酿主题串吧（华熙店）",
//        merchantLogo = "https://yt-card-system.oss-cn-beijing.aliyuncs.com/1632714956550Gnjqs.png"
//    )
//    val mer18 = Consume(
//        merchantName = "寻鲜记-老北京铜锅涮肉",
//        merchantLogo = "https://yt-card-system.oss-cn-beijing.aliyuncs.com/1633939638431IWATH.jpg"
//    )
//
//    val merchIds = arrayOf(
//        mer0,
//        mer1,
//        mer2,
//        mer3,
//        mer4,
//        mer5,
//        mer6,
//        mer7,
//        mer8,
//        mer9,
//        mer10,
//        mer11,
//        mer12,
//        mer13,
//        mer14,
//        mer15,
//        mer16,
//        mer17,
//        mer18
//    )
//
//    val nameIds = arrayOf(
//        "小虾米",
//        "离我🎁",
//        "开心就好",
//        "知足常乐",
//        "US Social Security",
//        "大漠孤烟",
//        "听雨",
//        "lili",
//        "大眼镜",
//        "莉莉娅",
//        "吴彦祖",
//        "Amelia",
//        "Isabella",
//        "兔兔",
//        "紫菜卷",
//        "可乐可口",
//        "Nike",
//        "冬瓜",
//        "手谈",
//        "david",
//        "Jim",
//        "good idea",
//        "other people"
//    )
//
//
////    override fun onConfigurationChanged(newConfig: Configuration?) {
////        // TODO Auto-generated method stub
////        try {
////            super.onConfigurationChanged(newConfig!!)
////            if (resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
////            } else if (resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
////            }
////        } catch (e: Exception) {
////            e.printStackTrace()
////        }
////    }
//
//    // /////////////////////////////////////////
//    // 向webview发出信息
//    private fun enableX5FullscreenFunc() {
//        if (webView.x5WebViewExtension != null) {
//            Toast.makeText(this, "开启X5全屏播放模式", Toast.LENGTH_LONG).show()
//            val data = Bundle()
//            data.putBoolean("standardFullScreen", false) // true表示标准全屏，false表示X5全屏；不设置默认false，
//            data.putBoolean("supportLiteWnd", false) // false：关闭小窗；true：开启小窗；不设置默认true，
//            data.putInt("DefaultVideoScreen", 2) // 1：以页面内开始播放，2：以全屏开始播放；不设置默认：1
//            webView.x5WebViewExtension.invokeMiscMethod(
//                "setVideoParams",
//                data
//            )
//        }
//    }
//
//    private fun disableX5FullscreenFunc() {
//        if (webView.x5WebViewExtension != null) {
//            Toast.makeText(this, "恢复webkit初始状态", Toast.LENGTH_LONG).show()
//            val data = Bundle()
//            data.putBoolean(
//                "standardFullScreen",
//                true
//            ) // true表示标准全屏，会调起onShowCustomView()，false表示X5全屏；不设置默认false，
//            data.putBoolean("supportLiteWnd", false) // false：关闭小窗；true：开启小窗；不设置默认true，
//            data.putInt("DefaultVideoScreen", 2) // 1：以页面内开始播放，2：以全屏开始播放；不设置默认：1
//            webView.x5WebViewExtension.invokeMiscMethod(
//                "setVideoParams",
//                data
//            )
//        }
//    }
//
//    private fun enableLiteWndFunc() {
//        if (webView.x5WebViewExtension != null) {
//            Toast.makeText(this, "开启小窗模式", Toast.LENGTH_LONG).show()
//            val data = Bundle()
//            data.putBoolean(
//                "standardFullScreen",
//                false
//            ) // true表示标准全屏，会调起onShowCustomView()，false表示X5全屏；不设置默认false，
//            data.putBoolean("supportLiteWnd", true) // false：关闭小窗；true：开启小窗；不设置默认true，
//            data.putInt("DefaultVideoScreen", 2) // 1：以页面内开始播放，2：以全屏开始播放；不设置默认：1
//            webView.x5WebViewExtension.invokeMiscMethod(
//                "setVideoParams",
//                data
//            )
//        }
//    }
//
//    private fun enablePageVideoFunc() {
//        if (webView.x5WebViewExtension != null) {
//            Toast.makeText(this, "页面内全屏播放模式", Toast.LENGTH_LONG).show()
//            val data = Bundle()
//            data.putBoolean(
//                "standardFullScreen",
//                false
//            ) // true表示标准全屏，会调起onShowCustomView()，false表示X5全屏；不设置默认false，
//            data.putBoolean("supportLiteWnd", false) // false：关闭小窗；true：开启小窗；不设置默认true，
//            data.putInt("DefaultVideoScreen", 1) // 1：以页面内开始播放，2：以全屏开始播放；不设置默认：1
//            webView.x5WebViewExtension.invokeMiscMethod(
//                "setVideoParams",
//                data
//            )
//        }
//    }
//
//    @SuppressLint("ClickableViewAccessibility")
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//
//        this.requestWindowFeature(Window.FEATURE_NO_TITLE)
//        this.window.setFlags(
//            WindowManager.LayoutParams.FLAG_FULLSCREEN,
//            WindowManager.LayoutParams.FLAG_FULLSCREEN
//        )
//        setContentView(R.layout.layout_show_info)
//        this.window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
//        supportActionBar?.hide()
//        initTTs(this)
//        initView()
//
//        MapsInitializer.updatePrivacyShow(this, true, true)
//        MapsInitializer.updatePrivacyAgree(this, true)
//
//        val mapView = findViewById<View>(R.id.map) as MapView
//        mapView.onCreate(savedInstanceState) // 此方法必须重写
//
//        val aMap = mapView.map
//
//        aMap.isTrafficEnabled = false // 显示实时交通状况
//
//        //地图模式可选类型：MAP_TYPE_NORMAL,MAP_TYPE_SATELLITE,MAP_TYPE_NIGHT
//        //地图模式可选类型：MAP_TYPE_NORMAL,MAP_TYPE_SATELLITE,MAP_TYPE_NIGHT
//        aMap.mapType = AMap.MAP_TYPE_NORMAL // 卫星地图模式
//
//
//        var latLng = LatLng(39.986919, 116.353369)
//
//
//        val cp = CameraUpdateFactory.newCameraPosition(CameraPosition.fromLatLngZoom(latLng, 7f))
//
//        aMap.animateCamera(cp)
//
//        //绘制marker
//        aMap.addMarker(
//            MarkerOptions()
//                .position(latLng)
//                .title("北京")
//                .snippet("简单描述")
//                .icon(
//                    BitmapDescriptorFactory.fromView(
//                        this.layoutInflater.inflate(
//                            R.layout.icon,
//                            null
//                        )
//                    )
//                ).anchor(0.5f, 0.5f)
//                .draggable(true)
//        )
//
//
//        val search = DistrictSearch(this)
//        val query = DistrictSearchQuery()
//        query.keywords = "朝阳区" //传入关键字
//
//        query.isShowBoundary = true //是否返回边界值
//
//        search.query = query
//        search.setOnDistrictSearchListener { result ->
//             val district = result.district
//            for (districtItem in district) {
////                val adcode = districtItem.adcode
////                val center = districtItem.center
////                val citycode = districtItem.citycode
////                val level = districtItem.level
////                val name = districtItem.name
////                val subDistrict = districtItem.subDistrict
////                val polyStr: Array<String> = districtItem.districtBoundary()
//                var listDistrict=  parseData(districtItem)
//
//                val polylineOption = PolylineOptions()
//                polylineOption.addAll(listDistrict)
//                polylineOption.width(6f).color(Color.BLUE)
//                aMap.addPolyline(polylineOption)
//
//            }
//
//        }
//        search.searchDistrictAsyn()
//
//        if (isDemo) {
//            initDemoData()
//        } else {
//            initTrueData()
//        }
//    }
//
//    open fun parseData(districtItem: DistrictItem): List<LatLng>? {
//        val polyStr = districtItem.districtBoundary()
//        if (polyStr == null || polyStr.isEmpty()) {
//            return null
//        }
//        val list: MutableList<LatLng> = ArrayList()
//        for (str in polyStr) {
//
//            val lat = str.split(";").toTypedArray()
//            var isFirst = true
//            var firstLatLng: LatLng? = null
//            for (latstr in lat) {
//                val lats = latstr.split(",").toTypedArray()
//                if (isFirst) {
//                    isFirst = false
//                    firstLatLng = LatLng(lats[1].toDouble(), lats[0].toDouble())
//                }
//                list.add(LatLng(lats[1].toDouble(), lats[0].toDouble()))
//            }
//            if (firstLatLng != null) {
//                list.add(firstLatLng)
//            }
//        }
//        return list
//    }
//
//
//    /**
//     * 方法必须重写
//     */
//    override fun onResume() {
//        super.onResume()
//        mapView?.onResume()
//    }
//
//    /**
//     * 方法必须重写
//     */
//    override fun onPause() {
//        super.onPause()
//        mapView?.onPause()
//    }
//
//
//    /**
//     * 方法必须重写
//     */
//    override fun onDestroy() {
//        super.onDestroy()
//        mapView?.onDestroy()
//    }
//
//    private fun initTTs(context: Context?) {
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
//    }
//
//
//    @Synchronized
//    fun Speak(name: String?, money: Int) {
//
////        MediaPlayer.create(this, R.raw.dingdong).apply {
////            start()
////        }
//
//        var id = R.raw.bianpao2
//        if (money < 300) {
//            id = R.raw.sound
//        } else if (money < 600) {
//            id = R.raw.sound4
//        }
//
//        MediaPlayer.create(this, id).apply {
//            start()
//        }
//        mSpeechSynthesizer?.speak(name + "储值" + money + "元")
//    }
//
//    fun playSound(id: Int) {
//        MediaPlayer.create(this, id).apply {
//            start()
//        }
//    }
//
//    var rechargeAdapter = RechargeAdapter(listPeople)
//    var consumptionsAdapter = ConsumptionsAdapter(this, listMerchant)
//    var merchantAdapter = MerchantAdapter(listMerchCount)
//
//
//    private fun initView() {
//
//        todayPanel = findViewById(R.id.todayPanel)
//        rechargeList = findViewById(R.id.rechargeList)
//        consumptionsList = findViewById(R.id.consumptionsList)
//        merchantList = findViewById(R.id.merchantList)
//        timeView = findViewById(R.id.timeTextView)
//
//
//        var time =
//            System.currentTimeMillis() //long now = android.os.SystemClock.uptimeMillis();
//        val format = SimpleDateFormat("yyyy年MM月dd日")
//        val d1 = Date(time)
//        val t1: String = format.format(d1)
//
//        timeView.text = t1
//
//
////        webView = findViewById<View>(R.id.webView) as X5WebView
////        webView.loadUrl("https://gf.ytpay.link/d/xhnlaxT7k/fan-zhe-jiao-yi-da-ping?orgId=1&refresh=5s")
////
////        window.setFormat(PixelFormat.TRANSLUCENT)
////
////        webView.view.overScrollMode = View.OVER_SCROLL_ALWAYS
////        webView.addJavascriptInterface(object : WebViewJavaScriptFunction {
////           override fun onJsFunctionCalled(tag: String?) {
////                // TODO Auto-generated method stub
////            }
////
////            @JavascriptInterface
////            fun onX5ButtonClicked() {
////                this@Page.enableX5FullscreenFunc()
////            }
////
////            @JavascriptInterface
////            fun onCustomButtonClicked() {
////                this@Page.disableX5FullscreenFunc()
////            }
////
////            @JavascriptInterface
////              fun onLiteWndButtonClicked() {
////                this@Page.enableLiteWndFunc()
////            }
////
////            @JavascriptInterface
////              fun onPageVideoClicked() {
////                this@Page.enablePageVideoFunc()
////            }
////        }, "Android")
//
//
//    }
//
//    private fun getMerchCountData(am: Int): Consume {
//
//        return Consume().apply {
//            i3 += am
//            totalConsumption = am
//            userName = nameIds[(Math.random() * (nameIds.size)).toInt()]
//            imageId = imageIds[(Math.random() * (imageIds.size)).toInt()]
//
//            val merch = merchIds[(Math.random() * (merchIds.size)).toInt()]
//            merchantName = merch.merchantName
//            merchantLogo = merch.merchantLogo
//        }
//    }
//
//    private fun getDataInt(am: Int): Consume {
//        return Consume().apply {
//            totalConsumption = am
//            userName = nameIds[(Math.random() * (nameIds.size)).toInt()]
//            imageId = imageIds[(Math.random() * (imageIds.size)).toInt()]
//        }
//    }
//
//    private fun getMerchData(am: Float): Consume {
//        return Consume().apply {
//            amount = am
//            userName = nameIds[(Math.random() * (nameIds.size)).toInt()]
//            imageId = imageIds[(Math.random() * (imageIds.size)).toInt()]
//            val merch = merchIds[(Math.random() * (merchIds.size)).toInt()]
//            merchantName = merch.merchantName
//            merchantLogo = merch.merchantLogo
//        }
//    }
//
//
//    private fun initTrueData() {
//        val countDownTimer2 = object : CountDownTimer(t, 6000) {
//            override fun onFinish() {
//
//            }
//
//            @SuppressLint("NotifyDataSetChanged")
//            override fun onTick(millisUntilFinished: Long) {
//                refreshData()
//            }
//        }
//
//        countDownTimer2.start()
//    }
//
//    @SuppressLint("SimpleDateFormat")
//    fun Long.toCNWeek(): String {
//        return "星期" + when (this.fixTime().getDateWeek()) {
//            0 -> "日"
//            1 -> "一"
//            2 -> "二"
//            3 -> "三"
//            4 -> "四"
//            5 -> "五"
//            6 -> "六"
//            else -> ""
//        }
//    }
//
//    /**
//     * 获取时间戳是星期几
//     * @return [Int] [Calendar.SUNDAY]
//     */
//    private fun Long.getDateWeek(): Int {
//        val calendar = Calendar.getInstance()
//        calendar.time = Date(this)
//        return calendar.get(Calendar.DAY_OF_WEEK) - 1
//    }
//
//    private fun Long.fixTime(): Long {
//        return (this.toString() + "0000000000000000").substring(0, 13).toLong()
//    }
//
//
//    var isADDed = false
//
//    @SuppressLint("SetTextI18n", "SimpleDateFormat")
//    fun refreshData() {
//
//        if (isDemo) {
//            isADDed = true
//            newNavData(
//                R.drawable.biaodan,
//                "新增用户",
//                i0,
//                "",
//                AutoIncrementUtil.INTTYPE
//            ).apply(::operation)
//
//            newNavData(
//                R.drawable.shoujinbi,
//                "充值金额",
//                i1,
//                unit,
//                AutoIncrementUtil.FLOATTYPE
//            ).apply(::operation)
//
//
//            newNavData(
//                R.drawable.mingxi,
//                "消费金额",
//                i2,
//                unit,
//                AutoIncrementUtil.FLOATTYPE
//            ).apply(::operation)
//
//
//            newNavData(
//                R.drawable.shangdian,
//                "消费笔数",
//                i3,
//                "",
//                AutoIncrementUtil.INTTYPE
//            ).apply(::operation)
//
//            val money = (88 + Math.random() * (100)).toInt().toFloat()
//            i2 = (i2 + money)
//            list[2].smallTitle.text = i2.toString()
//
//            val people = getMerchData(money)
//            consumptionsAdapter.addItem(people)
//
//            consumptionsList.smoothScrollToPosition(0)
//
//            listMerchCount.filter {
//                it.merchantName == people.merchantName
//            }.let {
//                if (it.isEmpty()) {
//                    people.totalConsumption = 1
//                    listMerchCount.add(people)
//                } else {
//                    it[0].totalConsumption = it[0].totalConsumption?.plus(1)
//                }
//            }
//            listMerchCount.sortBy { it.totalConsumption?.times(-1) }
//            merchantAdapter.notifyDataSetChanged()
//            i3 = i3 + 1
//            list[3].smallTitle.text = i3.toString()
//
//        } else {
//            val times =
//                System.currentTimeMillis() //long now = android.os.SystemClock.uptimeMillis();
//            val format = SimpleDateFormat("yyyy年MM月dd日")
//            val d1 = Date(times)
//            val t1: String = format.format(d1)
//            timeView.text = t1 + "     " + times.toCNWeek()
//
//            val c = Calendar.getInstance() //
//
//            val newDay = c.get(Calendar.DAY_OF_MONTH)
//
//            if (newDay != day) {
//                rechargeTime = null
//                consumptionTime = null
//                day = newDay
//            }
//
//            val rechargeTimeLong = rechargeTime?.toLong()
//            val consumptionTimeLong = consumptionTime?.toLong()
//
//
//            Log.e("start", times.toString())
//
//            com.tools.tvproject.net.getPodcastDailySummary(this@PageBack, rechargeTimeLong, consumptionTimeLong) { data ->
//
//                val newCount = data.newUsersNumber() ?: 0
//                if (newCount > lastPeopleCount) {  //新增用户播放声音
//                    playSound(R.raw.aigei_com)
//                    lastPeopleCount = newCount }
//
//                i0 = newCount
//                i1 = fixString(data.rechargeAmount()).setScale(0, BigDecimal.ROUND_HALF_UP).toInt()
//                i2 = fixString(data.consumptionAmount()).setScale(2, BigDecimal.ROUND_HALF_UP)
//                    .toFloat()
//                i3 = data.consumptionNumber() ?: 0
//
//
//                if (!isADDed) {
//                    isADDed = true
//                    newNavData(
//                        R.drawable.biaodan,
//                        "新增用户",
//                        i0,
//                        "",
//                        AutoIncrementUtil.INTTYPE
//                    ).apply(::operation)
//                    newNavData(
//                        R.drawable.shoujinbi,
//                        "充值金额",
//                        i1,
//                        unit,
//                        AutoIncrementUtil.FLOATTYPE
//                    ).apply(::operation)
//                    newNavData(
//                        R.drawable.mingxi,
//                        "消费金额",
//                        i2,
//                        unit,
//                        AutoIncrementUtil.FLOATTYPE
//                    ).apply(::operation)
//                    newNavData(
//                        R.drawable.shangdian,
//                        "消费笔数",
//                        i3,
//                        "",
//                        AutoIncrementUtil.INTTYPE
//                    ).apply(::operation)
//
//                    newNavData(
//                        R.drawable.shangdian,
//                        "店铺数",
//                        i3,
//                        "",
//                        AutoIncrementUtil.INTTYPE
//                    ).apply(::operation)
//                } else {
//
//                    list[0].smallTitle.text = i0.toString()
//                    list[1].smallTitle.text = i1.toString() + unit
//                    list[2].smallTitle.text = decimalFormat.format(i2) + unit
//                    list[3].smallTitle.text = i3.toString()
//                }
//
//                val listRechargeTemp = data.recharges()?.map {
//                    initByTrueDate(it.fragments().podcastDailyBean() )
//                }?.toMutableList() ?: mutableListOf()
//
//                val listConsumptionsTemp = data.consumptions()?.map {
//                    initByTrueDate(it.fragments().podcastDailyBean())
//                }?.toMutableList() ?: mutableListOf()
//
//                val listMerchantTemp = data.merchants()?.map {
//                    initByTrueDate(it.fragments().podcastDailyBean())
//                }?.toMutableList() ?: mutableListOf()
//
//                if (rechargeTime == null && consumptionTime == null) {
//
//                    rechargeAdapter = RechargeAdapter(listRechargeTemp)
//                    rechargeList.adapter = rechargeAdapter
//                    rechargeList.layoutManager =
//                        LinerManger(this@PageBack, LinearLayoutManager.VERTICAL, false)
//                    rechargeAdapter.notifyDataSetChanged()
//
//                    consumptionsAdapter = ConsumptionsAdapter(this@PageBack, listConsumptionsTemp)
//                    consumptionsList.adapter = consumptionsAdapter
//                    consumptionsList.layoutManager =
//                        LinerManger(this@PageBack, LinearLayoutManager.VERTICAL, false)
//                    consumptionsAdapter.notifyDataSetChanged()
//
//
//                    merchantAdapter = MerchantAdapter(listMerchantTemp)
//                    merchantList.adapter = merchantAdapter
//                    merchantList.layoutManager =
//                        LinerManger(this@PageBack, LinearLayoutManager.VERTICAL, false)
//                    merchantAdapter.notifyDataSetChanged()
//
//                } else {
//
//                    listRechargeTemp.forEach {
//                        rechargeAdapter.addItem(it)
//                        it.amount?.let { it1 -> Speak(it.userName, it1.toInt()) }
//                    }
//                    if (listRechargeTemp.size > 0) {
//                        rechargeList.postDelayed({ rechargeList.scrollToPosition(0) }, 500)
//
//                    }
//
//                    listConsumptionsTemp.forEach {
//                        consumptionsAdapter.addItem(it)
//                    }
//
//                    if (listConsumptionsTemp.size > 0) {
//                        playSound(R.raw.com)
//                        consumptionsList.postDelayed({ consumptionsList.scrollToPosition(0) }, 500)
//                    }
//
//                    if (listMerchantTemp.size > 0) {
//                        merchantAdapter = MerchantAdapter(listMerchantTemp)
//                        merchantList.adapter = merchantAdapter
//                        merchantAdapter.notifyDataSetChanged()
//                    }
//                }
//
//                listRechargeTemp.forEach {
//                    val newTime = BigDecimal(it.createTime.toString())
//                    if (rechargeTime == null || rechargeTime!! < newTime) {
//                        rechargeTime = newTime
//                        val format = SimpleDateFormat("yyyy/MM/dd HH:mm:ss")
//                        val d1 = Date(times.toLong())
//                        val t1: String = format.format(d1)
//                        Log.e("listPeopleTemp", it.userName + " 储值 " + t1)
//                    }
//                }
//
//
//                listConsumptionsTemp.forEach {
//                    val newTime = BigDecimal(it.createTime.toString())
//                    if (consumptionTime == null || consumptionTime!! < newTime) {
//                        consumptionTime = newTime
//                        val format = SimpleDateFormat("yyyy/MM/dd HH:mm:ss")
//                        val d1 = Date(times.toLong())
//                        val t1: String = format.format(d1)
//                        Log.e("listPeopleTemp", it.userName + " 消费 " + t1)
//                    }
//                }
//            }
//
////            com.tools.tvproject.net.getPodcastDailySummary(this@Page, rechargeTimeLong,consumptionTimeLong) { data ->
////
////                val newCount = data.newUsersNumber() ?: 0
////                if (newCount > lastPeopleCount) {
////                    playSound(R.raw.aigei_com)
////                    lastPeopleCount = newCount
////                }
////
////                list[0].smallTitle.text = data.newUsersNumber().toString()
////
////
////                list[2].smallTitle.text =
////                    fixString(data.consumptionAmount()).setScale(2, BigDecimal.ROUND_HALF_UP)
////                        .toString() + unit
////
////                list[3].smallTitle.text = data.consumptionNumber().toString()
////
////                val listMerchantTemp = data.consumptions()?.map {
////                    initByTrueDate(it.fragments().podcastDailyBean())
////                }?.toMutableList() ?: mutableListOf()
////
////                val listMerchCountTemp = data.merchants()?.map {
////                    initByTrueDate(it.fragments().podcastDailyBean())
////                }?.toMutableList() ?: mutableListOf()
////
////                if (consumptionTime == null) {
////
////                    list[1].smallTitle.text =
////                        fixString(data.rechargeAmount()).setScale(0, BigDecimal.ROUND_HALF_UP)
////                            .toString() + unit
////                    merchRechargAdapter = MerchCustomAdapter(this@Page, listMerchantTemp)
////                    merchCountAdapter = MerchCountAdapter(listMerchCountTemp)
////
////                    merchRechargeList.adapter = merchRechargAdapter
////                    merchRechargeList.layoutManager =
////                        LinerManger(this@Page, LinearLayoutManager.VERTICAL, false)
////
////                    recordList.adapter = merchCountAdapter
////                    recordList.layoutManager =
////                        LinerManger(this@Page, LinearLayoutManager.VERTICAL, false)
////
////                    merchRechargAdapter.notifyDataSetChanged()
////                    merchCountAdapter.notifyDataSetChanged()
////
////                } else {
////                    listMerchantTemp.forEach {
////                        merchRechargAdapter.addItem(it)
////                    }
////
////                    if (listMerchantTemp.size > 0) {
////                        playSound(R.raw.com)
////                    }
////
////                    merchRechargeList.postDelayed({ merchRechargeList.scrollToPosition(0) }, 500)
////
////                    if (listMerchCountTemp.size > 0) {
////                        merchCountAdapter = MerchCountAdapter(listMerchCountTemp)
////                        recordList.adapter = merchCountAdapter
////                    }
////                }
////
////
////                listMerchantTemp.forEach {
////                    val newTime = BigDecimal(it.createTime.toString())
////                    if (consumptionTime == null || consumptionTime!! < newTime) {
////                        consumptionTime = newTime
////                        val format = SimpleDateFormat("yyyy/MM/dd HH:mm:ss")
////                        val d1 = Date(times.toLong())
////                        val t1: String = format.format(d1)
////                        Log.e("listPeopleTemp", it.userName + " 消费 " + t1)
////                    }
////                }
////            }
//        }
//    }
//
//
//    private fun initDemoData() {
//        listPeople.add(getDataInt(300))
//        listPeople.add(getDataInt(100))
//        listPeople.add(getDataInt(500))
//
//        listMerchant.add(getMerchData(213f))
//        listMerchant.add(getMerchData(203f))
//        listMerchant.add(getMerchData(560f))
//
//        listMerchCount.add(getMerchCountData(3))
//        listMerchCount.add(getMerchCountData(4))
//        listMerchCount.add(getMerchCountData(5))
//
//        rechargeAdapter = RechargeAdapter(listPeople)
//        consumptionsAdapter = ConsumptionsAdapter(this@PageBack, listMerchant)
//        merchantAdapter = MerchantAdapter(listMerchCount)
//
//        rechargeList.adapter = rechargeAdapter
//        rechargeList.layoutManager =
//            LinerManger(this@PageBack, LinearLayoutManager.VERTICAL, false)
//
//        consumptionsList.adapter = consumptionsAdapter
//        consumptionsList.layoutManager =
//            LinerManger(this@PageBack, LinearLayoutManager.VERTICAL, false)
//
//
//        merchantList.adapter = merchantAdapter
//        merchantList.layoutManager =
//            LinerManger(this@PageBack, LinearLayoutManager.VERTICAL, false)
//
//        rechargeAdapter.notifyDataSetChanged()
//        consumptionsAdapter.notifyDataSetChanged()
//        merchantAdapter.notifyDataSetChanged()
//
//        i0 = 40
//        i1 = 8800
//        i2 = 8002f
//        i3 = 40
//
//        val countDownTimer = object : CountDownTimer(t, 3000) {
//            override fun onFinish() {
//            }
//
//            override fun onTick(millisUntilFinished: Long) {
//                val i00 = Math.random() * (3)
//                i0 = (i0 + i00).toInt()
//                list[0].smallTitle.text = i0.toString()
//            }
//        }
//
//        countDownTimer.start()
//
//        val countDownTimer2 = object : CountDownTimer(t, 6000) {
//            override fun onFinish() {
//
//            }
//
//            override fun onTick(millisUntilFinished: Long) {
//                val money = (88 + Math.random() * (100)).toInt().toFloat()
//                i2 = (i2 + money)
//                list[2].smallTitle.text = i2.toString()
//                val people = getMerchData(money)
//                consumptionsAdapter.addItem(people)
//                consumptionsList.smoothScrollToPosition(0)
//                listMerchCount.filter {
//                    it.merchantName == people.merchantName
//                }.let {
//                    if (it.isEmpty()) {
//                        people.totalConsumption = 1
//                        listMerchCount.add(people)
//                    } else {
//                        it[0].totalConsumption = it[0].totalConsumption?.plus(1)
//                    }
//                }
//                listMerchCount.sortBy { it.totalConsumption?.times(-1) }
//                merchantAdapter.notifyDataSetChanged()
//                i3 = i3 + 1
//                list[3].smallTitle.text = i3.toString()
//            }
//        }
//
//        countDownTimer2.start()
//
//        val countDownTimer3 = object : CountDownTimer(t, 8000) {
//            override fun onFinish() {
//
//            }
//
//            override fun onTick(millisUntilFinished: Long) {
//                val count = Math.random()
//                Log.e("count", "$count ")
//                val i01: Int = 100 * (1 + count * (10)).toInt()
//                i1 = (i1 + i01)
//                list[1].smallTitle.text = i1.toString()
//                val people = getDataInt(i01)
//                rechargeAdapter.addItem(people)
//                people.totalConsumption?.let { Speak(people.userName ?: "", it) }
//                rechargeList.smoothScrollToPosition(0)
//            }
//        }
//        countDownTimer3.start()
//    }
//
//    var rechargeTime: BigDecimal? = null
//
//    var consumptionTime: BigDecimal? = null
//
//    fun operation(layout: NavLayout) {
//        todayPanel.addView(layout)
//        list.add(layout)
//    }
//
//    private fun newNavData(
//        imageId: Int,
//        str: String,
//        value: Int,
//        unit: String,
//        type: String
//    ): NavLayout {
//        return newSimpleNav(imageId, str).apply {
//            setValueWithUnit(value, unit)
//        }
//    }
//
//    private fun newNavData(
//        imageId: Int,
//        str: String,
//        value: Float,
//        unit: String,
//        type: String
//    ): NavLayout {
//        return newSimpleNav(imageId, str).apply {
//            setValueWithUnit(value, unit)
//        }
//    }
//
//    private fun newSimpleNav(imageId: Int, str: String): NavLayout {
//        return NavLayout(this).apply {
//            imageIV.setImageResource(imageId)
//            bigTitle.text = str
//        }
//    }
//
//    override fun showLoading() {
//
//    }
//
//    override fun hideLoading() {
//
//    }
//
//    override fun onError(errorCode: String, error: String) {
//        Log.e(errorCode, error)
//    }
//
//}