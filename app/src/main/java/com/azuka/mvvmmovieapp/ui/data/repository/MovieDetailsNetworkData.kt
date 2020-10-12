package com.azuka.mvvmmovieapp.ui.data.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.azuka.mvvmmovieapp.ui.data.model.MovieDetails
import com.azuka.mvvmmovieapp.ui.data.retrofit.RetrofitGet
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class MovieDetailsNetworkData(
    private val apiService: RetrofitGet,
    private val compositeDisposable: CompositeDisposable
) {

    //THIS IS GENERAL REPO CLASS
    //DO LOGIC FOR MOVIE DETAILS

    private val _networkState = MutableLiveData<NetworkState>()
    val networkState: LiveData<NetworkState>
        get() = _networkState
    //with this get, no need to implement get function to get networkSate

    private val _downloadedMovieDetailsResponse = MutableLiveData<MovieDetails>()
    val downloadedMovieResponse: LiveData<MovieDetails>
        get() = _downloadedMovieDetailsResponse

    fun fetchMovieDetails(movieId: Int) {

        _networkState.postValue(NetworkState.LOADING)


        try {
            compositeDisposable.add(
                apiService.getMovieDetails(movieId)
                    .subscribeOn(Schedulers.io())
                    .subscribe(
                        {
                            _downloadedMovieDetailsResponse.postValue(it)
                            _networkState.postValue(NetworkState.LOADED)
                        },
                        {
                            _networkState.postValue(NetworkState.ERROR)
                            Log.e("MovieDetailsDataSource", it.message.toString())
                        }
                    )
            )

        } catch (e: Exception) {
            Log.e("MovieDetailsDataSource", e.message.toString())
        }


    }
}