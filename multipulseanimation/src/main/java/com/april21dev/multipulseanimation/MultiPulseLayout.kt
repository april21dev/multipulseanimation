package com.april21dev.multipulseanimation

import android.animation.Animator
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.graphics.drawable.Drawable
import android.support.constraint.ConstraintLayout
import android.util.AttributeSet

/**
 * @author april21dev
 *
 * Multiple Purse Drawing Layout.
 *
 * You can set start radius (default is 0) but end radius is automatically determined by this view size: min(width, height)/2
 *
 * It can have child views and purses will be drawn under child views.
 *
 */
class MultiPulseLayout(context: Context, attrs: AttributeSet?, defStyleAttr: Int) :
    ConstraintLayout(context, attrs, defStyleAttr) {
    constructor(context: Context) : this(context, null)
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)

    private val mDummyAnimTarget = DummyAnimTarget()
    private val mPulseDrawableList = mutableListOf<PulseDrawable>()
    private val mDrawableCallback = PulseDrawableCallback()
    private var mAnimatorSet: AnimatorSet = AnimatorSet()
    private var mPurseCount: Int = 1
    private var mDuration: Long = 1000
    private var mPurseColor: Int = Color.RED
    private var mPaintStyle = Paint.Style.FILL
    private var mStartRadius = 0f
    private var mStrokeWidth = 0f

    init {
        attrs?.let {
            val styledAttributes = context.obtainStyledAttributes(attrs, R.styleable.MultiPulseLayout)
            mPurseCount = styledAttributes.getInt(R.styleable.MultiPulseLayout_mpa_purse_count, 1)
            mDuration = styledAttributes.getInt(R.styleable.MultiPulseLayout_mpa_duration, 1000).toLong()
            mPurseColor = styledAttributes.getColor(R.styleable.MultiPulseLayout_mpa_purse_color, Color.RED)
            mStartRadius = styledAttributes.getDimension(R.styleable.MultiPulseLayout_mpa_start_radius, 0f)
            mStrokeWidth = styledAttributes.getDimension(R.styleable.MultiPulseLayout_mpa_stroke_width, 0f)
            mPaintStyle = styledAttributes.getInt(R.styleable.MultiPulseLayout_mpa_paint_style, 0).let { paintStyle ->
                when (paintStyle) {
                    0 -> Paint.Style.FILL
                    1 -> Paint.Style.STROKE
                    else -> Paint.Style.FILL
                }
            }

            styledAttributes.recycle()
        }
    }

    /**
     * Start animations
     */
    fun start() {
        build()
        mAnimatorSet.cancel()
        mAnimatorSet.start()
    }

    /**
     * Stop animations (Doesn't remove purse drawables from view)
     */
    fun stop() {
        mAnimatorSet.cancel()
    }

    /**
     * Clear all animations (Also remove purse drawables from view)
     */
    fun clear() {
        mAnimatorSet.cancel()
        mAnimatorSet.childAnimations.clear()
        mPulseDrawableList.clear()
        postInvalidate()
    }

    /**
     * Set purse count. Default is 1
     */
    fun setPurseCount(count: Int): MultiPulseLayout {
        this.mPurseCount = count
        return this
    }

    /**
     * Set purse duration. Default is 1000
     */
    fun setDuration(duration: Long): MultiPulseLayout {
        this.mDuration = duration
        return this
    }

    /**
     * Set purse color. Default is Color.RED
     */
    fun setPurseColor(color: Int): MultiPulseLayout {
        this.mPurseColor = color
        return this
    }

    /**
     * Set paint style. Default is Paint.Style.FILL
     */
    fun setPaintStyle(paintStyle: Paint.Style): MultiPulseLayout {
        this.mPaintStyle = paintStyle
        return this
    }

    /**
     * Set stroke width. Default is 1pixel (Need only when paintStyle is STROKE)
     */
    fun setStrokeWidth(strokeWidth: Float): MultiPulseLayout {
        this.mStrokeWidth = strokeWidth
        return this
    }

    /**
     * Set start radius. Default is 0 (Should not over min(view width, view height)/2)
     */
    fun setStartRadius(startRadius: Float): MultiPulseLayout {
        this.mStartRadius = startRadius
        return this
    }

    /**
     * Make AnimatorSet with set attributes
     */
    private fun build() {
        clear()

        val animatorList = mutableListOf<Animator>()
        for (i in 0 until mPurseCount) {
            val pulseDrawable = PulseDrawable(mPurseColor, mPaintStyle, mStrokeWidth, mStartRadius)
            mPulseDrawableList.add(pulseDrawable)

            if (i == 0) {
                pulseDrawable.callback = mDrawableCallback
            }

            val animator = ObjectAnimator.ofFloat(mDummyAnimTarget, "animValue", 0f, 1f).apply {
                val delay = i * mDuration / mPurseCount

                repeatCount = ObjectAnimator.INFINITE
                repeatMode = ObjectAnimator.RESTART
                startDelay = delay

                addUpdateListener {
                    val animValue = it.animatedValue as? Float ?: 0F
                    pulseDrawable.update(Rect(0, 0, width, height), animValue)
                }
            }
            animatorList.add(animator)
        }

        mAnimatorSet = AnimatorSet()
        mAnimatorSet.duration = mDuration
        mAnimatorSet.playTogether(animatorList)
    }

    override fun dispatchDraw(canvas: Canvas?) {
        canvas?.let { cv ->
            mPulseDrawableList.forEach {
                it.draw(cv)
            }
        }
        super.dispatchDraw(canvas)
    }

    private inner class PulseDrawableCallback : Drawable.Callback {
        override fun invalidateDrawable(who: Drawable) {
            postInvalidate()
        }

        override fun unscheduleDrawable(who: Drawable, what: Runnable) {}
        override fun scheduleDrawable(who: Drawable, what: Runnable, `when`: Long) {}
    }
}