package com.cry.tmdbclient.models.api.tmdb

import android.util.Log
import com.cry.tmdbclient.core.utils.Constants.TMDB_API_KEY
import com.cry.tmdbclient.core.utils.Constants.TMDB_API_URL
import com.cry.tmdbclient.models.api.tmdb.obj.discover.Discover
import com.cry.tmdbclient.models.api.tmdb.obj.movie.Movie
import com.cry.tmdbclient.models.api.tmdb.obj.movie.credits.Credits
import com.cry.tmdbclient.models.api.tmdb.obj.search.Search
import com.google.gson.GsonBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*

class TMDbClient
{
    //Discover/Movies
    fun getPopularMovies(onSuccess : (Discover) -> Unit) {
        val gson = GsonBuilder()
            .setLenient()
            .create()

        val retrofit = Retrofit.Builder()
            .baseUrl(TMDB_API_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()

        val tmDbApi = retrofit.create(TMDbApi::class.java)
        val call = tmDbApi.loadPopularMovies(TMDB_API_KEY)

        call.enqueue(object : Callback<Discover>{
            override fun onFailure(call: Call<Discover>?, t: Throwable?) {
                Log.e("retrofit", t!!.message)
            }

            override fun onResponse(call: Call<Discover>?, response: Response<Discover>?) {
                onSuccess(response!!.body()!!)
            }
        })
    }

    fun getRecentMovies(onSuccess : (Discover) -> Unit) {
        val gson = GsonBuilder()
            .setLenient()
            .create()

        val retrofit = Retrofit.Builder()
            .baseUrl(TMDB_API_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()

        val tmDbApi = retrofit.create(TMDbApi::class.java)
        var year = Calendar.getInstance().get(Calendar.YEAR)
        var month = Calendar.getInstance().get(Calendar.MONTH)
        var day = Calendar.getInstance().get(Calendar.DAY_OF_MONTH)

        var fromDate = "" + year + "-" + String.format("%02d",month-1) + "-" + String.format("%02d",1)
        var toDate = "" + year + "-" + String.format("%02d",month) + "-" + String.format("%02d",day)
        val call = tmDbApi.loadRecentMovies(TMDB_API_KEY, fromDate, toDate)

        call.enqueue(object : Callback<Discover> {
            override fun onFailure(call: Call<Discover>?, t: Throwable?) {
                Log.e("retrofit", t!!.message)
            }

            override fun onResponse(call: Call<Discover>?, response: Response<Discover>?) {
                onSuccess(response!!.body()!!)
            }
        })
    }

    //Movie
    fun loadMovie(movieId : Int, onSuccess: (Movie) -> Unit)
    {
        val gson = GsonBuilder()
            .setLenient()
            .create()

        val retrofit = Retrofit.Builder()
            .baseUrl(TMDB_API_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()

        val tmDbApi = retrofit.create(TMDbApi::class.java)
        val call = tmDbApi.loadMovie(movieId, TMDB_API_KEY)

        call.enqueue(object : Callback<Movie> {
            override fun onFailure(call: Call<Movie>?, t: Throwable?) {
                Log.e("retrofit", t!!.message)
            }

            override fun onResponse(call: Call<Movie>?, response: Response<Movie>?) {
                onSuccess(response!!.body()!!)
            }
        })
    }

    //Movie/Credits
    fun loadMovieCredits(movieId : Int, onSuccess: (Credits) -> Unit)
    {
        val gson = GsonBuilder()
            .setLenient()
            .create()

        val retrofit = Retrofit.Builder()
            .baseUrl(TMDB_API_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()

        val tmDbApi = retrofit.create(TMDbApi::class.java)

        val call = tmDbApi.loadMovieCredits(movieId, TMDB_API_KEY)

        call.enqueue(object : Callback<Credits>{
            override fun onFailure(call: Call<Credits>?, t: Throwable?) {
                Log.e("retrofit", t!!.message)
            }

            override fun onResponse(call: Call<Credits>?, response: Response<Credits>?) {
                onSuccess(response!!.body()!!)
            }
        })
    }

    //Search
    fun searchMovieByName(query : String, onSuccess: (Search) -> Unit)
    {
        val gson = GsonBuilder()
            .setLenient()
            .create()

        val retrofit = Retrofit.Builder()
            .baseUrl(TMDB_API_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()

        val tmDbApi = retrofit.create(TMDbApi::class.java)
        val call = tmDbApi.searchMovieByName(TMDB_API_KEY, query)

        call.enqueue(object : Callback<Search>{
            override fun onFailure(call: Call<Search>?, t: Throwable?) {
                Log.e("retrofit", t!!.message)
            }

            override fun onResponse(call: Call<Search>?, response: Response<Search>?) {
                onSuccess(response!!.body()!!)
            }
        })
    }

}