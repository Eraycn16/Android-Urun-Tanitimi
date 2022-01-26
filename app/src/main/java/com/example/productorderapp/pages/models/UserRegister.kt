package com.example.productorderapp.pages.models


import com.google.gson.annotations.SerializedName

data class UserRegister(
    @SerializedName("user")
    val user: List<UserData?>?
)

    data class UserData(
        @SerializedName("durum")
        val durum: Boolean?,
        @SerializedName("kullaniciId")
        val kullaniciId: String?,
        @SerializedName("mesaj")
        val mesaj: String?
    )
