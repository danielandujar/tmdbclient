package com.cry.tmdbclient.models.database.dao

import androidx.room.*
import com.cry.tmdbclient.models.database.DatabaseContract.TableGenre.COLUMN_GENRE_ID
import com.cry.tmdbclient.models.database.DatabaseContract.TableGenre.TABLE_NAME
import com.cry.tmdbclient.models.database.obj.Genre

@Dao
interface GenreDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertGenre(genre: Genre)

    @Update
    fun updateGenre(vararg genre: Genre)

    @Delete
    fun deleteGenre(vararg genre: Genre)

    @Query("SELECT * FROM $TABLE_NAME WHERE $COLUMN_GENRE_ID = :genreId ")
    fun getGenreById(genreId: Int): Genre?

    @Query("SELECT * FROM $TABLE_NAME")
    fun getAllGenres(): List<Genre>
}