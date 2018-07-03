package br.com.trmasolutions.pibaruja.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class YouTubeVideo(@SerializedName("snippet")
                        val snippet: SnippetVideo,
                        @SerializedName("kind")
                        val kind: String = "",
                        @SerializedName("etag")
                        val etag: String = "",
                        @SerializedName("id")
                        val id: IdVideo): Parcelable