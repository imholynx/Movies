package com.imholynx.data.api

import io.reactivex.Observable
import retrofit2.http.GET

interface RestApi {

    @GET("movie/popular")
    fun getPopular(): Observable<PopularResponse>

}