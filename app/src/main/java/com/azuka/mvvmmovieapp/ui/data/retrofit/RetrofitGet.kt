package com.azuka.mvvmmovieapp.ui.data.retrofit

import com.azuka.mvvmmovieapp.ui.data.model.MovieDetails
import com.azuka.mvvmmovieapp.ui.data.model.MovieResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RetrofitGet {

    @GET("movie/popular")
    fun getPopularMovie(@Query("page") page: Int): Single<MovieResponse>

    @GET("movie/{movie_id}")
    fun getMovieDetails(@Path("movie_id") id: Int): Single<MovieDetails>
}