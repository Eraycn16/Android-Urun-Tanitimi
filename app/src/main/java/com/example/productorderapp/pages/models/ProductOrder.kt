package com.example.productorderapp.pages.models


import com.google.gson.annotations.SerializedName

data class ProductOrder(
    @SerializedName("order")
    val order: List<Order?>?
)

    data class Order(
        @SerializedName("durum")
        val durum: Boolean?,
        @SerializedName("mesaj")
        val mesaj: String?
    )
