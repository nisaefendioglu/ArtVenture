package com.nisaefendioglu.paintapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.nisaefendioglu.paintapp.R
import com.nisaefendioglu.paintapp.data.ColorItem

class PaintViewModel : ViewModel() {
    private val _colorList = MutableLiveData<List<ColorItem>>()
    val colorList: LiveData<List<ColorItem>>
        get() = _colorList

    init {
        loadColors()
    }

    private fun loadColors() {
        val colorArray = arrayListOf(
            R.color.card_pink, R.color.card_blue, R.color.card_yellow,
            R.color.card_red, R.color.black, R.color.card_violet,
            R.color.card_green, R.color.darkPurple, R.color.orangeRed,
            R.color.green, R.color.peach, R.color.purple,
            R.color.yellowOrange, R.color.MangoTango, R.color.maizeCrayola
        )

        val colorItems = colorArray.map { color -> ColorItem(color) }
        _colorList.value = colorItems
    }
}
