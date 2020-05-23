package com.imholynx.movies.di.common

import android.content.Context
import androidx.room.Room
import com.imholynx.data.api.RestApi
import com.imholynx.data.db.MoviesDao
import com.imholynx.data.db.MoviesDatabase
import com.imholynx.data.repository.LocalMoviesDataStore
import com.imholynx.data.repository.MoviesDataRepository
import com.imholynx.data.repository.RemoteMoviesDataStore
import com.imholynx.domain.repository.MoviesRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DataModule {

    companion object {
        private const val CACHE_TIMEOUT_IN_MILLIS: Long = 2 * 60 * 60 * 1000
    }

    @Provides
    @Singleton
    fun provideMovieRepository(restApi: RestApi, moviesDao: MoviesDao): MoviesRepository {
        val remoteMoviesDataStore = RemoteMoviesDataStore(restApi)
        val localMoviesDataStore = LocalMoviesDataStore(moviesDao)
        return MoviesDataRepository(
            remoteMoviesDataStore,
            localMoviesDataStore,
            CACHE_TIMEOUT_IN_MILLIS
        )
    }

    @Singleton
    @Provides
    fun provideMoviesDatabase(context: Context): MoviesDatabase {
        return Room.databaseBuilder(context, MoviesDatabase::class.java, "movies_db")
            .build()
    }

    @Singleton
    @Provides
    fun provideMoviesDao(database: MoviesDatabase): MoviesDao {
        return database.getMoviesDao();
    }
}