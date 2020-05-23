package com.imholynx.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.imholynx.data.entity.MovieData
import io.reactivex.Completable
import io.reactivex.Maybe
import io.reactivex.Observable

@Dao
interface MoviesDao {

    @Query("SELECT * FROM movies")
    fun getMovies(): Observable<List<MovieData>>

    @Query("SELECT * FROM movies WHERE id=:id")
    fun getMovieById(id: Int): Maybe<MovieData>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(movies: List<MovieData>): Completable

    @Query("DELETE FROM movies")
    fun clear(): Completable

    @Query("SELECT last_fetch FROM movies ORDER BY last_fetch LIMIT 1")
    fun lastFetch(): Maybe<Long>
}