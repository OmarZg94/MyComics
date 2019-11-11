package com.ozuniga.mycomics.dialogs

import android.app.Dialog
import android.content.Context
import android.view.WindowManager
import com.ozuniga.mycomics.R

abstract class BaseDialog : Dialog {

    private var iContext: Context

    /**
     * @param context
     * @param layoutResID
     * @param gravity
     * @param fullscreen
     * @Title:BaseDialog
     * @Description:TODO
     */
    constructor(context: Context, layoutResID: Int, gravity: Int, fullscreen: Boolean) : super(context, R.style.DialogStyle) {
        iContext = context
        setContentView(layoutResID)
        val wm = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        val dialogWindow = window
        // dialogWindow.clearFlags(WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
        val lp = dialogWindow!!.attributes
        dialogWindow.setGravity(gravity)
        lp.width = wm.defaultDisplay.width
        if (fullscreen)
            lp.height = wm.defaultDisplay.height
        dialogWindow.attributes = lp
        show()
    }
}