package com.imholynx.movies.ui.list

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

    val movies: MutableLiveData<List<Movie>> = MutableLiveData()

    fun getPopular() {
        compositeDisposable.add(getPopularUseCase.getPopular()
            .map { it.map(MovieEntityMovieMapper::transform) }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ movies -> this.movies.value = movies },
                {
                    // TODO
                })
        )
    }

    override fun onCleared() {
        compositeDisposable.clear()
    }
}
