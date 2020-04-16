package com.example.adtcodingassesment.model.database

import androidx.room.TypeConverter
import com.example.adtcodingassesment.model.data.Article
import com.example.adtcodingassesment.model.data.Source
import com.google.gson.Gson

import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

/**
 * an utility class used to convert to JSON from Source data class and vice versa
 */
class Converters {

    @TypeConverter
    fun fromSource(list: Source): String {
        val gson = Gson()
        return gson.toJson(list)
    }
    @TypeConverter
    fun returnSource(value: String?): Source {
        return Gson().fromJson(value, Source::class.java)
    }
}
