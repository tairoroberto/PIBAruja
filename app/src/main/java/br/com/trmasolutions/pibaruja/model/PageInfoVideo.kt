package br.com.trmasolutions.pibaruja.model

import com.google.gson.annotations.SerializedName

data class PageInfoVideo(@SerializedName("totalResults")
                         var totalResults: Int = 0,
                         @SerializedName("resultsPerPage")
                         var resultsPerPage: Int = 0)