package br.com.trmasolutions.pibaruja.model

import com.google.gson.annotations.SerializedName

data class User(@SerializedName("birthday")
                var birthday: String = "",
                @SerializedName("image")
                var image: String = "",
                @SerializedName("uid")
                var uid: String = "",
                @SerializedName("password")
                var password: String = "",
                @SerializedName("address")
                var address: String = "",
                @SerializedName("city")
                var city: String = "",
                @SerializedName("name")
                var name: String = "",
                @SerializedName("created_at")
                var createdAt: String = "",
                @SerializedName("email")
                var email: String = "",
                @SerializedName("cep")
                var cep: String = "")