package com.tools.tvproject

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.squareup.picasso.Picasso


class MarkView : LinearLayout {

    private lateinit var page_image: ImageView
    private lateinit var countTV: TextView
    private lateinit var nameTV: TextView

    constructor(context: Context) : super(context) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        init()
    }

    private fun init() {
        LayoutInflater.from(context).inflate(R.layout.icon, this)
        page_image = findViewById(R.id.page_image)
        countTV = findViewById(R.id.count)
        nameTV = findViewById(R.id.nameTV)
    }

    fun setImage(icon: String) {
        Picasso.get()
            .load(icon)
            .resize(45, 45)
            .centerCrop()
            .into(page_image)
    }

    fun setName(source: String) {
        var name = source
        if (name.isNotEmpty()) {
            name = name.replace("（", "(")
            name = name.replace("）", ")")
            val s = name.indexOf("(")
            val e = name.indexOf(")")
            if (s >= 0 && e >= 0) {
                name = name.subSequence(s + 1, e).toString()
                if (name.endsWith("店")) {
                    name = name.dropLast(1)
                }
                nameTV.text = name
            } else {
                if (name.count() > 4) {
                    nameTV.text = name.subSequence(0, 4)
                } else {
                    nameTV.text = name
                }
            }
        }
    }

    fun setCount(count: Int) {
        countTV.text = count.toString()
    }


    val FLOATTYPE = "FloatType"
    val INTTYPE = "IntType"

//
//    fun startAnimation(
//        type: String,
//        tvView: TextView,
//        startFloat: Float,
//        floatValue: Float,
//        isRoundUp: Boolean,
//        danwei: String,
//        duration: Int
//    ) {
//        var animator: ValueAnimator? = null
//        if (type == FLOATTYPE) {
//            animator = ValueAnimator.ofFloat(startFloat, floatValue)
//            animator.addUpdateListener(AnimatorUpdateListener { valueAnimator ->
//                val curValue = valueAnimator.animatedValue as Float
//                tvView.text = NumUtil.FormatFloat(curValue) + danwei
//            })
//        } else if (type == INTTYPE) {
//            val targetValueString = NumUtil.FormatRoundUp(isRoundUp, floatValue)
//            animator = ValueAnimator.ofInt(startFloat.toInt(), targetValueString.toInt())
//            animator.addUpdateListener(AnimatorUpdateListener { valueAnimator ->
//                val curValue = valueAnimator.animatedValue as Int
//                tvView.text = curValue.toString() + danwei
//            })
//        }
//        animator!!.duration = duration.toLong()
//        animator.interpolator = AccelerateInterpolator()
//        animator.start()
//    }

}