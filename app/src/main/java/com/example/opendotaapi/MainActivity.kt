package com.example.opendotaapi

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.opendotaapi.adapter.HeroAdapter
import com.example.opendotaapi.databinding.ActivityMainBinding
import com.example.opendotaapi.model.Hero
import com.example.opendotaapi.network.ApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.recyclerView.layoutManager = LinearLayoutManager(this)

        fetchHeroStats()
    }

    private fun fetchHeroStats() {
        ApiClient.instance.getHeroStats().enqueue(object : Callback<List<Hero>> {
            override fun onResponse(call: Call<List<Hero>>, response: Response<List<Hero>>) {
                if (response.isSuccessful) {
                    val heroList = response.body()
                    binding.recyclerView.adapter = heroList?.let { HeroAdapter(it) }
                }
            }

            override fun onFailure(call: Call<List<Hero>>, t: Throwable) {
                Toast.makeText(this@MainActivity, "Failed to load data", Toast.LENGTH_SHORT).show()
            }
        })
    }
}
