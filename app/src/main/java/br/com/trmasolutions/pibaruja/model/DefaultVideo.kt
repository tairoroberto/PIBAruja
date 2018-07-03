package br.com.trmasolutions.pibaruja.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class DefaultVideo(@SerializedName("width")
                        var width: Int = 0,
                        @SerializedName("url")
                        var url: String = "",
                        @SerializedName("height")
                        var height: Int = 0): Parcelable