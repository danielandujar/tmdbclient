package com.cry.tmdbclient.models.repositories

import android.app.Application
import com.cry.tmdbclient.models.database.TMDbDatabase
import com.cry.tmdbclient.models.database.obj.WatchList
import kotlinx.coroutines.*

class WatchListRepository(app : Application) {
    val wlDao = TMDbDatabase.getDatabase(app).watchListDao()
    //Insert
    fun insertWatchList(watchList: WatchList) {
        GlobalScope.launch {
            wlDao.insertWatchList(watchList)
        }
    }
    //Update
    fun updateWatchList(watchList: WatchList) {
        GlobalScope.launch {
            wlDao.updateWatchList(watchList)
        }
    }
    //Delete
    fun deletefromWatchList(watchList: WatchList) {
        GlobalScope.launch {
            wlDao.deleteWatchList(watchList)
        }
    }
    //Query
    fun queryWatchList(onSuccess : (List<WatchList>) -> Unit) {
        GlobalScope.launch {
            val list = wlDao.getWatchList()
            onSuccess(list)
        }
    }
    fun getWatchListByMovieId(movieId : Int, onSuccess : (WatchList?) -> Unit )
    {
        GlobalScope.launch {
            val watchlist = wlDao.getWatchListByMovieId(movieId)
            onSuccess(watchlist)
        }
    }

}