package br.com.trmasolutions.pibaruja.ui.event

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.MutableLiveData
import android.graphics.Bitmap
import android.util.Log
import br.com.trmasolutions.pibaruja.data.EventLocalDataStore
import br.com.trmasolutions.pibaruja.data.EventRemoteDataStore
import br.com.trmasolutions.pibaruja.model.DefaultResponse
import br.com.trmasolutions.pibaruja.model.Event
import com.google.firebase.storage.FirebaseStorage
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import java.io.ByteArrayOutputStream

class EventViewModel(application: Application) : AndroidViewModel(application) {

    private val disposables = CompositeDisposable()
    private val eventResponse : MutableLiveData<DefaultResponse> = MutableLiveData()
    private val loadingStatus = MutableLiveData<Boolean>()
    private val eventRemoteDataStore = EventRemoteDataStore()
    private val eventLocalDataStore = EventLocalDataStore(application.applicationContext)
    private var storage = FirebaseStorage.getInstance()
    private lateinit var pathImage: String

    companion object {
        val IMAGE_REF_STORAGE = "images/event-${System.currentTimeMillis()}.jpg"
    }

    fun getEventResponse(): MutableLiveData<DefaultResponse> {
        return  eventResponse
    }

    fun getLoadingStatus(): MutableLiveData<Boolean> {
        return loadingStatus
    }

    fun saveEvent(event: Event) {
        disposables.add(eventRemoteDataStore.setEvent(event)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { loadingStatus.setValue(true) }
                .doAfterTerminate { loadingStatus.setValue(false) }
                .doOnError {
                    Log.i("TAG", "error: ${it.message}")
                }
                .subscribe(
                        { response ->
                            if (response.success){
                                eventResponse.value = response
                                return@subscribe
                            }

                            Log.i("TAG", "error: ${response.message}")
                            deleteImageFromStorage()
                        },
                        { throwable ->
                            Log.i("TAG", "error: ${throwable.message}")
                            deleteImageFromStorage()
                        }
                )
        )
    }

    fun uploadImageToStorage(bitmap: Bitmap?, event: Event) {
        val baos = ByteArrayOutputStream()
        bitmap?.compress(Bitmap.CompressFormat.JPEG, 80, baos)
        val bitmapData = baos.toByteArray()

        val imagesRef = storage.reference.child(IMAGE_REF_STORAGE)
        val uploadTask = imagesRef.putBytes(bitmapData)

        if (bitmap != null){
            uploadTask
                    .addOnFailureListener { exception ->
                        Log.i("TAG", "error: ${exception.message}")
                    }
                    .addOnSuccessListener {
                        imagesRef.downloadUrl.addOnSuccessListener {
                            pathImage = it.toString()
                            event.image = it.toString()
                            saveEvent(event)
                        }
                    }
        }else {
            saveEvent(event)
        }
    }

    private fun deleteImageFromStorage(){
        val imagesRef = storage.reference.child(IMAGE_REF_STORAGE)
        imagesRef.delete().addOnSuccessListener {
            Log.i("TAG", "$IMAGE_REF_STORAGE: deletado com sucesso")
        }.addOnFailureListener {exception ->
            Log.i("TAG", "error: ${exception.message}")
        }
    }
}
