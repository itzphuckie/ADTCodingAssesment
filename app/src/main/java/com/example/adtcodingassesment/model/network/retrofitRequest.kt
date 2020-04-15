package com.example.adtcodingassesment.model.network

import com.example.adtcodingassesment.model.data.ArticleList
import retrofit2.Response
import retrofit2.http.GET

interface retrofitRequest {
    @GET("v2/top-headlines?country=us&apiKey=1437be957ba74f9e93cf1688a28a05ac")
    suspend fun getBody():Response<ArticleList>
}