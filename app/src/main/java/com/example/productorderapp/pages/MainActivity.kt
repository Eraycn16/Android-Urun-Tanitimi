package com.example.productorderapp.pages

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.service.autofill.UserData
import android.widget.Toast
import com.bumptech.glide.Glide
import com.example.productorderapp.R
import com.example.productorderapp.databinding.ActivityMainBinding
import com.example.productorderapp.pages.models.UserLogin
import com.example.productorderapp.pages.objects.UserInfo
import com.example.productorderapp.pages.services.Service
import com.example.productorderapp.pages.utils.ApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val uri = "https://www.ideasoft.com.tr/wp-content/uploads/2021/10/hepsiburada-ideasoft-logo.png"
        Glide.with(this).load(uri).into(binding.iconImage)


        binding.btnRegisterPage.setOnClickListener {
            val i = Intent(this,RegisterPage::class.java)
            startActivity(i)
        }

        var userEmail = binding.userEmailLogin.text
        var userPassword = binding.userPassword.text

        
        binding.btnLogin.setOnClickListener {
            
            if (userEmail.isNotEmpty() && userPassword.isNotEmpty()){
                
                val service = ApiClient.getClient().create(Service::class.java)
                val dataService = service.userLogin(userEmail.toString(),userPassword.toString())

                dataService.enqueue(object : Callback<UserLogin> {
                    override fun onResponse(call: Call<UserLogin>, response: Response<UserLogin>) {
                        if (response.isSuccessful) {
                            val userStatus: Boolean? = response.body()!!.user!!.get(0)!!.durum
                            if (userStatus == true) {
                                val intent = Intent(applicationContext, ProductActivity::class.java)
                                startActivity(intent)
                                UserInfo.userId = response.body()!!.user!!.get(0)!!.bilgiler!!.userId.toString()
                                finish()
                            } else {
                                Toast.makeText(applicationContext, "Kullanıcı mailiniz veya şifreniz yanlış!", Toast.LENGTH_SHORT).show()
                            }
                        } else {
                            Toast.makeText(
                                applicationContext,
                                "Service Connection Error!",
                                Toast.LENGTH_SHORT
                            )
                                .show()
                        }
                    }

                    override fun onFailure(call: Call<UserLogin>, t: Throwable) {
                        Toast.makeText(applicationContext, "Lütfen internet bağlantınızı kontrol ediniz!!", Toast.LENGTH_SHORT)
                            .show()
                    }

                })
            }else{
                Toast.makeText(applicationContext, "Kullanıcı adı ve şifre alanları boş bırakılamaz!!", Toast.LENGTH_SHORT).show()
            }
        }

    }
}