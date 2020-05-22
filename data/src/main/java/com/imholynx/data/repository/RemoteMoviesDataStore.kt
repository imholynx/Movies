package com.imholynx.data.repository

import com.imholynx.data.api.RestApi
import com.imholynx.data.mapper.MovieDataMovieEntityMapper
import com.imholynx.domain.entity.MovieEntity
import io.reactivex.Observable

class RemoteMoviesDataStore(private val restApi: RestApi) : MoviesDataStore {

    override fun getPopular(): Observable<List<MovieEntity>> {
        return restApi.getPopular().map { it.results.map(MovieDataMovieEntityMapper::transform) }
    }

}