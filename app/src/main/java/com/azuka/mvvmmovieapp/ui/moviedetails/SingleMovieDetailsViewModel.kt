package com.azuka.mvvmmovieapp.ui.moviedetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.azuka.mvvmmovieapp.ui.data.model.MovieDetails
import com.azuka.mvvmmovieapp.ui.data.repository.NetworkState
import io.reactivex.disposables.CompositeDisposable

class SingleMovieDetailsViewModel
    (private val movieRepository: MovieDetailsRepository, movieId: Int) : ViewModel() {

    // THIS VIEWMODEL CLASS WILL GET LOGIC FROM MOVIE DETAIL REPO
    // PASS TO LIVE DATA
    // ONLY ACCESS WHEN CALLED (LAZY)

    private val compositeDisposable = CompositeDisposable()

    val movieDetails: LiveData<MovieDetails> by lazy {
        movieRepository.fetchSingleMovieDetails(compositeDisposable, movieId)
    }

    val networkState: LiveData<NetworkState> by lazy {
        movieRepository.getMovieDetailsNetworkState()
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}