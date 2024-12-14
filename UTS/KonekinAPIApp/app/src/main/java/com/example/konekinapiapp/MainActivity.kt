package com.example.konekinapiapp

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.konekinapiapp.databinding.ActivityMainBinding
import com.example.konekinapiapp.model.Authors
import com.example.konekinapiapp.network.ApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val client = ApiClient.getInstance()
        val responseAuthors = client.getAllAuthors()
        val authorsList = ArrayList<String>()

        responseAuthors.enqueue(object : Callback<List<Authors>>{
            override fun onResponse(p0: Call<List<Authors>>, p1: Response<List<Authors>>) {
                for (i in p1.body()!!) {
                    authorsList.add(i.name)
                }
                val  listAdapter = ArrayAdapter(this@MainActivity, android.R.layout.simple_list_item_1,
                    authorsList)

                binding.lvNama.adapter = listAdapter
            }

            override fun onFailure(p0: Call<List<Authors>>, p1: Throwable) {
                Toast.makeText(this@MainActivity, "koneksi error", Toast.LENGTH_LONG).show()
            }

        })

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}