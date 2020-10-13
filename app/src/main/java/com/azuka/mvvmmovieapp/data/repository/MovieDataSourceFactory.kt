package com.azuka.mvvmmovieapp.data.repository

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.azuka.mvvmmovieapp.data.model.Movie
import com.azuka.mvvmmovieapp.data.retrofit.RetrofitGet
import io.reactivex.disposables.CompositeDisposable

//THIS CLASS HANDLE DATA FOR PAGING
// AS GENERAL FACTORY REPO

class MovieDataSourceFactory(
    private val apiService: RetrofitGet,
    private val compositeDisposable: CompositeDisposable
) : DataSource.Factory<Int, Movie>() {

    val moviesLiveDataSource = MutableLiveData<MovieDataSource>()

    override fun create(): DataSource<Int, Movie> {
        val movieDataSource = MovieDataSource(apiService, compositeDisposable)

        moviesLiveDataSource.postValue(movieDataSource)
        return movieDataSource
    }
}