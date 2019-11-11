package com.ozuniga.mycomics

import android.annotation.SuppressLint
import android.content.Context
import androidx.multidex.MultiDex
import androidx.multidex.MultiDexApplication

@SuppressLint("StaticFieldLeak")
class Application : MultiDexApplication() {

    init {
        instance = this
    }

    companion object {
        val TAG = "Infringements"
        private var instance: Application? = null

        fun getContext(): Context = instance!!.applicationContext
    }

    override fun onCreate() {
        super.onCreate()
        MultiDex.install(getContext())
    }
}