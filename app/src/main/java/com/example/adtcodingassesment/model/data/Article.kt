package com.example.adtcodingassesment.model.data
import androidx.annotation.Nullable
import androidx.room.*


@Entity(tableName = "article_table")
data class Article (
    @Nullable
    @PrimaryKey(autoGenerate = true)
    val id:Int,
    val source: Source?,
    @ColumnInfo(name = "art_author")
    val author:String?,
    @ColumnInfo(name = "art_title")
    val title:String?,
    @ColumnInfo(name = "art_description")
    val description:String?,
    @ColumnInfo(name = "art_url")
    val url:String,
    @ColumnInfo(name = "art_urlToImage")
    val urlToImage:String?,
    @ColumnInfo(name = "art_pubAt")
    val publishedAt:String?,
    @ColumnInfo(name = "art_content")
    val content:String?
)