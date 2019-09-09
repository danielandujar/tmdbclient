package com.cry.tmdbclient.viewmodels

import android.app.Application
import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.cry.tmdbclient.core.arch.BaseViewModel
import com.cry.tmdbclient.models.api.tmdb.obj.search.Search
import com.cry.tmdbclient.models.database.obj.Movie
import com.cry.tmdbclient.models.database.obj.WatchList
import com.cry.tmdbclient.models.repositories.MovieRepository
import com.cry.tmdbclient.models.repositories.TMDbRepository
import com.cry.tmdbclient.models.repositories.WatchListRepository
import com.cry.tmdbclient.views.Adapters.SearchListAdapter
import org.threeten.bp.Instant

class SearchMoviesViewModel (app : Application) : BaseViewModel(app)
{
    var mApp = app
    protected var mMovieList = MutableLiveData<MutableList<Movie>>()
    val movieList : LiveData<MutableList<Movie>> get() = mMovieList

    protected lateinit var mRealAdapter : SearchListAdapter
    protected var mAdapter = MutableLiveData<SearchListAdapter>()
    val adapter : LiveData<SearchListAdapter> get() = mAdapter

    init {

    }

    fun searchMovie (movieTitle : String) {
        TMDbRepository().searchMovieByName(movieTitle){
            var list = convertToMovieListObject(it)
            mMovieList.postValue(list)
            mRealAdapter = SearchListAdapter(list, this)
            mAdapter.postValue(mRealAdapter)
        }
    }

    private fun convertToMovieListObject(search : Search) : MutableList<Movie> {
        var list = mutableListOf<Movie>()
        for (r in search.results) {
            val mov = Movie(
                r.id,
                r.title,
                r.original_title,
                "",
                r.poster_path,
                r.backdrop_path ?: "",
                r.overview,
                r.original_language,
                r.popularity,
                r.release_date,
                r.video,
                r.vote_average,
                r.vote_count,
                -1,
                "",
                -1,
                -1, "",
                r.adult,
                false )
            list.add(mov)
        }
        return list
    }

    fun toggleFavorite(movie : Movie) {
        movie.isFavorite = !movie.isFavorite
        MovieRepository(mApp).updateMovie(movie)
    }

    fun addMovieWatchList(movie : Movie) {
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