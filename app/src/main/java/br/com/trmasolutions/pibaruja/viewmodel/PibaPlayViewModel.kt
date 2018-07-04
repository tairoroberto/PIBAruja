package br.com.trmasolutions.pibaruja.viewmodel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.util.Log
import br.com.trmasolutions.pibaruja.data.YouTubeRemoteDataStore
import br.com.trmasolutions.pibaruja.model.YouTubeResponse
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class PibaPlayViewModel : ViewModel() {

    private val disposables = CompositeDisposable()
    private val eventResponse: MutableLiveData<YouTubeResponse> = MutableLiveData()
    private val loadingStatus = MutableLiveData<Boolean>()
    private val youtubeDataStore = YouTubeRemoteDataStore()

    fun getEventResponse(): MutableLiveData<YouTubeResponse> {
        return eventResponse
    }

    fun getLoadingStatus(): MutableLiveData<Boolean> {
        return loadingStatus
    }

    fun getYouTubeVideos(channelId: String, order: String, part: String, pageToken: String, key: String) {
        disposables.add(youtubeDataStore.getYouTubeVideos(channelId, order, part, pageToken, key)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { loadingStatus.setValue(true) }
                .doAfterTerminate { loadingStatus.setValue(false) }
                .doOnError {
                    Log.i("TAG", "error: ${it.message}")
                }
                .subscribe(
                        { response ->
                            eventResponse.value = response
                        },
                        { throwable ->
                            Log.i("TAG", "error: ${throwable.message}")
                        }
                )
        )
    }
}
