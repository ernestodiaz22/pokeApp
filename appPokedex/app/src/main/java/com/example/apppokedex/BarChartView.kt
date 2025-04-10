package com.example.apppokedex

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View

class BarChartView(context: Context, attrs: AttributeSet) : View(context, attrs) {

    private val paint = Paint()
    private var data = listOf<Pair<String, Int>>()

    fun setData(data: List<Pair<String, Int>>) {
        this.data = data
        // Redibuja el gráfico
        invalidate()
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        val chartWidth = width - 100
        val chartHeight = height - 100
        val barWidth = chartWidth / (data.size * 2)
        val maxVisits = data.maxByOrNull { it.second }?.second ?: 1

        paint.color = Color.BLUE
        var left = 50f
        for (entry in data) {
            val barHeight = (entry.second.toFloat() / maxVisits) * chartHeight
            canvas.drawRect(left, chartHeight - barHeight + 50f, left + barWidth, chartHeight + 50f, paint)
            left += barWidth * 2
        }

        paint.color = Color.BLACK
        paint.textSize = 30f
        left = 50f
        for (entry in data) {
            canvas.drawText(entry.first, left, chartHeight + 80f, paint)
            left += barWidth * 2
        }
    }
}
