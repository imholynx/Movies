package com.imholynx.data.api

import com.google.gson.annotations.SerializedName
import com.imholynx.data.entity.MovieData

data class PopularResponse(

    @SerializedName("results")
    var results: List<MovieData>
)