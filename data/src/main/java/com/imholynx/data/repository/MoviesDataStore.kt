package com.imholynx.data.repository

import com.imholynx.data.entity.MovieData
import io.reactivex.Observable

interface MoviesDataStore {

    fun getPopular(): Observable<List<MovieData>>
}