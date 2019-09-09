package com.cry.tmdbclient.models.api.tmdb

import com.cry.tmdbclient.models.api.tmdb.obj.discover.Discover
import com.cry.tmdbclient.models.api.tmdb.obj.movie.Movie
import com.cry.tmdbclient.models.api.tmdb.obj.movie.credits.Credits
import com.cry.tmdbclient.models.api.tmdb.obj.search.Search
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TMDbApi
{
    //Discover
    @GET("3/discover/movie?sort_by=popularity.desc")
    fun loadPopularMovies( @Query("api_key") key : String ) : Call<Discover>

    @GET("3/discover/movie?sort_by=popularity.desc")
    fun loadRecentMovies( @Query("api_key") key : String
                        , @Query("primary_release_date.gte") toDate : String
                        , @Query("primary_release_date.lte") fromDate : String) : Call<Discover>

    //Movie
    @GET("3/movie/{id}")
    fun loadMovie( @Path("id" ) id : Int
                 , @Query("api_key") key : String ) : Call<Movie>

    //Credits
    @GET("3/movie/{id}/credits")
    fun loadMovieCredits( @Path("id" ) id : Int
                        , @Query("api_key") key: String ) : Call<Credits>

    //Search
    @GET("3/search/movie")
    fun searchMovieByName( @Query("api_key") key : String
                         , @Query( "query") query : String) : Call<Search>
}