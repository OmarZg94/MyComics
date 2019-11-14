package com.ozuniga.mycomics.modules.main.fr_home

import android.view.View

class HomeContracts {
    interface Presenter {
        fun onComicsReady()
        fun onError(msg: String)
    }

    interface Iterator {
        fun getComics()
    }

    interface OnComicClickListener {
        fun onComicClick(view: View, position: Int)
    }
}