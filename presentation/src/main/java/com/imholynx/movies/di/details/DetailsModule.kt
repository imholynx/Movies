package com.imholynx.movies.di.details

import com.imholynx.domain.repository.MoviesRepository
import com.imholynx.domain.usecase.GetMovieUseCase
import dagger.Module
import dagger.Provides

@DetailsScope
@Module
class DetailsModule {

    @Provides
    fun provideGetMovieUseCase(moviesRepository: MoviesRepository): GetMovieUseCase {
        return GetMovieUseCase(moviesRepository)
    }
}