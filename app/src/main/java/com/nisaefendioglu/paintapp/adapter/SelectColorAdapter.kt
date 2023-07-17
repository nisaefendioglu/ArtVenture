package com.nisaefendioglu.paintapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.nisaefendioglu.paintapp.R
import com.nisaefendioglu.paintapp.data.ColorItem

class SelectColorAdapter(
    private val context: Context,
    private val onItemClick: (ColorItem) -> Unit
) : RecyclerView.Adapter<SelectColorAdapter.ViewHolder>() {

    private val colorList = mutableListOf<ColorItem>()

    fun setData(list: List<ColorItem>) {
        colorList.clear()
        colorList.addAll(list)
        notifyDataSetChanged()
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val selectColorImage: ImageView = itemView.findViewById(R.id.select_color_image)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(context).inflate(R.layout.select_color_layout, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = colorList[position]
        holder.selectColorImage.setImageResource(currentItem.colorResId)
        holder.selectColorImage.setOnClickListener {
            onItemClick(currentItem)
        }
    }

    override fun getItemCount(): Int {
        return colorList.size
    }
}
