package com.cry.tmdbclient.models.database.obj

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.cry.tmdbclient.models.database.DatabaseContract.TableGenre.COLUMN_GENRE_ID
import com.cry.tmdbclient.models.database.DatabaseContract.TableGenre.COLUMN_GENRE_NAME
import com.cry.tmdbclient.models.database.DatabaseContract.TableGenre.TABLE_NAME

@Entity(tableName = TABLE_NAME)
data class Genre(
    @PrimaryKey @NonNull @ColumnInfo(name= COLUMN_GENRE_ID)
    var id : Int,
    @ColumnInfo(name= COLUMN_GENRE_NAME)
    var name : String
)