package com.tools.tvproject

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class Page : AppCompatActivity() {

    lateinit var todayPanel: LinearLayout

    lateinit var marchList: RecyclerView

    lateinit var rechargeList: RecyclerView


    val list: MutableList<NavLayout> = mutableListOf()

    val listPeople: MutableList<Consume> = mutableListOf()

    val imageIds = arrayOf(
        R.drawable.tx1,
        R.drawable.tx2,
        R.drawable.tx3,
        R.drawable.tx4,
        R.drawable.tx5,
        R.drawable.tx6,
        R.drawable.tx7,
        R.drawable.tx8,
        R.drawable.tx9,
        R.drawable.tx10,
        R.drawable.tx11,
        R.drawable.tx12,
        R.drawable.tx13
    )

    val nameIds = arrayOf(
        "小虾米",
        "离我🎁",
        "开心就好",
        "知足常乐",
        "大漠孤烟",
        "听雨",
        "大眼镜",
        "莉莉娅",
        "吴彦祖",
        "刘德华",
        "兔兔",
        "紫菜卷",
        "可乐可口"
    )


    @SuppressLint("ClickableViewAccessibility")
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
        initView()
    }

    private fun initView() {
        todayPanel = findViewById(R.id.todayPanel)
        marchList = findViewById(R.id.marchList)
        rechargeList = findViewById(R.id.rechargeList)
        initData()
    }


    var i0: Int = 301
    var i1: Int = 8800
    var i2: Float = 20211.18f
    var i3: Int = 126
    var i4: Float = 312.32f

    private fun getData(am: Float): Consume {

        return Consume().apply {
            amount = am
            userName = nameIds[(Math.random() * (nameIds.size)).toInt()]
            imageId = imageIds[(Math.random() * (imageIds.size)).toInt()]
        }
    }


    private fun getDataInt(am: Int): Consume {

        return Consume().apply {
            rechage = am
            userName = nameIds[(Math.random() * (nameIds.size)).toInt()]
            imageId = imageIds[(Math.random() * (imageIds.size)).toInt()]
        }
    }


    private fun initData() {

        listPeople.add(getDataInt(300))
        listPeople.add(getDataInt(100))
        listPeople.add(getDataInt(500))

        val adapter=CustomAdapter(listPeople)
        marchList.adapter=adapter
        marchList.layoutManager=LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)

        adapter.notifyDataSetChanged()

        newNavData(
            R.drawable.biaodan,
            "新增用户",
            i0,
            "",
            AutoIncrementUtil.INTTYPE
        ).apply(::operation)


        i1 = 8800
        newNavData(
            R.drawable.shoujinbi,
            "充值金额",
            i1,
            "元",
            AutoIncrementUtil.FLOATTYPE
        ).apply(::operation)

        i2 = 8002f

        newNavData(
            R.drawable.mingxi,
            "消费金额",
            i2,
            "元",
            AutoIncrementUtil.FLOATTYPE
        ).apply(::operation)


        i3 = 126
        newNavData(
            R.drawable.shangdian,
            "消费笔数",
            i3,
            "",
            AutoIncrementUtil.INTTYPE
        ).apply(::operation)

        i4 = 312.32f
        newNavData(
            R.drawable.jinbiwangfan,
            "返利金额",
            i4,
            "元",
            AutoIncrementUtil.FLOATTYPE
        ).apply(::operation)


        var t: Long = 8 * 60 * 60 * 1000 //定义总时长 2小时
        var countDownTimer = object : CountDownTimer(t, 3000) {
            override fun onFinish() {

            }

            override fun onTick(millisUntilFinished: Long) {

                var i00=Math.random() * (3)
                i0 = (i0 + i00).toInt()
                Log.e("countDownTimer", i0.toString())
                list[0].smallTitle.text = i0.toString()

            }
        }

        countDownTimer.start()
//
        var countDownTimer2 = object : CountDownTimer(t, 5000) {
            override fun onFinish() {

            }

            override fun onTick(millisUntilFinished: Long) {
                i2 = (i2 + Math.random() * (4)).toInt().toFloat()
                list[2].smallTitle.text = i2.toString()

                i3 = i3 + 1
                list[3].smallTitle.text = i3.toString()

            }
        }

        countDownTimer2.start()

        var countDownTimer3 = object : CountDownTimer(t, 3300) {
            override fun onFinish() {

            }

            override fun onTick(millisUntilFinished: Long) {
                var i01:Int= 100*(Math.random() * (5)).toInt()
                i1 = (i1 + i01)
                list[1].smallTitle.text = i1.toString()
                var people=getDataInt(i01)
                listPeople.add(people)
                adapter.addItem( people)

            }
        }

        countDownTimer3.start()

    }


    fun operation(layout: NavLayout) {
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
}