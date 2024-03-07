package com.example.akbarlaptops

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class SplashActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        val splashScreenDuration = 2000L
        val mainIntent = Intent(this, MainActivity::class.java)

        val handler = android.os.Handler()
        handler.postDelayed({
            startActivity(mainIntent)
            finish()
        }, splashScreenDuration)
    }
}