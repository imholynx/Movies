package com.imholynx.movies.ui.details

import com.imholynx.domain.usecase.GetMovieUseCase
import com.imholynx.movies.ui.common.BaseViewModel

class DetailsViewModel(
    private val getMovieUseCase: GetMovieUseCase,
    private val movieId: Int
) : BaseViewModel() {
    // TODO: Implement the ViewModel
}
