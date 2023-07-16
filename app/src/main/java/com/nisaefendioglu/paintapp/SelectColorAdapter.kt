package com.nisaefendioglu.paintapp

import com.nisaefendioglu.paintapp.PaintView.Companion.currentBrush
import android.content.Context
import android.graphics.Path
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.nisaefendioglu.paintapp.MainActivity.Companion.paintBrush
import com.nisaefendioglu.paintapp.MainActivity.Companion.path

class SelectColorAdapter(private val list: ArrayList<Int>, private val context: Context) :
    RecyclerView.Adapter<SelectColorAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val selectColorImage: ImageView = itemView.findViewById(R.id.select_color_image)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.select_color_layout, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = list[position]
        holder.selectColorImage.setImageResource(currentItem)
        holder.selectColorImage.setOnClickListener {
            paintBrush.color = context.getColor(currentItem)
            currentColor(paintBrush.color)
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    private fun currentColor(color: Int) {
        currentBrush = color
        path = Path()
    }
}