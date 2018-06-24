package br.com.trmasolutions.pibaruja.data

import android.content.Context
import br.com.trmasolutions.pibaruja.model.Event
import io.reactivex.Flowable

class EventLocalDataStore(context: Context) {

    private val eventDao = AppDatabase.getInstance(context).eventsDAO()

     fun add(event: Event): Long {
        return eventDao.add(event)
    }

    fun update(event: Event) {
        eventDao.update(event)
    }

    fun delete(event: Event) {
        eventDao.delete(event)
    }

    fun getAll(): Flowable<List<Event>> {
        return eventDao.getAll()
    }
}