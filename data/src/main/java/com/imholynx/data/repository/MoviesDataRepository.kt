package com.imholynx.data.repository

import com.imholynx.data.entity.MovieData
import com.imholynx.data.mapper.MovieDataMovieEntityMapper
import com.imholynx.data.utils.NetworkBoundResource
import com.imholynx.domain.entity.MovieEntity
import com.imholynx.domain.repository.MoviesRepository
import io.reactivex.Completable
import io.reactivex.Maybe
import io.reactivex.Observable
import io.reactivex.Single

class MoviesDataRepository(
    private val remoteMoviesDataStore: RemoteMoviesDataStore,
    private val localMoviesDataStore: LocalMoviesDataStore,
    private val localDataExpiredTime: Long
) : MoviesRepository {

    override fun getPopular(): Observable<List<MovieEntity>> {
        return object : NetworkBoundResource<List<MovieData>, List<MovieData>>() {
            override fun saveCallResult(request: List<MovieData>): Completable {

                request.map { movieData ->
                    movieData.lastFetch = System.currentTimeMillis()
                    movieData
                }
                return localMoviesDataStore.clear()
                    .andThen(localMoviesDataStore.addMovies(request))
            }

            override fun loadFromDb(): Observable<List<MovieData>> {
                return localMoviesDataStore.getPopular()
            }

            override fun shouldFetch(): Single<Boolean> {
                return localMoviesDataStore.getLastFetch()
                    .map(this@MoviesDataRepository::isExpired)
                    .toSingle(true)
            }

            override fun createCall(): Observable<List<MovieData>> {
                return remoteMoviesDataStore.getPopular()
            }

        }.asObservable()
            .map { it.map(MovieDataMovieEntityMapper::transform) }
    }

    override fun getMovieById(id: Int): Maybe<MovieEntity> {
        return localMoviesDataStore.getMovieById(id)
            .map(MovieDataMovieEntityMapper::transform)
    }

    fun isExpired(time: Long): Boolean {
        return (System.currentTimeMillis() - time) > localDataExpiredTime
    }
}