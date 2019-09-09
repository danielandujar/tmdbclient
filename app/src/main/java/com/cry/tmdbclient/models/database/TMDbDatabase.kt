package com.cry.tmdbclient.models.database

import android.app.Application
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.cry.tmdbclient.models.database.dao.ActorDao
import com.cry.tmdbclient.models.database.dao.GenreDao
import com.cry.tmdbclient.models.database.dao.MovieDao
import com.cry.tmdbclient.models.database.dao.WatchListDao
import com.cry.tmdbclient.models.database.obj.Actor
import com.cry.tmdbclient.models.database.obj.Genre
import com.cry.tmdbclient.models.database.obj.Movie
import com.cry.tmdbclient.models.database.obj.WatchList

@Database(entities = [Movie::class, Genre::class, Actor::class, WatchList::class], version = 1)
@TypeConverters(Converters::class)
abstract class TMDbDatabase : RoomDatabase(){
    abstract fun actorDao() : ActorDao
    abstract fun genreDao() : GenreDao
    abstract fun movieDao() : MovieDao
    abstract fun watchListDao() : WatchListDao

    companion object {
        private const val DATABASE_NAME = "tmdb_client_db"
        private var INSTANCE: TMDbDatabase? = null

        fun getDatabase(context : Application) : TMDbDatabase {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: buildDatabase(context).also { INSTANCE = it }
            }
        }

        private fun buildDatabase(context: Context): TMDbDatabase {
            return Room.databaseBuilder(context.applicationContext, TMDbDatabase::class.java, DATABASE_NAME)
                //.fallbackToDestructiveMigration()
                .build()
        }
    }}