package com.azuka.mvvmmovieapp.ui.moviedetails

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.azuka.mvvmmovieapp.R
import com.azuka.mvvmmovieapp.data.model.MovieDetails
import com.azuka.mvvmmovieapp.data.repository.NetworkState
import com.azuka.mvvmmovieapp.data.retrofit.POSTER_BASE_URL
import com.azuka.mvvmmovieapp.data.retrofit.RetrofitBuilder
import com.azuka.mvvmmovieapp.data.retrofit.RetrofitGet
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_movie_details.*
import java.text.NumberFormat
import java.util.*

class MovieDetailsActivity : AppCompatActivity() {

    private lateinit var viewModel: SingleMovieDetailsViewModel
    private lateinit var movieRepository: MovieDetailsRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_details)

        val movieId: Int = intent.getIntExtra("id", 1)

        val apiService: RetrofitGet = RetrofitBuilder.getClient()
        movieRepository = MovieDetailsRepository(apiService)

        viewModel = getViewModel(movieId)

        //
        viewModel.movieDetails.observe(this, {
            bindUI(it)
        })

        viewModel.networkState.observe(this, {
            progress_bar.visibility = if (it == NetworkState.LOADING) View.VISIBLE else View.GONE
            txt_error.visibility = if (it == NetworkState.ERROR) View.VISIBLE else View.GONE

        })

    }

    private fun bindUI(it: MovieDetails?) {
        movie_title.text = it!!.title
        movie_tagline.text = it.tagline
        movie_release_date.text = it.releaseDate
        movie_rating.text = it.rating.toString()
        movie_runtime.text = it.runtime.toString() + " minutes"
        movie_overview.text = it.overview

        val formatCurrency = NumberFormat.getCurrencyInstance(Locale.US)
        movie_budget.text = formatCurrency.format(it.budget)
        movie_revenue.text = formatCurrency.format(it.revenue)

        val moviePosterURL = POSTER_BASE_URL + it.posterPath
        Glide.with(this)
            .load(moviePosterURL)
            .into(iv_movie_poster)
    }

    private fun getViewModel(movieId: Int): SingleMovieDetailsViewModel {
        return ViewModelProviders.of(this, object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                @Suppress("UNCHECKED_CAST")
                return SingleMovieDetailsViewModel(movieRepository, movieId) as T
            }
        })[SingleMovieDetailsViewModel::class.java]
    }
}