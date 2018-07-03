package br.com.trmasolutions.pibaruja.viewmodel

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.MutableLiveData
import android.util.Log
import br.com.trmasolutions.pibaruja.data.EventLocalDataStore
import br.com.trmasolutions.pibaruja.data.EventRemoteDataStore
import br.com.trmasolutions.pibaruja.model.EventsResponse
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import org.jetbrains.anko.doAsync

class HomeViewModel(application: Application) : AndroidViewModel(application) {

    private val disposables = CompositeDisposable()
    private val eventResponse: MutableLiveData<EventsResponse> = MutableLiveData()
    private val loadingStatus = MutableLiveData<Boolean>()
    private val eventRemoteDataStore = EventRemoteDataStore()
    private val eventLocalDataStore = EventLocalDataStore(application.applicationContext)

    fun getEventResponse(): MutableLiveData<EventsResponse> {
        return eventResponse
    }

    fun getLoadingStatus(): MutableLiveData<Boolean> {
        return loadingStatus
    }

    fun getEvents() {
        disposables.add(eventRemoteDataStore.getEvents()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { loadingStatus.setValue(true) }
                .doAfterTerminate { loadingStatus.setValue(false) }
                .doOnError {
                    Log.i("TAG", "error: ${it.message}")
                    loadFromDataBase()
                }
                .subscribe(
                        { response ->
                            eventResponse.value = response
                            doAsync {
                                eventLocalDataStore.deleteAll()
                                eventLocalDataStore.addAll(response.events)
                            }
                        },
                        { throwable ->
                            Log.i("TAG", "error: ${throwable.message}")
                            loadFromDataBase()
                        }
                )
        )
    }

    private fun loadFromDataBase() {
        disposables.add(eventLocalDataStore.getAll()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { loadingStatus.setValue(true) }
                .doAfterTerminate { loadingStatus.setValue(false) }
                .doOnError {
                    Log.i("TAG", "error: ${it.message}")
                }
                .subscribe(
                        { response ->
                            eventResponse.value = EventsResponse(true, response)
                        },
                        { throwable ->
                            Log.i("TAG", "error: ${throwable.message}")
                        }
                )
        )
    }
}
