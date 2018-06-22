package br.com.trmasolutions.pibaruja.model

import com.google.gson.annotations.SerializedName

data class EventsResponse(@SerializedName("success")
                          var success: Boolean = false,
                          @SerializedName("events")
                          var events: List<Event>?)