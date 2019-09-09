package com.cry.tmdbclient.models.repositories

import android.app.Application
import com.cry.tmdbclient.models.database.TMDbDatabase
import com.cry.tmdbclient.models.database.obj.Genre
import kotlinx.coroutines.*

class GenreRepository (app : Application) {
    val genreDao = TMDbDatabase.getDatabase(app).genreDao()
    //Insert
    fun insertGenre(genre : Genre) {
        GlobalScope.launch {
            genreDao.insertGenre(genre)
        }
    }
    //Update
    fun updateGenre(genre : Genre) {
        GlobalScope.launch {
            genreDao.updateGenre(genre)
        }
    }
    //Update all
    fun updateAllGenres(genres : List<Genre>) {
        GlobalScope.launch {
            for (genre in genres)
                updateGenre(genre)
        }
    }

    //Query
    fun getGenreById(id : Int, onSuccess : (Genre?) -> Unit)  {
        GlobalScope.launch {
            val genre = genreDao.getGenreById(id)
            onSuccess(genre)
        }
    }
    fun getGenres(onSuccess: (List<Genre>) -> Unit) {
        GlobalScope.launch {
            val genres = genreDao.getAllGenres()
            onSuccess(genres)
        }
    }
    //Delete
    fun deleteGenre(genre : Genre) {
        GlobalScope.launch {
            genreDao.deleteGenre(genre)
        }
    }
}