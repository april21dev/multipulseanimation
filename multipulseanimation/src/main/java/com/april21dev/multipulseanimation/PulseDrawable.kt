package com.april21dev.multipulseanimation

import android.graphics.*
import android.graphics.drawable.Drawable
import android.support.annotation.FloatRange

class PulseDrawable(
    color: Int,
    paintStyle: Paint.Style,
    strokeWidth: Float?,
    private val startRadius: Float
) : Drawable() {

    private var mPaint: Paint = Paint()
    private var currentAnimValue = 0f
    private var currentRadius = 0f

    init {
        mPaint.isAntiAlias = true
        mPaint.style = paintStyle
        mPaint.color = color
        mPaint.strokeWidth = strokeWidth ?: 0f
    }

    /**
     * @param animValue: Range from 0.0 to 1.0
     */
    fun update(bound: Rect, @FloatRange(from = 0.0, to = 1.0) animValue: Float) {
        bounds = bound
        mPaint.alpha = (Math.abs(1 - animValue) * 255).toInt()
        currentAnimValue = animValue
        val minBoundRadius = (Math.min(bounds.width(), bounds.height()) / 2).toFloat()
        currentRadius = startRadius + (minBoundRadius - startRadius) * currentAnimValue
        invalidateSelf()
    }

    override fun draw(canvas: Canvas) {
        canvas.drawCircle(bounds.exactCenterX(), bounds.exactCenterY(), currentRadius, mPaint)
    }

    override fun setColorFilter(colorFilter: ColorFilter?) {}
    override fun setAlpha(alpha: Int) {}
    override fun getOpacity(): Int = PixelFormat.TRANSPARENT
}