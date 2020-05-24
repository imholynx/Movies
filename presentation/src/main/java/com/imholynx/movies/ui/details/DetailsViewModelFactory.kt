package com.imholynx.movies.ui.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.imholynx.domain.usecase.GetMovieUseCase
import javax.inject.Inject

class DetailsViewModelFactory @Inject constructor(private val getMovieUseCase: GetMovieUseCase) :
    ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DetailsViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return DetailsViewModel(getMovieUseCase) as T
        }
        throw IllegalArgumentException("Unknown ViewModel")
    }

}