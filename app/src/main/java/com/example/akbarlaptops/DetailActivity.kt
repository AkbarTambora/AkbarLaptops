package com.example.akbarlaptops

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class DetailActivity : AppCompatActivity() {

    private lateinit var laptop: Laptop

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val imgDetailPhoto = findViewById<ImageView>(R.id.img_detail_photo)
        val tvDetailName = findViewById<TextView>(R.id.tv_detail_name)
        val tvDetailDescription = findViewById<TextView>(R.id.tv_detail_description)

        laptop = intent.getParcelableExtra("LAPTOP_EXTRA") ?: return

        imgDetailPhoto.setImageResource(laptop.photo)
        tvDetailName.text = laptop.name
        tvDetailDescription.text = laptop.description
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_detail, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_share -> {
                shareLaptop()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun shareLaptop() {
        val shareIntent = Intent(Intent.ACTION_SEND)
        shareIntent.type = "text/plain"
        shareIntent.putExtra(Intent.EXTRA_TEXT, laptop.description)
        startActivity(Intent.createChooser(shareIntent, "Share laptop via"))
    }
}
