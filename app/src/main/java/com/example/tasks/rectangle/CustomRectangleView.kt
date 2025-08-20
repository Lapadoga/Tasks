package com.example.tasks.rectangle

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import android.view.View
import kotlin.random.Random

class CustomRectangleView(context: Context, attrs: AttributeSet) : View(context, attrs) {

    private val paintStroke = Paint().apply {
        style = Paint.Style.STROKE
        strokeWidth = 5f
        color = Color.BLACK
    }
    private val rectStroke = RectF()
    private val paintFiller = Paint().apply {
        style = Paint.Style.FILL
    }
    private val rectFill = RectF()
    private var clickCounter = 0
    private val fillPercentage = 10

    init {
        isClickable = true
        paintFiller.color = Color.TRANSPARENT

        setOnClickListener {
            clickCounter++
            if (clickCounter <= 10) {
                rectFill.set(
                    rectStroke.left,
                    rectStroke.top,
                    rectStroke.right / 100 * fillPercentage * clickCounter,
                    rectStroke.bottom
                )
                paintFiller.color = getRandomColor()
                invalidate()
            } else {
                clickCounter = 0
                rectFill.set(
                    0f,
                    0f,
                    0f,
                    0f
                )
                paintFiller.color = Color.TRANSPARENT
                invalidate()
            }
        }
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val width = MeasureSpec.getSize(widthMeasureSpec)

        setMeasuredDimension(width, width / 2)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        rectStroke.set(
            paddingLeft.toFloat(),
            paddingTop.toFloat(),
            width - paddingRight.toFloat(),
            height - paddingBottom.toFloat()
        )
        canvas.drawRect(rectStroke, paintStroke)
        canvas.drawRect(rectFill, paintFiller)
    }

    private fun getRandomColor(): Int {
        val red = Random.nextInt(256)
        val green = Random.nextInt(256)
        val blue = Random.nextInt(256)

        return Color.rgb(red, green, blue)
    }
}