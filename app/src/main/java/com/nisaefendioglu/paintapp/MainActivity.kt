package com.nisaefendioglu.paintapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.nisaefendioglu.paintapp.adapter.SelectColorAdapter
import com.nisaefendioglu.paintapp.data.ColorItem
import com.nisaefendioglu.paintapp.view.PaintView
import com.nisaefendioglu.paintapp.viewmodel.PaintViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var colorRecyclerView: RecyclerView
    private lateinit var erasePaint: CardView
    private lateinit var editFont: CardView
    private lateinit var viewModel: PaintViewModel
    private lateinit var paintView: PaintView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        paintView = findViewById(R.id.paintView)
        colorRecyclerView = findViewById(R.id.color_recyclerView)
        erasePaint = findViewById(R.id.erase_paint)
        editFont = findViewById(R.id.edit_font)

        viewModel = ViewModelProvider(this).get(PaintViewModel::class.java)

        val adapter = SelectColorAdapter(this) { colorItem -> onColorItemSelected(colorItem) }

        val layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        colorRecyclerView.layoutManager = layoutManager
        colorRecyclerView.adapter = adapter

        erasePaint.setOnClickListener {
            paintView.clearPaths()
        }

        editFont.setOnClickListener {
            showPaintStrokeDialog()
        }

        viewModel.colorList.observe(this, { colorItems ->
            adapter.setData(colorItems)
        })
    }

    private fun onColorItemSelected(colorItem: ColorItem) {
        paintView.setPaintColor(getColor(colorItem.colorResId))
    }

    private fun showPaintStrokeDialog() {
        val dialog = BottomSheetDialog(this, R.style.BottomSheetDialogTheme)
        dialog.setContentView(R.layout.paint_stroke_layout)

        val first: CardView? = dialog.findViewById(R.id.first)
        val second: CardView? = dialog.findViewById(R.id.second)
        val third: CardView? = dialog.findViewById(R.id.third)
        val fourth: CardView? = dialog.findViewById(R.id.fourth)
        val fifth: CardView? = dialog.findViewById(R.id.fifth)

        first?.setOnClickListener {
            dialog.dismiss()
            setPaintStroke(5f)
        }
        second?.setOnClickListener {
            dialog.dismiss()
            setPaintStroke(10f)
        }
        third?.setOnClickListener {
            dialog.dismiss()
            setPaintStroke(15f)
        }
        fourth?.setOnClickListener {
            dialog.dismiss()
            setPaintStroke(20f)
        }
        fifth?.setOnClickListener {
            dialog.dismiss()
            setPaintStroke(25f)
        }

        dialog.show()
    }

    private fun setPaintStroke(strokeWidth: Float) {
        PaintView.paintStroke = strokeWidth
    }
}
