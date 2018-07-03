package br.com.trmasolutions.pibaruja.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class PageInfoVideo(@SerializedName("totalResults")
                         var totalResults: Int = 0,
                         @SerializedName("resultsPerPage")
                         var resultsPerPage: Int = 0): Parcelable