package com.cry.tmdbclient.models.database.dao

import androidx.room.*
import com.cry.tmdbclient.models.database.DatabaseContract
import com.cry.tmdbclient.models.database.DatabaseContract.TableWatchList.COLUMN_WATCHLIST_ID
import com.cry.tmdbclient.models.database.DatabaseContract.TableWatchList.TABLE_NAME
import com.cry.tmdbclient.models.database.DatabaseContract.TableWatchList.COLUMN_MOVIE_ID
import com.cry.tmdbclient.models.database.obj.Movie
import com.cry.tmdbclient.models.database.obj.WatchList

@Dao
interface WatchListDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertWatchList(watchList: WatchList)

    @Update
    fun updateWatchList(vararg watchList: WatchList)

    @Delete
    fun deleteWatchList(vararg watchList: WatchList)

    @Query("SELECT * FROM $TABLE_NAME WHERE $COLUMN_WATCHLIST_ID = :watchListId ")
    fun getWatchListById(watchListId: Int): WatchList?

    @Query("SELECT * FROM $TABLE_NAME")
    fun getWatchList(): List<WatchList>

    @Query("SELECT * FROM $TABLE_NAME WHERE $COLUMN_MOVIE_ID = :movieId ")
    fun getWatchListByMovieId(movieId: Int): WatchList?

}