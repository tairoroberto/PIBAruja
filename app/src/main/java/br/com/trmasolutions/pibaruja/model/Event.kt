package br.com.trmasolutions.pibaruja.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.PrimaryKey
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "events")
class Event(

        @SerializedName("id")
        @PrimaryKey
        @ColumnInfo(name = "id")
        var id:String,

        @SerializedName("title")
        @ColumnInfo(name = "title")
        var title:String,

        @SerializedName("description")
        @ColumnInfo(name = "description")
        var description:String,

        @SerializedName("date")
        @ColumnInfo(name = "date")
        var date:String,

        @SerializedName("image")
        @ColumnInfo(name = "image")
        var image:String,

        @SerializedName("local")
        @ColumnInfo(name = "local")
        var local:String,

        @SerializedName("sponsor")
        @ColumnInfo(name = "sponsor")
        var sponsor:String

) : Parcelable