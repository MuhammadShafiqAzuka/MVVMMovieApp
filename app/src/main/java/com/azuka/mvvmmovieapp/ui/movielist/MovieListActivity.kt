package com.azuka.mvvmmovieapp.ui.movielist

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.azuka.mvvmmovieapp.R
import com.azuka.mvvmmovieapp.data.model.Genre
import com.azuka.mvvmmovieapp.data.retrofit.RetrofitBuilder
import com.azuka.mvvmmovieapp.data.retrofit.RetrofitGet
import kotlinx.android.synthetic.main.activity_movie_list.*

class MovieListActivity : AppCompatActivity() {

    private lateinit var viewModel: MovieListViewModel
    private lateinit var movieRepository: MovieLisrRepository
    private var titleList = mutableListOf<Genre>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_list)

        lv_movie_list.layoutManager = LinearLayoutManager(this)
        lv_movie_list.adapter = MovieListAdapter(titleList)

        val apiService: RetrofitGet = RetrofitBuilder.getClient()
        movieRepository = MovieLisrRepository(apiService)

        viewModel = getViewModel()

        viewModel.movieList.observe(this, Observer {
            bindUI(it)
        })

        /*viewModel.networkState.observe(this, Observer {
            progress_bar.visibility = if (it == NetworkState.LOADING) View.VISIBLE else View.GONE
            txt_error.visibility = if (it == NetworkState.ERROR) View.VISIBLE else View.GONE

        })*/
    }

    private fun bindUI(it: Genre?) {
        it?.let { it1 -> titleList.add(it1) }
    }

    private fun getViewModel(): MovieListViewModel {
        return ViewModelProviders.of(this, object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                @Suppress("UNCHECKED_CAST")
                return MovieListViewModel(movieRepository) as T
            }
        })[MovieListViewModel::class.java]
    }
}
