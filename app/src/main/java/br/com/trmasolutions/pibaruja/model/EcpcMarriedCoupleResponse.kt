package br.com.trmasolutions.pibaruja.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class EcpcMarriedCoupleResponse(@SerializedName("success")
                                     var success: Boolean = false,
                                     @SerializedName("ecpc")
                                     var ecpc: List<EcpcMarriedCouple>?): Parcelable