package com.imholynx.movies.ui.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.imholynx.domain.usecase.GetPopularUseCase
import com.imholynx.movies.entity.Movie
import com.imholynx.movies.mapper.MovieEntityMovieMapper
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class ListViewModel(private val getPopularUseCase: GetPopularUseCase) : ViewModel() {

    private val compositeDisposable = CompositeDisposable()

    private val _movies: MutableLiveData<List<Movie>> = MutableLiveData()
    val movies: LiveData<List<Movie>> = _movies

    private val _loading: MutableLiveData<Boolean> = MutableLiveData()
    val loading: LiveData<Boolean> = _loading

    private val _dataNotAvailable: MutableLiveData<Boolean> = MutableLiveData()
    val dataNotAvailable: LiveData<Boolean> = _dataNotAvailable


    init {
        getPopular()
    }

    private fun getPopular() {
        _loading.value = true
        _dataNotAvailable.value = false
        compositeDisposable.clear()

        compositeDisposable.add(getPopularUseCase.getPopular()
            .map { it.map(MovieEntityMovieMapper::transform) }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ movies ->
                this._movies.value = movies
                _dataNotAvailable.value = false
                _loading.value = false
            }, {
                _dataNotAvailable.value = true
                _loading.value = false
            })
        )
    }

    override fun onCleared() {
        compositeDisposable.clear()
    }

    fun onRetry() {
        getPopular()
    }
}
