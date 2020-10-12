package com.azuka.mvvmmovieapp.ui.moviedetails

import androidx.lifecycle.LiveData
import com.azuka.mvvmmovieapp.ui.data.model.MovieDetails
import com.azuka.mvvmmovieapp.ui.data.repository.MovieDetailsNetworkData
import com.azuka.mvvmmovieapp.ui.data.repository.NetworkState
import com.azuka.mvvmmovieapp.ui.data.retrofit.RetrofitGet
import io.reactivex.disposables.CompositeDisposable

class MovieDetailsRepository(private val apiService: RetrofitGet) {

    //THIS REPO CLASS TO GET MOVIE DETAILS LOGIC ONLY
    //LOGIC : MOVIE DETAILS & NETWORK
    //THIS LOOK DUPLICATE FOR REPO, BUT IT WILL LOAD TO INTERNAL CACHE

    private lateinit var movieDetailsNetworkDataSource: MovieDetailsNetworkData

    fun fetchSingleMovieDetails(
        compositeDisposable: CompositeDisposable,
        movieId: Int
    ): LiveData<MovieDetails> {

        movieDetailsNetworkDataSource = MovieDetailsNetworkData(apiService, compositeDisposable)
        movieDetailsNetworkDataSource.fetchMovieDetails(movieId)

        return movieDetailsNetworkDataSource.downloadedMovieResponse
    }

    fun getMovieDetailsNetworkState(): LiveData<NetworkState> {
        return movieDetailsNetworkDataSource.networkState
    }
}