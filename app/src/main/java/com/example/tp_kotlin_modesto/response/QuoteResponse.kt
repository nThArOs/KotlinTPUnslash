package com.example.tp_kotlin_modesto.response

data class QuoteResponse(
    val id: String,
    val urls: UrlsResponse,
    val liked_by_user: Boolean,
    val likes: Int,
    val description: String,
    val user: SponsorResponse
)
