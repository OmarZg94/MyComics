package com.ozuniga.mycomics.modules.main.fr_home

import com.ozuniga.mycomics.net.ComicData
import com.ozuniga.mycomics.net.ComicsResult
import com.ozuniga.mycomics.net.MarvelAPI
import com.ozuniga.mycomics.utils.PRIVATE_KEY
import com.ozuniga.mycomics.utils.PUBLIC_KEY
import com.ozuniga.mycomics.utils.md5
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.net.HttpURLConnection
import java.text.SimpleDateFormat
import java.util.*

class HomeIterator(private val listener: HomeContracts.Presenter) : HomeContracts.Iterator {

    internal var comics: MutableList<ComicData> = mutableListOf()

    override fun getComics() {
        val time = System.currentTimeMillis()
        val date = SimpleDateFormat("yyyy-MM-dd").format(Date())
        val hash = "$time$PRIVATE_KEY$PUBLIC_KEY".md5()
        val limit = if (comics.isEmpty()) 10 else comics.size + 30
        MarvelAPI.getMarvelService().getComics(ts = time, limit = limit, dateRange = "1900-01-01,$date",
                hash = hash).enqueue(object : Callback<ComicsResult> {
            override fun onResponse(call: Call<ComicsResult>, response: Response<ComicsResult>) {
                if (response.body() != null && response.body()?.code == HttpURLConnection.HTTP_OK) {
                    if (!response.body()?.data?.results.isNullOrEmpty()) {
                        comics = response.body()?.data?.results!!
                        listener.onComicsReady()
                    } else {
                        listener.onError("Lista vacía")
                    }
                } else {
                    listener.onError(response.body()?.status ?: "Error en la obtención de comics")
                }
            }

            override fun onFailure(call: Call<ComicsResult>, t: Throwable) {
                listener.onError(t.message ?: "Error en la conexión con Marvel")
            }
        })
    }
}
