package com.example.productorderapp.pages.models


import com.google.gson.annotations.SerializedName

data class ProductData(
    @SerializedName("Products")
    val products: List<Product?>?
)
    data class Product(
        @SerializedName("bilgiler")
        val bilgiler: List<BilgilerData?>?,
        @SerializedName("durum")
        val durum: Boolean?,
        @SerializedName("mesaj")
        val mesaj: String?,
        @SerializedName("total")
        val total: String?
    )

        data class BilgilerData(
            @SerializedName("brief")
            val brief: String? =null,
            @SerializedName("campaign")
            val campaign: Campaign?=null,
            @SerializedName("campaignDescription")
            val campaignDescription: String?=null,
            @SerializedName("campaignTitle")
            val campaignTitle: String?=null,
            @SerializedName("categories")
            val categories: List<Category?>?=null,
            @SerializedName("description")
            val description: String?=null,
            @SerializedName("image")
            val image: Boolean?=null,
            @SerializedName("images")
            val images: List<İmage?>?=null,
            @SerializedName("likes")
            val likes: Likes? =null ,
            @SerializedName("price")
            val price: String?=null,
            @SerializedName("productId")
            val productId: String?=null,
            @SerializedName("productName")
            val productName: String?=null,
            @SerializedName("saleInformation")
            val saleInformation: SaleInformation?=null
        )

            data class Campaign(
                @SerializedName("campaignType")
                val campaignType: String?,
                @SerializedName("campaignTypeId")
                val campaignTypeId: String?
            )

            data class Category(
                @SerializedName("categoryId")
                val categoryId: String?,
                @SerializedName("categoryName")
                val categoryName: String?
            )

            data class İmage(
                @SerializedName("normal")
                val normal: String?,
                @SerializedName("thumb")
                val thumb: String?
            )

            data class Likes(
                @SerializedName("dislike")
                val dislike: Int?,
                @SerializedName("like")
                val like: Like?
            )
                data class Like(
                    @SerializedName("ortalama")
                    val ortalama: String?,
                    @SerializedName("oy_toplam")
                    val oyToplam: String?
                )


            data class SaleInformation(
                @SerializedName("saleType")
                val saleType: String?,
                @SerializedName("saleTypeId")
                val saleTypeId: String?
            )