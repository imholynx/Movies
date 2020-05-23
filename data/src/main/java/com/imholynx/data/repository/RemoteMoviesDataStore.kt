package com.imholynx.data.repository

import com.imholynx.data.api.RestApi
import com.imholynx.data.entity.MovieData
import io.reactivex.Observable

class RemoteMoviesDataStore(private val restApi: RestApi) : MoviesDataStore {

    override fun getPopular(): Observable<List<MovieData>> {
        return restApi.getPopular().map { it.results }
    }

}