package com.nisaefendioglu.paintapp

import com.nisaefendioglu.paintapp.PaintView.Companion.colorList
import com.nisaefendioglu.paintapp.PaintView.Companion.pathList
import android.graphics.Paint
import android.graphics.Path
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomsheet.BottomSheetDialog

class MainActivity : AppCompatActivity() {

    private lateinit var colorRecyclerView: RecyclerView
    private lateinit var erasePaint: CardView
    private lateinit var editFont: CardView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        colorRecyclerView = findViewById(R.id.color_recyclerView)
        erasePaint = findViewById(R.id.erase_paint)
        editFont = findViewById(R.id.edit_font)

        val colorArray = arrayListOf(
            R.color.white, R.color.card_pink, R.color.card_yellow, R.color.card_red,
            R.color.card_violet, R.color.card_blue, R.color.card_green, R.color.peach,
            R.color.green, R.color.purple, R.color.darkPurple, R.color.orangeRed,
            R.color.yellowOrange, R.color.MangoTango, R.color.maizeCrayola
        )

        val adapter = SelectColorAdapter(colorArray, this)

        val layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        colorRecyclerView.layoutManager = layoutManager
        colorRecyclerView.adapter = adapter

        erasePaint.setOnClickListener {
            pathList.clear()
            colorList.clear()
            path.reset()
        }

        editFont.setOnClickListener {
            showPaintStrokeDialog()
        }
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

    companion object {
        var path = Path()
        var paintBrush = Paint()
    }
}
