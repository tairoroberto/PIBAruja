package br.com.trmasolutions.pibaruja.model

import com.google.gson.annotations.SerializedName

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
                        var liveBroadcastContent: String = "")