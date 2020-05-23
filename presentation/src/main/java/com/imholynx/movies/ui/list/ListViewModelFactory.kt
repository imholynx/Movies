package com.imholynx.movies.ui.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.imholynx.domain.usecase.GetPopularUseCase
import javax.inject.Inject

class ListViewModelFactory @Inject constructor(private val getPopularUseCase: GetPopularUseCase) :
    ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ListViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ListViewModel(getPopularUseCase) as T
        }
        throw IllegalArgumentException("Unknown ViewModel")
    }

}