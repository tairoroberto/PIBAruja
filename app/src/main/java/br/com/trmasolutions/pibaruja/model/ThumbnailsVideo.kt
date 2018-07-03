package br.com.trmasolutions.pibaruja.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ThumbnailsVideo(@SerializedName("default")
                           var default: DefaultVideo,
                           @SerializedName("high")
                           var high: HighVideo,
                           @SerializedName("medium")
                           var medium: MediumVideo): Parcelable