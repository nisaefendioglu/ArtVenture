package com.nisaefendioglu.paintapp.view

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View

class PaintView : View {
    companion object {
        var pathList = ArrayList<Path>()
        var colorList = ArrayList<Int>()
        var paintStroke: Float = 5f
    }

    private var path = Path()
    private var currentBrush = Color.BLACK
    private val paintBrush = Paint()

    constructor(context: Context) : this(context, null) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        init()
    }

    private fun init() {
        paintBrush.isAntiAlias = true
        paintBrush.color = currentBrush
        paintBrush.style = Paint.Style.STROKE
        paintBrush.strokeJoin = Paint.Join.ROUND
        paintBrush.strokeWidth = paintStroke
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        val x = event.x
        val y = event.y

        when (event.action) {
            MotionEvent.ACTION_DOWN -> touchStart(x, y)
            MotionEvent.ACTION_MOVE -> touchMove(x, y)
            MotionEvent.ACTION_UP -> touchUp()
        }

        return true
    }

    private fun touchStart(x: Float, y: Float) {
        path = Path()
        path.moveTo(x, y)
        pathList.add(path)
        colorList.add(currentBrush)
    }

    private fun touchMove(x: Float, y: Float) {
        path.lineTo(x, y)
        invalidate()
    }

    private fun touchUp() {
        // Nothing needed for now
    }

    fun clearPaths() {
        pathList.clear()
        colorList.clear()
        path.reset()
        invalidate()
    }

    override fun onDraw(canvas: Canvas) {
        paintBrush.strokeWidth = paintStroke
        for (i in pathList.indices) {
            paintBrush.color = colorList[i]
            canvas.drawPath(pathList[i], paintBrush)
        }
    }

    fun setPaintColor(color: Int) {
        currentBrush = color
    }

    fun setPaintStroke(strokeWidth: Float) {
        paintStroke = strokeWidth
        invalidate()
    }
}
