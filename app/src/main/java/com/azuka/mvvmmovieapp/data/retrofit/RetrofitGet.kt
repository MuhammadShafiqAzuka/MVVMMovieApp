package com.azuka.mvvmmovieapp.data.retrofit

import com.azuka.mvvmmovieapp.data.model.Genre
import com.azuka.mvvmmovieapp.data.model.MovieDetails
import com.azuka.mvvmmovieapp.data.model.MovieResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RetrofitGet {

    @GET("movie/popular")
    fun getPopularMovie(@Query("page") page: Int): Single<MovieResponse>

    @GET("movie/{movie_id}")
    fun getMovieDetails(@Path("movie_id") id: Int): Single<MovieDetails>

    @GET("genre/movie/list")
    fun getMovieList(): Single<Genre>
}