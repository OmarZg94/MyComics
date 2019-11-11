package com.ozuniga.mycomics.modules.main

import android.view.View
import com.ozuniga.mycomics.helpers.activity_helper.Direction

class MainContracts {

    interface Router {
        fun presentHomeScreen(direction: Direction, view: View?)
        fun presentDetailScreen(direction: Direction, view: View)
    }
}