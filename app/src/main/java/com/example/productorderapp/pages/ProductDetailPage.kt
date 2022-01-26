package com.example.productorderapp.pages

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.bumptech.glide.Glide
import com.example.productorderapp.databinding.ActivityProductDetailPageBinding
import com.example.productorderapp.pages.models.ProductOrder
import com.example.productorderapp.pages.objects.UserInfo
import com.example.productorderapp.pages.services.Service
import com.example.productorderapp.pages.utils.ApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProductDetailPage : AppCompatActivity() {
    private lateinit var binding: ActivityProductDetailPageBinding

        override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProductDetailPageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        title="Product Detail Page"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val name =intent.getStringExtra("productName")
        val price = intent.getStringExtra("productPrice")
        val image = intent.getStringExtra("productImage")
        val description = intent.getStringExtra("description")

        Glide.with(this).load(image).into(binding.pDetailImage)
        binding.pDetailName.text = name
        binding.pDetailPrice.text ="Price:" + price+ "TL"
        binding.pDetailDesc.text ="Description:"+description

            
            val service = ApiClient.getClient().create(Service::class.java)
            val productService = service.order()

            binding.btnAdd.setOnClickListener {
                productService.enqueue(object : Callback<ProductOrder> {
                    override fun onResponse(
                        call: Call<ProductOrder>,
                        response: Response<ProductOrder>
                    ) {
                        if (response.isSuccessful) {
                            val orderStatus = response.body()!!.order!!.get(0)!!.durum

                            if (orderStatus == true) {

                                val i = Intent(applicationContext, ProductActivity::class.java)
                                startActivity(i)
                                finish()
                            } else {
                                Toast.makeText(applicationContext, "Siparişiniz oluşturulurken hata ile karşılanıldı! "+ response.body()!!.order!!.get(0)!!.mesaj, Toast.LENGTH_SHORT).show()
                            }
                        }else{
                            Toast.makeText(applicationContext, "Sunucuya erişilemedi..", Toast.LENGTH_SHORT).show()
                        }
                    }

                    override fun onFailure(call: Call<ProductOrder>, t: Throwable) {
                        Toast.makeText(
                            applicationContext,
                            "Siparişiniz Alınamadı!!",
                            Toast.LENGTH_SHORT
                        ).show()
                    }

                })
            }

    }
}