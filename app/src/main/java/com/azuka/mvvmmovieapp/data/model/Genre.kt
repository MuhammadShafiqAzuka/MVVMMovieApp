package com.azuka.mvvmmovieapp.data.model


import com.google.gson.annotations.SerializedName

data class Genre(
    @SerializedName("genres")
    val genres: List<GenreX>
)