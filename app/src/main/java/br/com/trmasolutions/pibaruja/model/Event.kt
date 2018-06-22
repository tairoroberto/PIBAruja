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
        val uid: String = "",

        @ColumnInfo(name = "date")
        @SerializedName("date")
        val date: String = "",

        @ColumnInfo(name = "image")
        @SerializedName("image")
        val image: String = "",

        @ColumnInfo(name = "sponsor")
        @SerializedName("sponsor")
        val sponsor: String = "",


        @ColumnInfo(name = "contact")
        @SerializedName("contact")
        val contact: String = "",

        @ColumnInfo(name = "name")
        @SerializedName("name")
        val name: String = "",

        @ColumnInfo(name = "created_at")
        @SerializedName("created_at")
        val createdAt: String = "",

        @ColumnInfo(name = "description")
        @SerializedName("description")
        val description: String = "",

        @ColumnInfo(name = "video")
        @SerializedName("video")
        val video: String = "",

        @ColumnInfo(name = "local")
        @SerializedName("local")
        val local: String = "") : Parcelable