package br.com.trmasolutions.pibaruja.model

import com.google.gson.annotations.SerializedName

data class ThumbnailsVideo(@SerializedName("default")
                           var default: DefaultVideo,
                           @SerializedName("high")
                           var high: HighVideo,
                           @SerializedName("medium")
                           var medium: MediumVideo)