package com.imholynx.data.mapper

import com.imholynx.data.entity.MovieData
import com.imholynx.domain.entity.MovieEntity

object MovieDataMovieEntityMapper {

    private const val posterPrefix = "https://image.tmdb.org/t/p/w500/"

    fun transform(movieData: MovieData): MovieEntity {
        return MovieEntity(
            movieData.id,
            movieData.title,
            posterPrefix + movieData.posterPath
        )
    }
}