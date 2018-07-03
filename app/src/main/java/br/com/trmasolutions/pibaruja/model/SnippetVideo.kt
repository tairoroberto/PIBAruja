package br.com.trmasolutions.pibaruja.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class SnippetVideo(@SerializedName("publishedAt")
                        var publishedAt: String = "",
                        @SerializedName("description")
                        var description: String = "",
                        @SerializedName("title")
                        var title: String = "",
                        @SerializedName("thumbnails")
                        var thumbnails: ThumbnailsVideo,
                        @SerializedName("channelId")
                        var channelId: String = "",
                        @SerializedName("channelTitle")
                        var channelTitle: String = "",
                        @SerializedName("liveBroadcastContent")
                        var liveBroadcastContent: String = ""): Parcelable