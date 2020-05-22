package com.imholynx.movies.di.popular

import com.imholynx.domain.repository.MoviesRepository
import com.imholynx.domain.usecase.GetPopularUseCase
import dagger.Module
import dagger.Provides

@PopularScope
@Module
class PopularModule {

    @Provides
    fun provideGetPopularUseCase(moviesRepository: MoviesRepository): GetPopularUseCase {
        return GetPopularUseCase(moviesRepository)
    }

}