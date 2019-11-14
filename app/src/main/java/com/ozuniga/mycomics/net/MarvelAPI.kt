package com.ozuniga.mycomics.net

import com.ozuniga.mycomics.BuildConfig
import com.ozuniga.mycomics.utils.PUBLIC_KEY
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import java.util.concurrent.TimeUnit

class MarvelAPI {
    companion object {
        fun getMarvelService(): MarvelInterface {
            val customClient = OkHttpClient.Builder().readTimeout(30, TimeUnit.SECONDS)
                    .connectTimeout(60, TimeUnit.SECONDS)
            /* Enable network logging only in debug mode */
            if (BuildConfig.DEBUG) {
                val loggingInterceptor = HttpLoggingInterceptor()
                loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
                customClient.addInterceptor(loggingInterceptor)
            }
            val clientBuilder = customClient.build()
            val builder = Retrofit.Builder().baseUrl("https://gateway.marvel.com/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(clientBuilder).build()
            return builder.create(MarvelInterface::class.java)
        }
    }

    interface MarvelInterface {
        @GET("v1/public/comics")
        fun getComics(@Query("ts") ts: Long,
                      @Query("limit") limit: Int,
                      @Query("dateRange") dateRange: String,
                      @Query("apikey") apikey: String = PUBLIC_KEY,
                      @Query("hash") hash: String,
                      @Query("orderBy") orderBy: String = "-onsaleDate"): Call<ComicsResult>
    }
}