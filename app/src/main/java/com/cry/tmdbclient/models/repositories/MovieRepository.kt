package com.cry.tmdbclient.models.repositories

import android.app.Application
import com.cry.tmdbclient.models.database.TMDbDatabase
import com.cry.tmdbclient.models.database.TMDbDatabase_Impl
import com.cry.tmdbclient.models.database.dao.MovieDao
import com.cry.tmdbclient.models.database.obj.Movie
import kotlinx.coroutines.*


class MovieRepository (app : Application) {
    val movieDao = TMDbDatabase.getDatabase(app).movieDao()
    //Insert
    fun insertMovie(movie : Movie) {
        GlobalScope.launch {
            movieDao.insertMovie(movie)
        }
    }
    //Update
    fun updateMovie(movie : Movie) {
        GlobalScope.launch {
            movieDao.updateMovie(movie)
        }
    }
    //Update All
    fun updateMovies(movies : List<Movie>) {
        GlobalScope.launch {
            for (movie in movies)
                movieDao.updateMovie(movie)
        }
    }
    //Delete
    fun deleteMovie(movie : Movie) {
        GlobalScope.launch {
            movieDao.deleteMovie(movie)
        }
    }
    //Query
    fun getMovie(movieId : Int, onSuccess: (Movie?) -> Unit) {
        GlobalScope.launch {
            var movie = movieDao.getMovieById(movieId)
            onSuccess(movie)
        }
    }
    fun getPopularMovies(onSuccess : (List<Movie>) -> Unit) {
        GlobalScope.launch {
            val movies = movieDao.getPopularMovies()
            onSuccess(movies)
        }
    }
    fun getNewestMovies(onSuccess : (List<Movie>) -> Unit) {
        //will leave this in blank
        //the app will have to fetch recent movies
        onSuccess(ArrayList())
    }
    fun getFavoriteMovies(onSuccess : (List<Movie>) -> Unit) {
        GlobalScope.launch {
            val movies = movieDao.getFavoriteMovies()
            onSuccess(movies)
        }
    }
    fun getMoviesById(ids : List<Int>, onSuccess: (List<Movie>) -> Unit) {
        GlobalScope.launch {
            val movies = movieDao.getMoviesById(ids)
            onSuccess(movies)
        }
    }

}