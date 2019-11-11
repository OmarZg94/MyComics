package com.ozuniga.mycomics.helpers.activity_helper

import androidx.annotation.AnimRes
import com.ozuniga.mycomics.R

enum class Direction(@param:AnimRes val enterAnimation: Int, @param:AnimRes val exitAnimation: Int) {
    FORDWARD(R.anim.slide_from_right, R.anim.slide_to_left),
    BACK(R.anim.slide_from_left, R.anim.slide_to_right),
    BOTTOM(R.anim.slide_from_top, R.anim.slide_to_bottom),
    TOP(R.anim.slide_from_bottom, R.anim.slide_to_top),
    NONE(0, 0)
}