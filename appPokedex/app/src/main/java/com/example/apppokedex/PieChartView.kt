package com.example.apppokedex

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import android.view.View
import kotlin.math.cos
import kotlin.math.sin

class PieChartView(context: Context, attrs: AttributeSet) : View(context, attrs) {

    private val paint = Paint()
    private var data = listOf<Pair<String, Int>>()

    fun setData(data: List<Pair<String, Int>>) {
        this.data = data
        invalidate()
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        val chartSize = Math.min(width, height) - 100
        val centerX = width / 2f
        val centerY = height / 2f
        val radius = chartSize / 2f
        var startAngle = 0f
        val totalVisits = data.sumBy { it.second }

        for (entry in data) {
            val sweepAngle = (entry.second.toFloat() / totalVisits) * 360f
            paint.color = randomColor()
            canvas.drawArc(RectF(centerX - radius, centerY - radius, centerX + radius, centerY + radius),
                startAngle, sweepAngle, true, paint)
            startAngle += sweepAngle
        }

        paint.color = Color.BLACK
        paint.textSize = 40f
        startAngle = 0f
        for (entry in data) {
            val sweepAngle = (entry.second.toFloat() / totalVisits) * 360f
            val midAngle = startAngle + sweepAngle / 2f
            val x = centerX + (radius / 2) * cos(Math.toRadians(midAngle.toDouble())).toFloat()
            val y = centerY + (radius / 2) * sin(Math.toRadians(midAngle.toDouble())).toFloat()
            canvas.drawText(entry.first, x, y, paint)
            startAngle += sweepAngle
        }
    }

    private fun randomColor(): Int {
        return Color.rgb((Math.random() * 255).toInt(), (Math.random() * 255).toInt(), (Math.random() * 255).toInt())
    }
}