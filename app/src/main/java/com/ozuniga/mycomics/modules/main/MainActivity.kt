package com.ozuniga.mycomics.modules.main

import android.os.Bundle
import com.ozuniga.mycomics.R
import com.ozuniga.mycomics.helpers.activity_helper.ActivityHelper

class MainActivity : ActivityHelper() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}
