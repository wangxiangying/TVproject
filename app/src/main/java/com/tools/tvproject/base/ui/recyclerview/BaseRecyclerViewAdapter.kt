package base.ui.recyclerview

import android.content.Context
import androidx.recyclerview.widget.RecyclerView

/**
 * Created by iamwxy on 2020/6/25 17:00
 * description:
 */
abstract class BaseRecyclerViewAdapter<T, VH : RecyclerView.ViewHolder>(var context: Context) :
    RecyclerView.Adapter<VH>() {
    var itemClickListener: OnItemClickListener? = null
    open var dataList: MutableList<T> = mutableListOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.itemView.setOnClickListener {
            itemClickListener?.onItemClick(position)
        }
    }

    override fun getItemCount() = dataList.size
    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }

}
