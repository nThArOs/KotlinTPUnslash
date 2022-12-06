package com.example.tp_kotlin_modesto

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitApi {
    fun getService(
    ): RetrofitService {
        val retrofitBuilder = Retrofit.Builder()

        val okhttpClient = OkHttpClient.Builder()
            .build()
        retrofitBuilder.client(okhttpClient)

        val retrofit = retrofitBuilder
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return retrofit.create(RetrofitService::class.java)
    }
    private const val BASE_URL = "https://api.unsplash.com/"

}