package com.example.movieswatchnow.data.service

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class APIInstance {
    companion object {
        private val httpClient: OkHttpClient.Builder = OkHttpClient.Builder()

        private val retrofitBuilder: Retrofit.Builder =
            Retrofit.Builder()
                .baseUrl("https://api.themoviedb.org/3/trending/movie/")
                .client(httpClient.build())
                .addConverterFactory(GsonConverterFactory.create())

        private val retrofit: Retrofit = retrofitBuilder.build()


        fun <T> createService(serviceClass: Class<T>): T {
            return retrofit.create(serviceClass)
        }
    }
}