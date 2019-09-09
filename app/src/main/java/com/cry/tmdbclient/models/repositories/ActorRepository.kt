package com.cry.tmdbclient.models.repositories

import android.app.Application
import com.cry.tmdbclient.models.database.TMDbDatabase
import com.cry.tmdbclient.models.database.obj.Actor
import kotlinx.coroutines.*

class ActorRepository (app : Application) {
    val actorDao = TMDbDatabase.getDatabase(app).actorDao()
    //Insert
    fun insertActor(actor : Actor) {
        GlobalScope.launch {
            actorDao.insertActor(actor)
        }
    }
    //Update
    fun updateActor(actor : Actor) {
        GlobalScope.launch {
            actorDao.updateActor(actor)
        }
    }
    //Update All
    fun updateAllActors (actors : List<Actor>) {
        GlobalScope.launch {
            for (actor in actors) {
                actorDao.updateActor(actor)
            }
        }
    }
    //Delete
    fun deleteActor(actor : Actor ) {
        GlobalScope.launch {
            actorDao.deleteActor(actor)
        }
    }
    //Query
    fun getActor(id : Int, onSuccess : (Actor?) -> Unit) {
        GlobalScope.launch {
            val actor = actorDao.getActorById(id)
            onSuccess(actor)
        }
    }
}