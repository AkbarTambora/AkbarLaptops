package com.example.akbarlaptops

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var rvLaptops: RecyclerView
    private val list = ArrayList<Laptop>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rvLaptops = findViewById(R.id.rv_laptops)
        rvLaptops.setHasFixedSize(true)

        list.addAll(getListLaptops())
        showRecyclerList()

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_list -> {
                rvLaptops.layoutManager = LinearLayoutManager(this)
            }
            R.id.action_grid -> {
                rvLaptops.layoutManager = GridLayoutManager(this, 2)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun showSelectedLaptop(laptop: Laptop) {
        Toast.makeText(this, "Kamu memilih " + laptop.name, Toast.LENGTH_SHORT).show()
    }

    private fun getListLaptops(): ArrayList<Laptop> {
        val dataLaptop = resources.getStringArray(R.array.data_laptop)
        val dataDescription = resources.getStringArray(R.array.data_description)
        val dataPhoto = resources.obtainTypedArray(R.array.data_photo)
        val listLaptop = ArrayList<Laptop>()
        for (i in dataLaptop.indices) {
            val hero = Laptop(dataLaptop[i], dataDescription[i], dataPhoto.getResourceId(i, -1))
            listLaptop.add(hero)
        }
        return listLaptop
    }



    private fun showRecyclerList() {
        rvLaptops.layoutManager = LinearLayoutManager(this)
        val listHeroAdapter = ListLaptopAdapter(list)
        rvLaptops.adapter = listHeroAdapter

        listHeroAdapter.setOnItemClickCallback(object : ListLaptopAdapter.OnItemClickCallback {
            override fun onItemClicked(data: Laptop) {
                showSelectedLaptop(data)
            }
        })
    }


}
