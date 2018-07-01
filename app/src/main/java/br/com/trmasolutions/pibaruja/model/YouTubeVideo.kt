package br.com.trmasolutions.pibaruja.model

import com.google.gson.annotations.SerializedName

data class YouTubeVideo(@SerializedName("snippet")
                        val snippet: SnippetVideo,
                        @SerializedName("kind")
                        val kind: String = "",
                        @SerializedName("etag")
                        val etag: String = "",
                        @SerializedName("id")
                        val id: IdVideo)