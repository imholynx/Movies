package com.imholynx.data.mapper

import com.imholynx.data.entity.MovieData
import com.imholynx.domain.entity.MovieEntity

object MovieDataMovieEntityMapper {

    fun transform(movieData: MovieData): MovieEntity {
        return MovieEntity(
            movieData.id,
            movieData.title,
            movieData.posterPath
        )
    }
}