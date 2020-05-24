package com.imholynx.domain.repository

import com.imholynx.domain.entity.MovieEntity
import io.reactivex.Maybe
import io.reactivex.Observable

interface MoviesRepository {
    fun getPopular(): Observable<List<MovieEntity>>

    fun getMovieById(id: Int): Maybe<MovieEntity>
}