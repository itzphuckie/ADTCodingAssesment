package com.example.adtcodingassesment.model.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.adtcodingassesment.model.data.Article
import com.example.adtcodingassesment.model.data.Source


@Database(entities = [Article::class,Source::class], version = 5)
@TypeConverters(Converters::class)
abstract class AppDatabase:RoomDatabase(){
    abstract fun myDao() : MyDAO
}