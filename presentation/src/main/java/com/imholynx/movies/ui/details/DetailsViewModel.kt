package com.imholynx.movies.ui.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.imholynx.domain.usecase.GetMovieUseCase
import com.imholynx.movies.entity.Movie
import com.imholynx.movies.mapper.MovieEntityMovieMapper
import com.imholynx.movies.ui.common.BaseViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class DetailsViewModel(private val getMovieUseCase: GetMovieUseCase) : BaseViewModel() {

    private val _movie: MutableLiveData<Movie> = MutableLiveData()
    val movie: LiveData<Movie> = _movie

    private val _loading: MutableLiveData<Boolean> = MutableLiveData()
    val loading: LiveData<Boolean> = _loading

    private val _dataNotAvailable: MutableLiveData<Boolean> = MutableLiveData()
    val dataNotAvailable: LiveData<Boolean> = _dataNotAvailable

    fun getMovie(movieId: Int) {
        _dataNotAvailable.value = false
        compositeDisposable.clear()

        compositeDisposable.add(
            getMovieUseCase.getMovieById(movieId)
                .map(MovieEntityMovieMapper::transform)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { _loading.value = true }
                .doFinally { _loading.value = false }
                .subscribe(
                    { movie -> this._movie.value = movie },
                    { _dataNotAvailable.value = true })
        )
    }
}
