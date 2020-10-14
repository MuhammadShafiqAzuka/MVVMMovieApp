package com.azuka.mvvmmovieapp.ui.movielist

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.azuka.mvvmmovieapp.data.model.Genre
import com.azuka.mvvmmovieapp.data.repository.NetworkState
import io.reactivex.disposables.CompositeDisposable

class MovieListViewModel(private val movieListRepository: MovieLisrRepository) : ViewModel() {

    private val compositeDisposable = CompositeDisposable()

    val movieList: LiveData<Genre> by lazy {
        movieListRepository.fetchMovieList(compositeDisposable)
    }

    val networkState: LiveData<NetworkState> by lazy {
        movieListRepository.getMovieListNetworkState()
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}