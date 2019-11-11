package com.ozuniga.mycomics.helpers

import android.annotation.SuppressLint
import android.content.Context
import com.ozuniga.mycomics.dialogs.StatusDialog

@SuppressLint("StaticFieldLeak")
object DialogStatusHelper {

    private var dialog: StatusDialog? = null

    fun showDialog(context: Context, title: String) {
        if (dialog == null) {
            dialog = StatusDialog(context, title)
        } else {
            dialog!!.updateTitle(title)
        }
    }

    fun closeDialog() {
        if (dialog != null) {
            try {
                dialog?.dismiss()
                dialog = null
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}