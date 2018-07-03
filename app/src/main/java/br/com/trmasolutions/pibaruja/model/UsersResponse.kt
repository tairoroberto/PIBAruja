package br.com.trmasolutions.pibaruja.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class UsersResponse(@SerializedName("success")
                         var success: Boolean = false,
                         @SerializedName("users")
                         var users: List<User>?): Parcelable