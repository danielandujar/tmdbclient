package com.cry.tmdbclient.viewmodels

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.cry.tmdbclient.core.arch.BaseViewModel
import com.cry.tmdbclient.models.database.obj.Movie
import com.cry.tmdbclient.models.repositories.MovieRepository
import com.cry.tmdbclient.models.repositories.WatchListRepository
import com.cry.tmdbclient.views.Adapters.MoviesListAdapter
import com.cry.tmdbclient.views.Adapters.WatchListAdapter

class WatchListViewModel (app : Application) : BaseViewModel(app)
{
    var mApp = app
    private var mMovieList = MutableLiveData<MutableList<Movie>>()
    val movieList : LiveData<MutableList<Movie>> get() = mMovieList

    private lateinit var mRealAdapter : WatchListAdapter
    private var mAdapter = MutableLiveData<WatchListAdapter>()
    val adapter : LiveData<WatchListAdapter> get() = mAdapter

    private var mClassIsLoading = MutableLiveData<Boolean>()
    val classIsLoading : LiveData<Boolean> get() = mClassIsLoading

    init {
        queryWatchList{
            if (it)
                Log.i("watchlist", "all is well")
        }
    }

    fun queryWatchList(onSuccess : (Boolean) -> Unit) {
        WatchListRepository(mApp).queryWatchList{
            if (it.isNotEmpty()) {
                val ids = mutableListOf<Int>()
                for (item in it) {
                    ids.add(item.movieId)
                }
                MovieRepository(mApp).getMoviesById(ids) { movies ->
                    mMovieList.postValue(movies.toMutableList())
                    mRealAdapter = WatchListAdapter(movies.toMutableList(), this)
                    mAdapter.postValue(mRealAdapter)
                    onSuccess(true)
                }
            }
            mClassIsLoading.postValue(false)
        }
    }

    fun toggleFavorite(movie : Movie) {
        val newMovie = Movie(
            movie.id, movie.title, movie.originalTitle, movie.tagline, movie.posterPath, movie.backdropPath,
            movie.overview, movie.originalLanguage, movie.popularity, movie.releaseDate, movie.hasVideo,
            movie.voteAverage, movie.voteCount, movie.budget, movie.homepage, movie.revenue, movie.runtime,
            movie.status, movie.isAdult, !movie.isFavorite )
        MovieRepository(mApp).updateMovie(newMovie)
    }
}