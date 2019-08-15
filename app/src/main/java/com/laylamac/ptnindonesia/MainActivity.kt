package com.laylamac.ptnindonesia

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.ActionBar
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    private lateinit var rvCampus: RecyclerView
    private var list: ArrayList<Campus> = arrayListOf()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setActionBarTitle("")

        rvCampus = findViewById(R.id.rv_campus)
        rvCampus.setHasFixedSize(true)

        val data = CampusData.listData
        Log.e("layla", "data:\n${data.joinToString("\n\n")}")
        list.addAll(data)
        showRecyclerList()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        setMode(item.itemId)
        return super.onOptionsItemSelected(item)
    }

    private fun setMode(selectedMode: Int) {
        when (selectedMode) {
            R.id.about -> {
                val goToAbout = Intent(this@MainActivity, AboutActivity::class.java)
                startActivity(goToAbout)
            }
        }
    }


    private fun showRecyclerList() {
        rvCampus.layoutManager = LinearLayoutManager(this)
        val listCampusAdapter = ListViewAdapter(list)
        rvCampus.adapter = listCampusAdapter

        listCampusAdapter.setOnItemClickCallback(object : ListViewAdapter.OnItemClickCallback {
            override fun onItemClicked(data: Campus) {
                showSelectedCampus(data)
            }
        })
    }

    private fun showSelectedCampus(data: Campus) {
        Toast.makeText(this, "Kamu memilih " + data.name, Toast.LENGTH_SHORT).show()
    }

    private fun setActionBarTitle(title: String) {
        if (supportActionBar != null) {
            (supportActionBar as ActionBar).title = title
        }
    }
}
