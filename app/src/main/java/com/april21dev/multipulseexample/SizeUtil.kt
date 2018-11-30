package com.april21dev.multipulseexample

import android.content.Context
import android.util.TypedValue

object SizeUtil {

    fun dpToPx(context: Context, dp: Int): Float =
        TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp.toFloat(), context.resources.displayMetrics)
}