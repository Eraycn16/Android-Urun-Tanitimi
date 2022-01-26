package com.example.productorderapp.pages

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.productorderapp.databinding.ActivityRegisterPageBinding
import com.example.productorderapp.pages.models.UserRegister
import com.example.productorderapp.pages.services.Service
import com.example.productorderapp.pages.utils.ApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegisterPage : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterPageBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterPageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        title="Register Page"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val name = binding.registerUserName.text
        val surname = binding.registerUserSurname.text
        val mail = binding.registerUserEmail.text
        val phone = binding.registerUserPhone.text
        val pass = binding.registerUserPass.text

        binding.btnRegister.setOnClickListener {

            if (name.isNotEmpty() && surname.isNotEmpty() && mail.isNotEmpty() && phone.isNotEmpty() && pass.isNotEmpty()){

                val service = ApiClient.getClient().create(Service::class.java)
                val dataService = service.userRegister(name.toString(),surname.toString(),mail.toString(),pass.toString(),phone.toString())

                dataService.enqueue(object : Callback<UserRegister> {
                    override fun onResponse(
                        call: Call<UserRegister>,
                        response: Response<UserRegister>
                    ) {
                        if (response.isSuccessful) {
                            val registerStatus = response.body()!!.user!!.get(0)!!.durum

                            if (registerStatus == true) {

                                val intent = Intent(applicationContext, MainActivity::class.java)
                                startActivity(intent)
                                finish()
                                
                            } else {
                                Toast.makeText(
                                    applicationContext,
                                    "Kayıt işlemi başarısız!! " + response.body()!!.user!!.get(0)!!.mesaj,
                                    Toast.LENGTH_SHORT
                                ).show()
                            }


                        } else {
                            Toast.makeText(applicationContext, "Service Error!", Toast.LENGTH_SHORT)
                                .show()
                        }
                    }

                    override fun onFailure(call: Call<UserRegister>, t: Throwable) {
                        Toast.makeText(applicationContext, "Service Error!", Toast.LENGTH_SHORT)
                            .show()
                    }

                })
            }else{
                Toast.makeText(this, "Bütün alanlarını doldurduğunuzdan emin olun!!", Toast.LENGTH_SHORT).show()
            }
        }
    }
}