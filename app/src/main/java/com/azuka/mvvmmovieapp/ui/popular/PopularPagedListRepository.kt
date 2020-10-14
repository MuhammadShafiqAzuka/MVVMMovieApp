package com.azuka.mvvmmovieapp.ui.popular

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.azuka.mvvmmovieapp.data.model.Movie
import com.azuka.mvvmmovieapp.data.repository.MovieDataSource
import com.azuka.mvvmmovieapp.data.repository.MovieDataSourceFactory
import com.azuka.mvvmmovieapp.data.repository.NetworkState
import com.azuka.mvvmmovieapp.data.retrofit.POST_PER_PAGE
import com.azuka.mvvmmovieapp.data.retrofit.RetrofitGet
import io.reactivex.disposables.CompositeDisposable

//THIS CLASS TO GET LOGIC FROM FACTORY REPO
//INTERACT WITH UI

class PopularPagedListRepository(private val apiService: RetrofitGet) {

    private lateinit var moviePagedList: LiveData<PagedList<Movie>>
    private lateinit var moviesDataSourceFactory: MovieDataSourceFactory

    fun fetchLiveMoviePagedList(compositeDisposable: CompositeDisposable): LiveData<PagedList<Movie>> {
        moviesDataSourceFactory = MovieDataSourceFactory(apiService, compositeDisposable)

        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setPageSize(POST_PER_PAGE)
            .build()

        moviePagedList = LivePagedListBuilder(moviesDataSourceFactory, config).build()
        return moviePagedList
    }

    fun getNetworkState(): LiveData<NetworkState> {
        return Transformations.switchMap(
            moviesDataSourceFactory.moviesLiveDataSource, MovieDataSource::networkState
        )
    }
}