package com.imholynx.data.repository

import com.imholynx.data.db.MoviesDao
import com.imholynx.data.entity.MovieData
import io.reactivex.Completable
import io.reactivex.Maybe
import io.reactivex.Observable

class LocalMoviesDataStore(private val moviesDao: MoviesDao) : MoviesDataStore {

    override fun getPopular(): Observable<List<MovieData>> {
        return moviesDao.getMovies()
    }

    fun getMovieById(id: Int): Maybe<MovieData> {
        return moviesDao.getMovieById(id)
    }

    fun addMovies(movies: List<MovieData>): Completable {
        return moviesDao.insertAll(movies)
    }

    fun getLastFetch(): Maybe<Long> {
        return moviesDao.lastFetch()
    }

    fun clear(): Completable {
        return moviesDao.clear()
    }

}