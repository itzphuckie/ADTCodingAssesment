package com.example.adtcodingassesment.model.data

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "source")
data class Source(
    @NonNull
    @PrimaryKey
    val id:String = "",
    @ColumnInfo(name = "source_name")
    val name:String?
)
