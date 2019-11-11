package com.ozuniga.mycomics

import android.content.Context
import androidx.multidex.MultiDex
import androidx.multidex.MultiDexApplication
import com.jakewharton.picasso.OkHttp3Downloader
import com.squareup.picasso.Picasso

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
        val builder = Picasso.Builder(this)
        builder.downloader(OkHttp3Downloader(this, Long.MAX_VALUE))
        val built = builder.build()
        built.setIndicatorsEnabled(true)
        built.isLoggingEnabled = true
        Picasso.setSingletonInstance(built)
    }
}