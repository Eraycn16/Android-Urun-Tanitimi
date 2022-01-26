package com.example.productorderapp.pages.services

import com.example.productorderapp.pages.models.ProductData
import com.example.productorderapp.pages.models.ProductOrder
import com.example.productorderapp.pages.models.UserLogin
import com.example.productorderapp.pages.models.UserRegister
import com.example.productorderapp.pages.objects.UserInfo
import com.example.productorderapp.pages.utils.ApiClient
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface Service {


    @GET("userLogin.php")
    fun userLogin(
        @Query("userEmail") userEmail:String,
        @Query("userPass") userPass:String,
        @Query("ref") ref:String = ApiClient.ref,
        @Query("face") face:String = "no"
    ): Call<UserLogin>

    @GET("userRegister.php")
    fun userRegister(
        @Query("userName") userName:String,
        @Query("userSurname") userSurname:String,
        @Query("userMail") userEmail:String,
        @Query("userPass") userPass:String,
        @Query("userPhone") userPhone: String,
        @Query("ref") ref:String = ApiClient.ref
    ): Call<UserRegister>

    @GET("product.php")
    fun getProduct(
        @Query("start") start:String = "0",
        @Query("ref") ref:String = ApiClient.ref
    ): Call<ProductData>


    @GET("orderForm.php")
    fun order(
        @Query("customerId") customerId:String = UserInfo.userId,
        @Query("productId") productId:String = UserInfo.productId,
        @Query("html") html:String = UserInfo.productId,
        @Query("ref") ref:String = ApiClient.ref
    ): Call<ProductOrder>

}