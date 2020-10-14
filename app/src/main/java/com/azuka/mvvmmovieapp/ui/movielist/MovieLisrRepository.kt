package com.azuka.mvvmmovieapp.ui.movielist

import androidx.lifecycle.LiveData
import com.azuka.mvvmmovieapp.data.model.Genre
import com.azuka.mvvmmovieapp.data.repository.MovieListDataSource
import com.azuka.mvvmmovieapp.data.repository.NetworkState
import com.azuka.mvvmmovieapp.data.retrofit.RetrofitGet
import io.reactivex.disposables.CompositeDisposable

class MovieLisrRepository(private val apiService: RetrofitGet) {

    private lateinit var movieListsNetworkDataSource: MovieListDataSource

    fun fetchMovieList(
        compositeDisposable: CompositeDisposable,
        //movieId: Int
    ): LiveData<Genre> {

        movieListsNetworkDataSource = MovieListDataSource(apiService, compositeDisposable)
        movieListsNetworkDataSource.fetchMovieList()

        return movieListsNetworkDataSource.showMovieList
    }

    fun getMovieListNetworkState(): LiveData<NetworkState> {
        return movieListsNetworkDataSource.networkState
    }
}