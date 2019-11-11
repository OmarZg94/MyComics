package com.ozuniga.mycomics.dialogs

import android.content.Context
import android.view.Gravity
import android.widget.TextView
import com.airbnb.lottie.LottieAnimationView
import com.airbnb.lottie.LottieDrawable
import com.ozuniga.mycomics.R

class StatusDialog(context: Context, title: String?) :
    BaseDialog(context, R.layout.dialog_status, Gravity.TOP, true) {

    private var title: TextView? = null
    private var lottie: LottieAnimationView? = null
    private val TAG = "StatusDialog"

    init {
        initView()
        setCancelable(false)
        setCanceledOnTouchOutside(false)
        if (title != null) {
            if (title.isNotEmpty()) {
                this.title!!.text = title
            }
        }
    }

    private fun initView() {
        title = findViewById(R.id.txt_status)
        lottie = findViewById(R.id.animation_view)
        lottie!!.repeatMode = LottieDrawable.RESTART
        lottie!!.loop(true)
        lottie!!.imageAssetsFolder = "loader"
        lottie!!.setAnimation(R.raw.loader)
        lottie!!.playAnimation()
    }

    fun updateTitle(title: String) {
        this.title!!.text = title
    }

    fun dismissSelf() {
        lottie!!.pauseAnimation()
        this.dismiss()
    }
}