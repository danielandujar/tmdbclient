package com.cry.tmdbclient.viewmodels

import android.app.Application
import android.content.Context
import android.preference.Preference
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.cry.tmdbclient.R
import com.cry.tmdbclient.core.arch.BaseViewModel
import com.cry.tmdbclient.models.api.tmdb.obj.discover.Result
import com.cry.tmdbclient.models.database.obj.Movie
import com.cry.tmdbclient.models.database.obj.WatchList
import com.cry.tmdbclient.models.repositories.MovieRepository
import com.cry.tmdbclient.models.repositories.WatchListRepository
import com.cry.tmdbclient.views.Adapters.MoviesListAdapter
import org.threeten.bp.Instant
import org.threeten.bp.LocalDateTime
import org.threeten.bp.ZoneId
import org.threeten.bp.format.DateTimeFormatter
import java.util.*

open class BaseMoviesViewModel (app : Application) : BaseViewModel(app) {
    var mApp = app
    protected var mMovieList = MutableLiveData<MutableList<Movie>>()
    val movieList : LiveData<MutableList<Movie>> get() = mMovieList

    protected lateinit var mRealAdapter : MoviesListAdapter
    protected var mAdapter = MutableLiveData<MoviesListAdapter>()
    val adapter : LiveData<MoviesListAdapter> get() = mAdapter

    protected var mClassIsLoading = MutableLiveData<Boolean>()
    val classIsLoading : LiveData<Boolean> get() = mClassIsLoading

    fun toggleFavorite(movie : Movie) {
        movie.isFavorite = !movie.isFavorite
        MovieRepository(mApp).updateMovie(movie)
    }
    fun addMovieWatchList(movie : Movie)
    {
        var watchListId = mApp.getSharedPreferences("WATCHLIST_ID", Context.MODE_PRIVATE)
            .getInt("WATCHLIST_ID", 1)

        watchListId++

        var watchlist = WatchList(
            watchListId, movie.id, Instant.now(), false )

        WatchListRepository(mApp).insertWatchList(watchlist)

        val sharedPref = mApp.getSharedPreferences("WATCHLIST_ID", Context.MODE_PRIVATE) ?: return
        with (sharedPref.edit()) {
            putInt("WATCHLIST_ID", watchListId)
            apply()
        }
    }
}