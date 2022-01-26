package com.example.productorderapp.pages

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.AnimationUtils
import com.bumptech.glide.Glide
import com.example.productorderapp.R
import com.example.productorderapp.databinding.ActivitySplashScreenBinding
import java.util.*

class SplashScreenActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySplashScreenBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val uri = "https://www.ideasoft.com.tr/wp-content/uploads/2021/10/hepsiburada-ideasoft-logo.png"
        Glide.with(this).load(uri).into(binding.splashImage)

        Timer().schedule(Task(this),3000)
        var slide = true
        if (slide){
            val animation = AnimationUtils.loadAnimation(this, R.anim.slide_down)
            binding.splashImage.startAnimation(animation)
            slide = false
        }

    }

    class Task(val activity: SplashScreenActivity): TimerTask(){
        override fun run() {
            val i = Intent(activity,MainActivity::class.java)
            activity.startActivity(i)
            activity.finish()
        }

    }
}