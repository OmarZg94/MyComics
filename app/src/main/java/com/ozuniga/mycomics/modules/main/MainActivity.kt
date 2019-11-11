package com.ozuniga.mycomics.modules.main

import android.content.res.Configuration
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.ozuniga.mycomics.R
import com.ozuniga.mycomics.databinding.ActivityMainBinding
import com.ozuniga.mycomics.helpers.activity_helper.ActivityHelper
import com.ozuniga.mycomics.helpers.activity_helper.Direction
import com.ozuniga.mycomics.utils.Utils

class MainActivity : ActivityHelper() {

    internal val router = lazy { MainRouter(this) }
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        router.value.presentHomeScreen(Direction.NONE, null)
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        if (Utils.isTablet()) {
            val fm = supportFragmentManager.findFragmentById(R.id.main_container)
            fm?.onConfigurationChanged(newConfig)
        }
    }
}
