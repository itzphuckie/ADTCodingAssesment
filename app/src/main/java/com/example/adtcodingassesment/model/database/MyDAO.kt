package com.example.adtcodingassesment.model.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.adtcodingassesment.model.data.Article

@Dao
interface MyDAO{
    @Insert
    fun addArticle(articleList: Article?)

    @Query("SELECT * from article_table")
    fun getAllArticles():List<Article?>?

    @Query("DELETE from article_table")
    fun deleteArticles()
}