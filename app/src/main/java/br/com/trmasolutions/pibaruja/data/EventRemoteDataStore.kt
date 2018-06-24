package br.com.trmasolutions.pibaruja.data

import br.com.trmasolutions.pibaruja.domain.services.network.RemoteApiService
import br.com.trmasolutions.pibaruja.model.DefaultResponse
import br.com.trmasolutions.pibaruja.model.Event
import br.com.trmasolutions.pibaruja.model.EventsResponse
import io.reactivex.Single

class EventRemoteDataStore {
    fun getEvents(): Single<EventsResponse> {
        return RemoteApiService.getInstance().getApiService().getEvents()
    }

    fun setEvent(event: Event): Single<DefaultResponse> {
        return RemoteApiService.getInstance().getApiService().setEvent(event)
    }

    fun updateEvent(event: Event): Single<DefaultResponse> {
        return RemoteApiService.getInstance().getApiService().updateEvent(event)
    }

    fun deleteEvent(event: Event): Single<DefaultResponse> {
        return RemoteApiService.getInstance().getApiService().deleteEvent(event)
    }
}