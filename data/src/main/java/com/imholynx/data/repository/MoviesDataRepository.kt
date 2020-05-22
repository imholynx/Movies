package com.imholynx.data.repository

import com.imholynx.domain.entity.MovieEntity
import com.imholynx.domain.repository.MoviesRepository
import io.reactivex.Observable

class MoviesDataRepository(private val remoteMoviesDataStore: RemoteMoviesDataStore) :
    MoviesRepository {

    override fun getPopular(): Observable<List<MovieEntity>> {
        return remoteMoviesDataStore.getPopular()
    }

}