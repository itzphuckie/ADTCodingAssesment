package com.example.adtcodingassesment.model.network

import android.content.Context
import android.util.Log
import androidx.room.Room
import com.example.adtcodingassesment.model.data.Article
import com.example.adtcodingassesment.model.database.AppDatabase


class MainRepository{
    companion object{
        var context:Context ?= null
    }
    var client = retrofitService.retrofitRequest

    /**
     * describe the api call to fetch the data related to NewsFeed
     * @params N/A
     *
     */
    suspend fun getNewsResponse(){
        var list = client.getBody().body()?.articles
        postCachedReponse(list)
        Log.d("getNews",list.toString())
    }

    /**
     * describe fetching the cached data
     * @params N/A
     *
     */
    suspend fun getCachedReponse(isConnected: Boolean):List<Article?>?{
        var myDB = context?.let {
            Room.databaseBuilder(it,AppDatabase::class.java,"articleDatabase")
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .build()
        }
        if (isConnected){
            if(!myDB?.myDao()?.getAllArticles().isNullOrEmpty()){
                myDB?.myDao()?.deleteArticles()
            }
            getNewsResponse()
        }
        getNewsResponse()
        return myDB?.myDao()?.getAllArticles()
    }

    /**
     * describe saving the data after fetching from the server
     * @params N/A
     *
     */
    fun postCachedReponse(articleList: List<Article?>?){
        var myDB = context?.let {
            Room.databaseBuilder(it, AppDatabase::class.java,"articleDatabase")
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .build()
        }
        for (element in articleList!!){
            myDB?.myDao()?.addArticle(element)
        }
    }


}