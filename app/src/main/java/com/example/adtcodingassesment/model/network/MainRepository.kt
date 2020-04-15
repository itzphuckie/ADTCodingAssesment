package com.example.adtcodingassesment.model.network

class MainRepository {
    var client = retrofitService.retrofitRequest
    suspend fun getNewsResponse() = client.getBody()
}