package com.azuka.mvvmmovieapp.ui.popular

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.azuka.mvvmmovieapp.data.model.Movie
import com.azuka.mvvmmovieapp.data.repository.NetworkState
import io.reactivex.disposables.CompositeDisposable

class MainActivityViewModel(private val movieRepository: PopularPagedListRepository) : ViewModel() {

    // THIS VIEWMODEL CLASS WILL GET LOGIC FROM ITS OWN REPO
    // PASS TO LIVE DATA
    // ONLY ACCESS WHEN CALLED (LAZY)

    private val compositeDisposable = CompositeDisposable()

    val moviePagedList: LiveData<PagedList<Movie>> by lazy {
        movieRepository.fetchLiveMoviePagedList(compositeDisposable)
    }

    val networkState: LiveData<NetworkState> by lazy {
        movieRepository.getNetworkState()
    }

    fun listIsEmpty(): Boolean {
        return moviePagedList.value?.isEmpty() ?: true
    }


    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}