package com.example.zenconnect

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import android.animation.ValueAnimator
import android.view.animation.DecelerateInterpolator

class BreathAnimationView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    private val paint = Paint().apply {
        color = context.getColor(R.color.breathCircleColor)
        isAntiAlias = true
    }

    private var scaleFactor: Float = 1.0f

    fun animateCircle(targetScale: Float) {
        val animator = ValueAnimator.ofFloat(scaleFactor, targetScale)
        animator.duration = 2000
        animator.interpolator = DecelerateInterpolator()
        animator.addUpdateListener {
            scaleFactor = it.animatedValue as Float
            invalidate()
        }
        animator.start()
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.let {
            val radius = (width / 4) * scaleFactor
            val centerX = width / 2f
            val centerY = height / 2f
            it.drawCircle(centerX, centerY, radius, paint)
        }
    }
}
