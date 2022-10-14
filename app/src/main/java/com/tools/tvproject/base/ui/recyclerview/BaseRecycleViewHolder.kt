package base.ui.recyclerview

import android.util.SparseArray
import android.view.View
import androidx.recyclerview.widget.RecyclerView

/**
 * Created by iamwxy on 2020/6/25 18:00
 * description:
 */
open class BaseRecycleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    fun <T : View> findView(viewId: Int): T? {
        return itemView.findViewOften(viewId)
    }

    @SuppressWarnings("unchecked")
    fun <T : View> View.findViewOften(viewId: Int): T? {
        val viewHolder: SparseArray<View> = tag as? SparseArray<View> ?: SparseArray()
        tag = viewHolder
        var childView: View? = viewHolder.get(viewId)
        if (null == childView) {
            childView = findViewById(viewId)
            childView?.let {
                viewHolder.put(viewId, childView)
            }
        }
        return childView as? T
    }
}

//在布局中，RecyclerView外嵌套一个swipeRefreshLayout，即可实现下拉刷新上拉加载更多
