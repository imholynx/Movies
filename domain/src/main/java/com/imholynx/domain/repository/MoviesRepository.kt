package com.imholynx.domain.repository

import com.imholynx.domain.entity.MovieEntity
import io.reactivex.Observable

interface MoviesRepository {
    fun getPopular(): Observable<List<MovieEntity>>
}