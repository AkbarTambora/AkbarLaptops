package com.example.akbarlaptops

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var rvLaptops: RecyclerView
    private lateinit var listLaptops: ArrayList<Laptop>
    private lateinit var adapter: ListLaptopAdapter
    private var isListView: Boolean = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rvLaptops = findViewById(R.id.rv_laptops)
        rvLaptops.layoutManager = LinearLayoutManager(this)

        listLaptops = getListLaptops()
        adapter = ListLaptopAdapter(listLaptops)
        adapter.setOnItemClickListener { laptop ->
            val intent = Intent(this@MainActivity, DetailActivity::class.java)
            intent.putExtra("LAPTOP_EXTRA", laptop)
            startActivity(intent)
        }
        rvLaptops.adapter = adapter
    }

    private fun getListLaptops(): ArrayList<Laptop> {
        val dataLaptop = resources.getStringArray(R.array.data_laptop)
        val dataDescription = resources.getStringArray(R.array.data_description)
        val dataPhoto = resources.obtainTypedArray(R.array.data_photo)
        val laptops = ArrayList<Laptop>()
        for (i in dataLaptop.indices) {
            val laptop = Laptop(dataLaptop[i], dataDescription[i], dataPhoto.getResourceId(i, -1))
            laptops.add(laptop)
        }
        dataPhoto.recycle() // Recycle the typed array
        return laptops
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_list -> {
                isListView = true
                rvLaptops.layoutManager = LinearLayoutManager(this)
                adapter.notifyDataSetChanged()
            }
            R.id.action_grid -> {
                isListView = false
                rvLaptops.layoutManager = GridLayoutManager(this, 2)
                adapter.notifyDataSetChanged()
            }
            R.id.action_about_me -> {
                val intent = Intent(this, AboutActivity::class.java)
                startActivity(intent)
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}
