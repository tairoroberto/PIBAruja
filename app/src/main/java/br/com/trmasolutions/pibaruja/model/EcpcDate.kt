package br.com.trmasolutions.pibaruja.model

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "ecpc_dates")
data class EcpcDate(
        @PrimaryKey
        @ColumnInfo(name = "uid")
        @SerializedName("uid")
        var uid: String = "",

        @ColumnInfo(name = "ecpc_date")
        @SerializedName("ecpc_date")
        var ecpcDate: String = "") : Parcelable