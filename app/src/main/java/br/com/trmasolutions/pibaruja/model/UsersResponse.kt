package br.com.trmasolutions.pibaruja.model

import com.google.gson.annotations.SerializedName

data class UsersResponse(@SerializedName("success")
                         var success: Boolean = false,
                         @SerializedName("users")
                         var users: List<User>?)