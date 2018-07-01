package br.com.trmasolutions.pibaruja.model

import com.google.gson.annotations.SerializedName

data class IdVideo(@SerializedName("kind")
                   var kind: String = "",
                   @SerializedName("videoId")
                   var videoId: String = "")