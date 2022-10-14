package com.tools.tvproject

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
//import com.bumptech.glide.Glide
import com.squareup.picasso.Picasso
import java.math.BigDecimal
import java.text.SimpleDateFormat
import java.util.*


class ConsumptionsAdapter(val context: Context, private val dataSet: MutableList<Consume>) :
    RecyclerView.Adapter<ConsumptionsAdapter.ViewHolder>() {

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder).
     */
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val bigText: TextView

        val smallText: TextView

        val iconImage: ImageView

        val userIV: ImageView

        val userName: TextView

        val timeTextView: TextView

        init {
            // Define click listener for the ViewHolder's View.
            bigText = view.findViewById(R.id.bigTitle)
            smallText = view.findViewById(R.id.smallTitle)
            iconImage = view.findViewById(R.id.imageIV)
            userIV = view.findViewById(R.id.userIV)
            userName = view.findViewById(R.id.userName)
            timeTextView = view.findViewById(R.id.timeTextView)
        }

    }

    fun addItem(viewModel: Consume?) {
        viewModel?.let {
            dataSet.add(0, it)
            notifyItemInserted(0)
        }
    }


    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.merch_info, viewGroup, false)
        return ViewHolder(view)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

        dataSet[position].apply {
            viewHolder.bigText.text = merchantName
            viewHolder.smallText.text = amount.toString() + "元"
            val newTime = BigDecimal(createTime.toString())
            val format = SimpleDateFormat("HH:mm:ss")
            val d1 = Date(newTime.toLong())
            val t1: String = format.format(d1)
            viewHolder.timeTextView.text = t1
            viewHolder.timeTextView.text = t1

            if (userAvatar != null) {
                Picasso.get()
                    .load(userAvatar)
                    .resize(45, 45)
                    .centerCrop()
                    .into(viewHolder.userIV)

                viewHolder.userName.text = userName?.trim().toString()


                Picasso.get()
                    .load(merchantLogo)
                    .resize(45, 45)
                    .centerCrop()
                    .into(viewHolder.iconImage)
            }

        }

    }

    override fun getItemCount() = dataSet.size

}