package com.example.akbarlaptops

import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class DetailActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val dataHero = if (Build.VERSION.SDK_INT >= 33) {
            intent.getParcelableExtra<Laptop>(key_laptop, Laptop::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getParcelableExtra<Laptop>(key_laptop)
        }
    }

    val tvDetailName = findViewById(R.id.tv_item_name)
    val tvDetailDescription = findViewById(R.id.tv_item_description)
    val tvDetailPhoto = findViewById(R.id.img_item_photo)

    tvDetailName.text = dataHero.name
    tvDetailDescription.text = dataHero.description
    ivDetailPhoto.setImageResource(dataHero.photo)
}