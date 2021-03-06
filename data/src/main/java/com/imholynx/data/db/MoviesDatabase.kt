package com.imholynx.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.imholynx.data.entity.MovieData


@Database(entities = [MovieData::class], version = 2)
abstract class MoviesDatabase : RoomDatabase() {

    abstract fun getMoviesDao(): MoviesDao
}