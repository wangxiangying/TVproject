package base.ui.recyclerview

import android.content.Context
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

/**
 * Created by iamwxy on 2020/6/25 17:00
 * description:
 */
abstract class BaseHeaderFooterRecyclerViewAdapter<T, VH : RecyclerView.ViewHolder>(var context: Context) : RecyclerView.Adapter<VH>() {

    val TYPE_HEADER = 0  //说明是带有Header的
    val TYPE_FOOTER = 1  //说明是带有Footer的
    val TYPE_NORMAL = 2  //说明是不带有header和footer的
    //获取从Activity中传递过来每个item的数据集合
    //HeaderView, FooterView
    private var mHeaderView: VH? = null
    private var mFooterView: VH? = null


    //HeaderView和FooterView的get和set函数
    fun getHeaderView(): VH? {
        return mHeaderView
    }

    fun setHeaderView(headerView: VH) {
        mHeaderView = headerView
        notifyItemInserted(0)
    }

    fun getFooterView(): VH? {
        return mFooterView
    }

    fun setFooterView(footerView: VH) {
        mFooterView = footerView
        notifyItemInserted(itemCount - 1)
    }


    var itemClickListener: OnItemClickListener? = null

    open var dataList: MutableList<T> = mutableListOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }

    //返回View中Item的个数，这个时候，总的个数应该是ListView中Item的个数加上HeaderView和FooterView
    override fun getItemCount(): Int {
        return if (mHeaderView == null && mFooterView == null) {
            dataList.size
        } else if (mHeaderView == null && mFooterView != null) {
            dataList.size + 1
        } else if (mHeaderView != null && mFooterView == null) {
            dataList.size + 1
        } else {
            dataList.size + 2
        }
    }

    override fun getItemViewType(position: Int): Int {
        if (mHeaderView == null && mFooterView == null) {
            return TYPE_NORMAL
        }
        if (position == 0) {
            //第一个item应该加载Header
            return TYPE_HEADER
        }
        return if (position == itemCount - 1) {
            //最后一个,应该加载Footer
            TYPE_FOOTER
        } else TYPE_NORMAL
    }

    //创建View，如果是HeaderView或者是FooterView，直接在Holder中返回
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        if (mHeaderView != null && viewType == TYPE_HEADER) {
            return mHeaderView as VH
        }
        if (mFooterView != null && viewType == TYPE_FOOTER) {
            return mFooterView as VH
        }
        return onCreateChileViewHolder(parent, viewType)
    }

    abstract fun onCreateChileViewHolder(parent: ViewGroup, viewType: Int): VH


    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.itemView.setOnClickListener {
            itemClickListener?.onItemClick(position)
        }
        when {
            getItemViewType(position) == TYPE_NORMAL -> {
                onBindChildViewHolder(holder, position-1)
                return
            }
            getItemViewType(position) == TYPE_HEADER -> return
            else -> return
        }
    }

    abstract fun onBindChildViewHolder(holder: VH, position: Int)

}
