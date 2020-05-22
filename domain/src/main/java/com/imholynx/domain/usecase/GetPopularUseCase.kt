package com.imholynx.domain.usecase

import com.imholynx.domain.entity.MovieEntity
import com.imholynx.domain.repository.MoviesRepository
import io.reactivex.Observable

class GetPopularUseCase(private val moviesRepository: MoviesRepository) {

    fun getPopular(): Observable<List<MovieEntity>> {
        return moviesRepository.getPopular()
    }
}