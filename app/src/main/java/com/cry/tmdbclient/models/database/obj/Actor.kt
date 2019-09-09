package com.cry.tmdbclient.models.database.obj

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.cry.tmdbclient.models.database.DatabaseContract.TableActor.COLUMN_ACTOR_ID
import com.cry.tmdbclient.models.database.DatabaseContract.TableActor.COLUMN_ACTOR_NAME
import com.cry.tmdbclient.models.database.DatabaseContract.TableActor.COLUMN_PROFILE_PATH
import com.cry.tmdbclient.models.database.DatabaseContract.TableActor.TABLE_NAME

@Entity (tableName = TABLE_NAME)
data class Actor  (
    @PrimaryKey @NonNull @ColumnInfo(name= COLUMN_ACTOR_ID)
    var id: Int,
    @ColumnInfo(name= COLUMN_ACTOR_NAME)
    var name: String,
    @ColumnInfo(name= COLUMN_PROFILE_PATH)
    var profile_path: String
)