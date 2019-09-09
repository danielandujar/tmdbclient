package com.cry.tmdbclient.viewmodels

import android.app.Application
import com.cry.tmdbclient.models.api.tmdb.obj.discover.Discover
import com.cry.tmdbclient.models.database.obj.Movie
import com.cry.tmdbclient.models.repositories.MovieRepository
import com.cry.tmdbclient.models.repositories.TMDbRepository
import com.cry.tmdbclient.views.Adapters.MoviesListAdapter
import org.threeten.bp.Instant
import org.threeten.bp.LocalDateTime
import org.threeten.bp.ZoneId
import org.threeten.bp.format.DateTimeFormatter
import java.util.*

class NewestMoviesViewModel (app: Application) : BaseMoviesViewModel(app)
{
    init {
        MovieRepository(mApp).getNewestMovies {
            if (it.isEmpty()) {
                TMDbRepository().getRecentMovies {
                    var lst = convertToMovieListObject(it)
                    mMovieList.postValue(lst)
                    mRealAdapter = MoviesListAdapter(lst, this)
                    mAdapter.postValue(mRealAdapter)
                    for(movie in lst) {
                        MovieRepository(mApp).insertMovie(movie)
                    }
                    mClassIsLoading.postValue(false)
                }
            } else {
                var lst = it.toMutableList()
                mMovieList.postValue(lst)
                mRealAdapter = MoviesListAdapter(lst, this)
                mAdapter.postValue(mRealAdapter)
                mClassIsLoading.postValue(false)
            }
        }
    }
    fun fetchNewestMovies(onSuccess : (Boolean) -> Unit)
    {
        TMDbRepository().getRecentMovies {
            var lst = convertToMovieListObject(it)
            mMovieList.postValue(lst)
            mRealAdapter = MoviesListAdapter(lst, this)
            mAdapter.postValue(mRealAdapter)
            MovieRepository(mApp).updateMovies(lst)
            onSuccess(true)
        }
    }
    private fun convertToMovieListObject(discover : Discover) : MutableList<Movie> {
        var list = mutableListOf<Movie>()
        for (r in discover.results)
        {
            val mov = Movie(
                r.id,
                r.title,
                r.original_title,
                "",
                r.poster_path,
                r.backdrop_path,
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
                -1,
                "",
                r.adult,
                false
            )
            list.add(mov)
        }
        return list
    }
}