package com.imholynx.data.repository

import com.imholynx.domain.entity.MovieEntity
import io.reactivex.Observable

interface MoviesDataStore {

    fun getPopular(): Observable<List<MovieEntity>>
}