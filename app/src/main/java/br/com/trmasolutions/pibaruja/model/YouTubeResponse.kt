package br.com.trmasolutions.pibaruja.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class YouTubeResponse(@SerializedName("regionCode")
                           var regionCode: String = "",
                           @SerializedName("kind")
                           var kind: String = "",
                           @SerializedName("nextPageToken")
                           var nextPageToken: String = "",
                           @SerializedName("pageInfo")
                           var pageInfo: PageInfoVideo,
                           @SerializedName("etag")
                           var etag: String = "",
                           @SerializedName("items")
                           var items: List<YouTubeVideo>?): Parcelable