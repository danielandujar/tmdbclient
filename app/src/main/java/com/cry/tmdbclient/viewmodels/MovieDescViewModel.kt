package com.cry.tmdbclient.viewmodels

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.cry.tmdbclient.core.arch.BaseViewModel
import com.cry.tmdbclient.models.database.obj.Actor
import com.cry.tmdbclient.models.database.obj.Genre
import com.cry.tmdbclient.models.database.obj.Movie
import com.cry.tmdbclient.models.repositories.ActorRepository
import com.cry.tmdbclient.models.repositories.GenreRepository
import com.cry.tmdbclient.models.repositories.MovieRepository
import com.cry.tmdbclient.models.repositories.TMDbRepository
import com.cry.tmdbclient.views.Adapters.ActorsListAdapter
import java.text.NumberFormat
import java.util.*

class MovieDescViewModel(app: Application) : BaseViewModel(app)
{
    private val mApp = app
    private lateinit var movie : Movie

    private var mPosterPath = MutableLiveData<String>()
    val posterPath : LiveData<String> get() = mPosterPath

    private var mBackdropPath = MutableLiveData<String>()
    val backdropPath : LiveData<String> get() = mBackdropPath

    private var mTitle = MutableLiveData<String>()
    val title : LiveData<String> get() = mTitle

    private var mTagline = MutableLiveData<String>()
    val tagline : LiveData<String> get() = mTagline

    private var mOverview = MutableLiveData<String>()
    val overview : LiveData<String> get() = mOverview

    private var mVoteAverage = MutableLiveData<Double>()
    val voteAverage : LiveData<Double> get() = mVoteAverage

    private var mVoteAverageString = MutableLiveData<String>()
    val voteAverageString : LiveData<String> get() = mVoteAverageString

    private var mVoteCountString = MutableLiveData<String>()
    val voteCount : LiveData<String> get() = mVoteCountString

    private var mBudgetString = MutableLiveData<String>()
    val budget : LiveData<String> get() = mBudgetString

    private var mRevenueString = MutableLiveData<String>()
    val revenue : LiveData<String> get() = mRevenueString

    private var mActorList = MutableLiveData<List<Actor>>()
    val actorList : LiveData<List<Actor>> get() = mActorList

    private var mGenres = MutableLiveData<List<Genre>>()
    val genres : LiveData<List<Genre>> get() = mGenres

    private var mIsAdultString = MutableLiveData<String>()
    val isAdult : LiveData<String> get() = mIsAdultString

    private var mStatus = MutableLiveData<String>()
    val status : LiveData<String> get() = mStatus

    private var mHomepage = MutableLiveData<String>()
    val homepage : LiveData<String> get() = mHomepage

    private var mReleaseDate = MutableLiveData<String>()
    val releaseDate : LiveData<String> get() = mReleaseDate

    private lateinit var mRealAdapter : ActorsListAdapter
    private var mAdapter = MutableLiveData<ActorsListAdapter>()
    val adapter : LiveData<ActorsListAdapter> get() = mAdapter

    private var mIsFavorite = MutableLiveData<Boolean>()
    val isFavorite : LiveData<Boolean> get() = mIsFavorite

    init {

    }

    fun loadMovie(movieId : Int) {
        val country = "US"
        val language = "en"
        TMDbRepository().loadMovie(movieId){
            getMovieObject(it)
            //mPosterPath.postValue(it.poster_path)
            mBackdropPath.postValue(it.backdrop_path)
            mTitle.postValue(it.title)
            mTagline.postValue(it.tagline)
            mOverview.postValue(it.overview)
            mVoteAverage.postValue(it.vote_average)
            mVoteAverageString.postValue(it.vote_average.toString() + " / 10")
            mVoteCountString.postValue(it.vote_count.toString())
            mVoteCountString.postValue(it.vote_count.toString())
            mBudgetString.postValue(NumberFormat.getCurrencyInstance(Locale(language,country)).format(it.budget))
            mRevenueString.postValue(NumberFormat.getCurrencyInstance(Locale(language, country)).format(it.revenue))
            mIsAdultString.postValue( if (it.adult) "Yes" else "No")
            mHomepage.postValue(it.homepage)
            mReleaseDate.postValue(it.release_date)
            mStatus.postValue(it.status)

            TMDbRepository().loadMovieCredits(movieId){
                val actors = mutableListOf<Actor>()
                for ( c in it.cast)
                {
                    val actor = Actor (c.id, c.name, c.profile_path ?: "")
                    actors.add(actor)
                }
                mActorList.postValue(actors)
                ActorRepository(mApp).updateAllActors(actors)

                mRealAdapter = ActorsListAdapter(actors.take(15).toMutableList(), this)
                mAdapter.postValue(mRealAdapter)
            }

            val genres = mutableListOf<Genre>()
            for (g in it.genres)
            {
                val genre = Genre(g.id, g.name)
                genres.add(genre)
            }
            mGenres.postValue(genres)
            GenreRepository(mApp).updateAllGenres(genres)
        }
    }
    private fun getMovieObject(apiMovie : com.cry.tmdbclient.models.api.tmdb.obj.movie.Movie)
    {
        MovieRepository(mApp).getMovie(apiMovie.id) {
            if (it != null)
            {
                this.movie = it
            }
            else {
                var mov = Movie(
                    apiMovie.id,
                    apiMovie.title,
                    apiMovie.original_title,
                    apiMovie.tagline,
                    apiMovie.poster_path,
                    apiMovie.backdrop_path,
                    apiMovie.overview,
                    apiMovie.original_language,
                    apiMovie.popularity,
                    apiMovie.release_date,
                    apiMovie.video,
                    apiMovie.vote_average,
                    apiMovie.vote_count,
                    apiMovie.budget,
                    apiMovie.homepage,
                    apiMovie.revenue,
                    apiMovie.runtime,
                    apiMovie.status,
                    apiMovie.adult,
                    false )
                this.movie = mov
            }
        }

    }
    fun addWatchList() {

    }
    fun toggleFavorite()
    {
        movie.isFavorite = !movie.isFavorite
        mIsFavorite.postValue(movie.isFavorite)
        MovieRepository(mApp).updateMovie(movie)
    }
}