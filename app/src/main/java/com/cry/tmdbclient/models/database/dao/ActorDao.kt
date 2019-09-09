package com.cry.tmdbclient.models.database.dao

import androidx.room.*
import com.cry.tmdbclient.models.database.DatabaseContract.TableActor.COLUMN_ACTOR_ID
import com.cry.tmdbclient.models.database.DatabaseContract.TableActor.TABLE_NAME
import com.cry.tmdbclient.models.database.obj.Actor

@Dao
interface ActorDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertActor(actor: Actor)

    @Update
    fun updateActor(vararg actor: Actor)

    @Delete
    fun deleteActor(vararg actor: Actor)

    @Query("SELECT * FROM $TABLE_NAME WHERE $COLUMN_ACTOR_ID = :actorId ")
    fun getActorById(actorId: Int): Actor?
}