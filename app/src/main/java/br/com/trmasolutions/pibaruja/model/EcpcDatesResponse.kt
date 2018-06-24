package br.com.trmasolutions.pibaruja.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class EcpcDatesResponse(@SerializedName("success")
                             var success: Boolean = false,
                             @SerializedName("ecpc_dates")
                             var ecpcDates: List<EcpcDate>?):Parcelable