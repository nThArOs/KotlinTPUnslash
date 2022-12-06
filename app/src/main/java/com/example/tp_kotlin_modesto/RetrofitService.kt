package com.example.tp_kotlin_modesto

import com.example.tp_kotlin_modesto.response.QuoteResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitService {
    @GET("/photos/random")
    suspend fun getData(@Query("client_id") accessKey: String ="vsKBJQnRDk1doBI914JcGHGi8KLvzwaW9psCtgwvitg"): Response<QuoteResponse>
}