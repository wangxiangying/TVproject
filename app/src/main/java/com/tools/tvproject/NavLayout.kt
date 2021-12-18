package com.tools.tvproject

import android.animation.ValueAnimator
import android.animation.ValueAnimator.AnimatorUpdateListener
import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.view.animation.AccelerateInterpolator
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView


class NavLayout : LinearLayout {

    lateinit var imageIV: ImageView
    lateinit var bigTitle: TextView
    lateinit var smallTitle: TextView




    var valueFloatLast: Float? = null
    var unitValue: String? = null

    constructor(context: Context) : super(context) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        init()
    }

    private fun init() {
        LayoutInflater.from(context).inflate(R.layout.label_info, this)
        imageIV = findViewById(R.id.imageIV)
        bigTitle = findViewById(R.id.bigTitle)
        smallTitle = findViewById(R.id.smallTitle)
    }


    fun setValueWithUnit(value: Int, unit: String) {
        unitValue = unit
        trueSetData(value.toFloat(), AutoIncrementUtil.INTTYPE)
    }

    fun setValueWithUnit(value: Float, unit: String) {
        unitValue = unit
        trueSetData(value, AutoIncrementUtil. FLOATTYPE)
    }

    fun trueSetData(value: Float, type: String) {
        if (valueFloatLast == null) {
            startAnimation(
                type, smallTitle, 0f,
                value, false, unitValue!!, 3000
            );
        } else {
            startAnimation(
                type, smallTitle, valueFloatLast!!,
                value, false, unitValue!!, (valueFloatLast!!.toInt() - value).toInt() * 100
            );
        }
        valueFloatLast = value
    }

    val FLOATTYPE = "FloatType"
    val INTTYPE = "IntType"


    fun startAnimation(
        type: String,
        tvView: TextView,
        startFloat: Float,
        floatValue: Float,
        isRoundUp: Boolean,
        danwei: String,
        duration: Int
    ) {
        var animator: ValueAnimator? = null
        if (type == FLOATTYPE) {
            animator = ValueAnimator.ofFloat(startFloat, floatValue)
            animator.addUpdateListener(AnimatorUpdateListener { valueAnimator ->
                val curValue = valueAnimator.animatedValue as Float
                tvView.text = NumUtil.FormatFloat(curValue) + danwei
                Log.e("curValue1",curValue.toString()  )
            })
        } else if (type == INTTYPE) {
            val targetValueString = NumUtil.FormatRoundUp(isRoundUp, floatValue)
            animator = ValueAnimator.ofInt(startFloat.toInt(), targetValueString.toInt())
            animator.addUpdateListener(AnimatorUpdateListener { valueAnimator ->
                val curValue = valueAnimator.animatedValue as Int
                tvView.text = curValue.toString() + danwei
                Log.e("curValue2",curValue.toString()  )

            })
        }
        animator!!.duration = duration.toLong()
        animator.interpolator = AccelerateInterpolator()
        animator.start()
    }

}