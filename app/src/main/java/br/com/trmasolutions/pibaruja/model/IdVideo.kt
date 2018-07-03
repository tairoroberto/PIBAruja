package br.com.trmasolutions.pibaruja.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class IdVideo(@SerializedName("kind")
                   var kind: String = "",
                   @SerializedName("videoId")
                   var videoId: String = ""): Parcelable