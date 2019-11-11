package com.ozuniga.mycomics.modules.main

import android.view.View
import com.ozuniga.mycomics.R
import com.ozuniga.mycomics.helpers.activity_helper.ActivityHelper
import com.ozuniga.mycomics.helpers.activity_helper.Direction
import com.ozuniga.mycomics.modules.main.fr_home.HomeFragment

internal const val VIEW_LIST = "view_list"

class MainRouter(private val activity: ActivityHelper) : MainContracts.Router {
    override fun presentHomeScreen(direction: Direction, view: View?) {
        if (view != null) {
            activity.loadFragment(HomeFragment.newInstance(), R.id.main_container, direction, false, view, VIEW_LIST)
        } else {
            activity.loadFragment(HomeFragment.newInstance(), R.id.main_container, direction, false)
        }
    }

    override fun presentDetailScreen(direction: Direction, view: View) {

    }
}