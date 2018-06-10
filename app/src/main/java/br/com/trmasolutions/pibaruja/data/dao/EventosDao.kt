package br.com.trmasolutions.pibaruja.data.dao

import android.arch.persistence.room.*
import br.com.trmasolutions.pibaruja.model.Event
import io.reactivex.Flowable

/**
 * Created by tairo on 12/12/17 3:03 PM.
 */
@Dao
interface EventosDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun add(event: Event): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addAll(jobs: List<Event>?)

    @Update
    fun update(event: Event)

    @Delete
    fun delete(event: Event)

    @Query("DELETE FROM events")
    fun deleteAll()

    @Query("SELECT * FROM events LIMIT 30")
    fun getAll(): Flowable<List<Event>>

}