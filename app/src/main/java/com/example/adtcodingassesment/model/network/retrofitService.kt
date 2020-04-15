package com.example.adtcodingassesment.model.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object retrofitService {
    const val BASE_URL = "https://newsapi.org/v2/"
    val retrofitService = Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build()
    val retrofitRequest = retrofitService.create(retrofitRequest::class.java)
}