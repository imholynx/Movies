package com.imholynx.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "movies")
data class MovieData(

    @SerializedName("id")
    @PrimaryKey
    var id: Int,

    @SerializedName("title")
    var title: String,

    @SerializedName("poster_path")
    var posterPath: String,

    @SerializedName("overview")
    var overview: String,

    @ColumnInfo(name = "last_fetch")
    var lastFetch: Long = System.currentTimeMillis()
)