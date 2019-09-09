package com.cry.tmdbclient.models.repositories

import com.cry.tmdbclient.models.api.tmdb.obj.discover.Discover
import com.cry.tmdbclient.models.api.tmdb.TMDbClient
import com.cry.tmdbclient.models.api.tmdb.obj.movie.Movie
import com.cry.tmdbclient.models.api.tmdb.obj.movie.credits.Credits
import com.cry.tmdbclient.models.api.tmdb.obj.search.Search

class TMDbRepository
{
    fun getPopularMovies(onSuccess : (Discover) -> Unit)
    {
        TMDbClient().getPopularMovies {
            onSuccess(it)
        }
    }

    fun getRecentMovies(onSuccess : (Discover) -> Unit)
    {
        TMDbClient().getRecentMovies {
            onSuccess(it)
        }
    }

    fun loadMovie(movieId : Int, onSuccess: (Movie) -> Unit)
    {
        TMDbClient().loadMovie(movieId){
            onSuccess(it)
        }
    }

    fun loadMovieCredits(movieId: Int, onSuccess: (Credits) -> Unit){
        TMDbClient().loadMovieCredits(movieId) {
            onSuccess(it)
        }
    }

    fun searchMovieByName(queryRaw : String, onSuccess: (Search) -> Unit) {
        var query = queryRaw.trim().toLowerCase().replace(' ', '+')
        TMDbClient().searchMovieByName(query){
            onSuccess(it)
        }
    }
}