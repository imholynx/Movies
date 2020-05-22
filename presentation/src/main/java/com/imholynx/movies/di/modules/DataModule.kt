package com.imholynx.movies.di.modules

import com.imholynx.data.api.RestApi
import com.imholynx.data.repository.MoviesDataRepository
import com.imholynx.data.repository.RemoteMoviesDataStore
import com.imholynx.domain.repository.MoviesRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
@Singleton
class DataModule {

    @Provides
    @Singleton
    fun provideMovieRepository(restApi: RestApi): MoviesRepository {
        val remoteMoviesDataStore = RemoteMoviesDataStore(restApi)
        return MoviesDataRepository(remoteMoviesDataStore)
    }
}