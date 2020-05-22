package com.imholynx.data.entity

import com.google.gson.annotations.SerializedName

data class MovieData(

    @SerializedName("id")
    var id: Int,

    @SerializedName("title")
    var title: String,

    @SerializedName("poster_path")
    var posterPath: String
)