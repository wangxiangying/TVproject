package com.tools.tvproject

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
//import com.bumptech.glide.Glide
import java.math.BigDecimal
import java.text.SimpleDateFormat
import java.util.*


class RechargeAdapter(private val dataSet: MutableList<Consume>) :
    RecyclerView.Adapter<RechargeAdapter.ViewHolder>() {

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder).
     */
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val bigText: TextView = view.findViewById(R.id.bigTitle)

        val smallText: TextView = view.findViewById(R.id.smallTitle)

        val iconImage: ImageView = view.findViewById(R.id.imageIV)

        val timeTextView: TextView = view.findViewById(R.id.timeTextView)

    }

    fun addItem(viewModel: Consume?) {

        viewModel?.let {
            dataSet.add(0, it)
            if (getSize() > 20) {
                dataSet.removeAt(getSize() - 1)
            }
        }
        notifyItemInserted(0)
    }

    fun getSize(): Int {
        return dataSet.size
    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.user_info, viewGroup, false)
        return ViewHolder(view)
    }

    // Replace the contents of a view (invoked by the layout manager)
    @SuppressLint("SimpleDateFormat", "SetTextI18n")
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        dataSet[position].run {
            viewHolder.bigText.text = userName?.trim()
            val newTime = BigDecimal(createTime.toString())
            val format = SimpleDateFormat("HH:mm:ss")
            val d1 = Date(newTime.toLong())
            val t1: String = format.format(d1)

            viewHolder.timeTextView.text = t1
            viewHolder.smallText.text = amount.toString() + "元"

            if(userAvatar!=null  && userAvatar?.length!! >0 )
            {
                Picasso.get()
                    .load(userAvatar)
                    .resize(45, 45)
                    .centerCrop().placeholder(R.drawable.want_empty)
                    .into(viewHolder.iconImage)
            }

        }

    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = dataSet.size

}