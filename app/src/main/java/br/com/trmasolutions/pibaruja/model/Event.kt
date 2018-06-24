package br.com.trmasolutions.pibaruja.model

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "events")
data class Event(

        @PrimaryKey
        @ColumnInfo(name = "id")
        @SerializedName("uid")
        var uid: String = "",

        @ColumnInfo(name = "name")
        @SerializedName("name")
        var name: String = "",

        @ColumnInfo(name = "description")
        @SerializedName("description")
        var description: String = "",

        @ColumnInfo(name = "date")
        @SerializedName("date")
        var date: String = "",

        @ColumnInfo(name = "image")
        @SerializedName("image")
        var image: String = "",

        @ColumnInfo(name = "video")
        @SerializedName("video")
        var video: String = "",

        @ColumnInfo(name = "local")
        @SerializedName("local")
        var local: String = "",

        @ColumnInfo(name = "sponsor")
        @SerializedName("sponsor")
        var sponsor: String = "",

        @ColumnInfo(name = "contact")
        @SerializedName("contact")
        var contact: String = "",

        @ColumnInfo(name = "created_at")
        @SerializedName("created_at")
        var createdAt: String = "") : Parcelable