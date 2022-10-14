package com.tools.tvproject

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

//import com.bumptech.glide.Glide


class MerchantAdapter(private val dataSet: MutableList<Consume>) :
    RecyclerView.Adapter<MerchantAdapter.ViewHolder>() {

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder).
     */
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val bigText: TextView = view.findViewById(R.id.bigTitle)

        val smallText: TextView = view.findViewById(R.id.smallTitle)

        val iconImage: ImageView = view.findViewById(R.id.imageIV)
    }

    fun addItem(viewModel: Consume?) {
        viewModel?.let {
            dataSet.add(0, it)
            if (getSize() > 10) {
                dataSet.removeAt(getSize() - 1)
            }
        }
        notifyItemInserted(0)
    }

    private fun getSize(): Int {
        return dataSet.size
    }


    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.merch_count_info, viewGroup, false)
        return ViewHolder(view)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.bigText.text = dataSet[position].merchantName
        viewHolder.smallText.text = dataSet[position].totalConsumption.toString()

//        Glide
//            .with(viewHolder.iconImage)
//            .load(dataSet[position].merchantLogo)
//            .centerCrop()
//            .placeholder(R.drawable.lou)
//            .into(viewHolder.iconImage)

        Picasso.get()
            .load(dataSet[position].merchantLogo)
            .resize(45, 45)
            .centerCrop().placeholder(R.drawable.lou)
            .into(viewHolder.iconImage)
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = dataSet.size

}