package com.cry.tmdbclient.models.database.dao

import androidx.room.*
import com.cry.tmdbclient.models.database.DatabaseContract.INT_TRUE
import com.cry.tmdbclient.models.database.DatabaseContract.TableMovie.COLUMN_IS_FAVORITE
import com.cry.tmdbclient.models.database.DatabaseContract.TableMovie.COLUMN_MOVIE_ID
import com.cry.tmdbclient.models.database.DatabaseContract.TableMovie.COLUMN_POPULARITY
import com.cry.tmdbclient.models.database.DatabaseContract.TableMovie.TABLE_NAME
import com.cry.tmdbclient.models.database.obj.Movie

@Dao
interface MovieDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovie(movie: Movie)

    @Update
    fun updateMovie(vararg movie: Movie)

    @Delete
    fun deleteMovie(vararg movie: Movie)

    @Query("SELECT * FROM $TABLE_NAME WHERE $COLUMN_MOVIE_ID = :movieId ")
    fun getMovieById(movieId: Int): Movie?

    @Query("SELECT * FROM $TABLE_NAME ORDER BY $COLUMN_POPULARITY DESC")
    fun getPopularMovies(): List<Movie>

    @Query("SELECT * FROM $TABLE_NAME WHERE $COLUMN_IS_FAVORITE = $INT_TRUE")
    fun getFavoriteMovies() : List<Movie>

    @Query("SELECT * FROM $TABLE_NAME WHERE $COLUMN_MOVIE_ID IN (:movieId) ")
    fun getMoviesById(movieId: List<Int>): List<Movie>

}