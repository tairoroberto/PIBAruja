package br.com.trmasolutions.pibaruja.model

import com.google.gson.annotations.SerializedName

data class EventItem(@SerializedName("date")
                      val date: String = "",
                     @SerializedName("image")
                      val image: String = "",
                     @SerializedName("sponsor")
                      val sponsor: String = "",
                     @SerializedName("uid")
                      val uid: String = "",
                     @SerializedName("contact")
                      val contact: String = "",
                     @SerializedName("name")
                      val name: String = "",
                     @SerializedName("created_at")
                      val createdAt: String = "",
                     @SerializedName("description")
                      val description: String = "",
                     @SerializedName("video")
                      val video: String = "",
                     @SerializedName("local")
                      val local: String = "")