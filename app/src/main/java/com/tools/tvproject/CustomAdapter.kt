package com.tools.tvproject

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.lifecycle.ViewModel




class CustomAdapter(private val dataSet: MutableList<Consume>) :
    RecyclerView.Adapter<CustomAdapter.ViewHolder>() {

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder).
     */
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val bigText: TextView

        val smallText: TextView

        val iconImage:ImageView

        init {
            // Define click listener for the ViewHolder's View.
            bigText = view.findViewById(R.id.bigTitle)
            smallText = view.findViewById(R.id.smallTitle)
            iconImage = view.findViewById(R.id.imageIV)
        }
    }

    fun addItem( viewModel: Consume?) {
        viewModel?.let { dataSet.add( it) }

//        notifyItemInserted()
//        notifyDataSetChanged()
//        notifyDataSetChanged()
    }




    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.user_info, viewGroup, false)
        return ViewHolder(view)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.bigText.text = dataSet[position].userName
        viewHolder.smallText.text =dataSet[position].rechage.toString()+"元"
        dataSet[position].imageId?.let { viewHolder.iconImage.setImageResource(it) }
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = dataSet.size

}