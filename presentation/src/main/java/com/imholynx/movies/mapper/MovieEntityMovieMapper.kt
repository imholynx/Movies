package com.imholynx.movies.mapper

import com.imholynx.domain.entity.MovieEntity
import com.imholynx.movies.entity.Movie

object MovieEntityMovieMapper {

    fun transform(movieEntity: MovieEntity): Movie {
        return Movie(
            movieEntity.id,
            movieEntity.title,
            movieEntity.posterPath,
            movieEntity.overview
        )
    }
}