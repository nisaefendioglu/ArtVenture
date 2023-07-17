package com.nisaefendioglu.paintapp

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.nisaefendioglu.paintapp.adapter.SelectColorAdapter
import com.nisaefendioglu.paintapp.data.ColorItem
import com.nisaefendioglu.paintapp.databinding.ActivityMainBinding
import com.nisaefendioglu.paintapp.view.PaintView
import com.nisaefendioglu.paintapp.viewmodel.PaintViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: PaintViewModel by viewModels()
    private lateinit var paintView: PaintView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        paintView = binding.paintViewContainer.findViewById(R.id.paintView)

        val colorAdapter = SelectColorAdapter(this) { colorItem -> onColorItemSelected(colorItem) }

        val layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        binding.colorRecyclerView.layoutManager = layoutManager
        binding.colorRecyclerView.adapter = colorAdapter

        binding.editFontButton.setOnClickListener {
            showPaintStrokeDialog()
        }

        binding.erasePaintButton.setOnClickListener {
            paintView.clearPaths()
        }

        binding.eraseButton.setOnClickListener {
            paintView.setPaintColor(Color.WHITE)
        }

        viewModel.colorList.observe(this) { colorItems ->
            colorAdapter.setData(colorItems)
        }
    }

    private fun onColorItemSelected(colorItem: ColorItem) {
        paintView.setPaintColor(getColor(colorItem.colorResId))
    }


    private fun showPaintStrokeDialog() {
        val dialog = BottomSheetDialog(this, R.style.BottomSheetDialogTheme)
        dialog.setContentView(R.layout.option_item_layout)

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
