package com.cry.tmdbclient.viewmodels

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.cry.tmdbclient.core.arch.BaseViewModel
import com.cry.tmdbclient.models.api.tmdb.obj.discover.Result
import com.cry.tmdbclient.models.database.obj.Movie
import com.cry.tmdbclient.models.repositories.MovieRepository
import com.cry.tmdbclient.views.Adapters.MoviesListAdapter
import org.threeten.bp.Instant

class FavoriteMoviesViewModel(app: Application) : BaseMoviesViewModel(app)
{
    init {
        fetchFavoriteMovies{
            if (it) { Log.i(TAG, "Successfully loaded favorite movies")}
        }
    }

    fun fetchFavoriteMovies(onSuccess : (Boolean) -> Unit) {
        MovieRepository(mApp).getFavoriteMovies{
            mMovieList.postValue(it.toMutableList())
            mRealAdapter = MoviesListAdapter(it.toMutableList(), this)
            mAdapter.postValue(mRealAdapter)
            mClassIsLoading.postValue(false)
            onSuccess(true)
        }
    }
}