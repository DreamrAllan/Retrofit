package com.example.disasterapp

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.disasterapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val adapterDisaster = DisasterAdapter(generateDataDummy()){
            disaster ->
            Toast.makeText(this@MainActivity, "You click on ${disaster.name}",
                Toast.LENGTH_SHORT).show()
        }
        with(binding) {
            rvDisaster.apply {
                adapter = adapterDisaster
                layoutManager = GridLayoutManager(this@MainActivity, 2)
            }
        }

    } fun generateDataDummy() : List<Disaster> {
        return listOf(
            Disaster(name = "Volcanic Eruption", type = "Natural"),
            Disaster(name = "Earthquake", type = "Natural"),
            Disaster(name = "War", type = "man-made"),
            Disaster(name = "Nuclear Accident", type = "man-made")
        )
    }
}