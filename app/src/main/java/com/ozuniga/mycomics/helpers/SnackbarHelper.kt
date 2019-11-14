package com.ozuniga.mycomics.helpers

import android.app.Activity
import android.view.Gravity
import android.widget.FrameLayout
import androidx.core.content.ContextCompat
import com.google.android.material.snackbar.Snackbar
import com.ozuniga.mycomics.Application
import com.ozuniga.mycomics.R

class SnackbarHelper {
    companion object {
        fun showErrorSnackBar(rootView: Activity, message: String, length: Int) {
            val snack = Snackbar.make(rootView.window.decorView, message, length)
            val view = snack.view
            val params = view.layoutParams as FrameLayout.LayoutParams
            params.gravity = Gravity.BOTTOM
            params.setMargins(16, 0, 16, 100)
            view.layoutParams = params
            view.setBackgroundColor(ContextCompat.getColor(Application.getContext(), R.color.colorRed))
            snack.show()
        }

        fun showSuccessSnackBar(rootView: Activity, message: String, length: Int) {
            val snack = Snackbar.make(rootView.window.decorView, message, length)
            val view = snack.view
            val params = view.layoutParams as FrameLayout.LayoutParams
            params.gravity = Gravity.BOTTOM
            params.setMargins(16, 0, 16, 100)
            view.layoutParams = params
            view.setBackgroundColor(ContextCompat.getColor(Application.getContext(), R.color.colorGreen))
            snack.show()
        }
    }
}