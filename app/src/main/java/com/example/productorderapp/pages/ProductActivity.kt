package com.example.productorderapp.pages

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.productorderapp.databinding.ActivityProductBinding
import com.example.productorderapp.pages.adapter.ProductAdapter
import com.example.productorderapp.pages.models.BilgilerData
import com.example.productorderapp.pages.models.ProductData
import com.example.productorderapp.pages.objects.UserInfo
import com.example.productorderapp.pages.services.Service
import com.example.productorderapp.pages.utils.ApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProductActivity : AppCompatActivity() {
    private lateinit var binding: ActivityProductBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProductBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val service = ApiClient.getClient().create(Service::class.java)
        val productService = service.getProduct()

        var product: List<BilgilerData>

        productService.enqueue(object : Callback<ProductData> {
            override fun onResponse(call: Call<ProductData>, response: Response<ProductData>) {
                if (response.isSuccessful) {

                    val productList = response.body()!!.products
                    val status = productList!!.get(0)!!.durum

                    if (status == true) {

                        product = productList.get(0)!!.bilgiler as List<BilgilerData>
                        showData(product)
                    } else {
                        Toast.makeText(applicationContext, "Veriler yüklenemedi..", Toast.LENGTH_SHORT).show()
                    }
                }else{
                    Toast.makeText(applicationContext, "Response Error", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<ProductData>, t: Throwable) {
                Toast.makeText(applicationContext, "Lütfen internet bağlantınızı kontrol ediniz!!", Toast.LENGTH_SHORT).show()
            }

        })

    }

    private fun showData(productList: List<BilgilerData?>?) {

        var productAdapter = ProductAdapter(this, productList as List<BilgilerData>)
        binding.listProduct.adapter = productAdapter
        }


    }


