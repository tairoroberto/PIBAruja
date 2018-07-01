package br.com.trmasolutions.pibaruja.model

import com.google.gson.annotations.SerializedName

data class HighVideo(@SerializedName("width")
                     var width: Int = 0,
                     @SerializedName("url")
                     var url: String = "",
                     @SerializedName("height")
                     var height: Int = 0)