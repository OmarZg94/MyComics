package com.ozuniga.mycomics.utils

import com.ozuniga.mycomics.Application
import kotlin.math.sqrt

class Utils {
    companion object {
        fun isTablet(): Boolean {
            val metrics = Application.getContext().resources.displayMetrics
            val yInches = metrics.heightPixels / metrics.ydpi
            val xInches = metrics.widthPixels / metrics.xdpi
            val diagonalInches = sqrt((xInches * xInches + yInches * yInches).toDouble())
            return diagonalInches >= 6.5
        }
    }
}