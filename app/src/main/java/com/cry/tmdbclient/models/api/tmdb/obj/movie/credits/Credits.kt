package com.cry.tmdbclient.models.api.tmdb.obj.movie.credits

data class Credits(
    val cast: List<Cast>,
    val crew: List<Crew>,
    val id: Int
)