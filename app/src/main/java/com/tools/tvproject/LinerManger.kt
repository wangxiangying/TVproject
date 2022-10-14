package com.tools.tvproject

import android.content.Context
import android.util.AttributeSet
import androidx.recyclerview.widget.RecyclerView

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.Recycler
import java.lang.IndexOutOfBoundsException

class LinerManger : LinearLayoutManager {
    constructor(context: Context?) : super(context) {}
    constructor(context: Context?, orientation: Int, reverseLayout: Boolean) : super(
        context,
        orientation,
        reverseLayout
    ) {
    }

    constructor(
        context: Context?,
        attrs: AttributeSet?,
        defStyleAttr: Int,
        defStyleRes: Int
    ) : super(context, attrs, defStyleAttr, defStyleRes) {
    }

//    override fun onLayoutChildren(recycler: Recycler, state: RecyclerView.State) {
//        //这里加个异常处理 ，经测试可以避免闪退，具体副作用暂时还没有发现，可以作为临时解决方案
//        try {
//            super.onLayoutChildren(recycler, state)
//        } catch (e: IndexOutOfBoundsException) {
//            e.printStackTrace()
//        }
//    }
}