package com.cry.tmdbclient.models.database.obj

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.cry.tmdbclient.models.database.DatabaseContract.TableWatchList.COLUMN_DATE_ADDED
import com.cry.tmdbclient.models.database.DatabaseContract.TableWatchList.COLUMN_IS_REMOVED
import com.cry.tmdbclient.models.database.DatabaseContract.TableWatchList.COLUMN_MOVIE_ID
import com.cry.tmdbclient.models.database.DatabaseContract.TableWatchList.COLUMN_WATCHLIST_ID
import com.cry.tmdbclient.models.database.DatabaseContract.TableWatchList.TABLE_NAME
import org.threeten.bp.Instant

@Entity(tableName = TABLE_NAME)
data class WatchList (
    @PrimaryKey @NonNull @ColumnInfo(name= COLUMN_WATCHLIST_ID)
    var id: Int,
    @ColumnInfo(name= COLUMN_MOVIE_ID)
    var movieId : Int,
    @ColumnInfo(name= COLUMN_DATE_ADDED)
    var dateAdded : Instant,
    @ColumnInfo(name= COLUMN_IS_REMOVED)
    var isRemoved : Boolean
)