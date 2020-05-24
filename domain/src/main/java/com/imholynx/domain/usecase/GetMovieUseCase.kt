package com.imholynx.domain.usecase

import com.imholynx.domain.entity.MovieEntity
import com.imholynx.domain.repository.MoviesRepository
import io.reactivex.Maybe

class GetMovieUseCase(private val moviesRepository: MoviesRepository) {

    fun getMovieById(id: Int): Maybe<MovieEntity> {
        return moviesRepository.getMovieById(id)
    }
}