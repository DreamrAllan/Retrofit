package com.example.hellotoast

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.hellotoast.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
   // private lateinit var binding:ActivityMainBinding
    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
   }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.count.setOnClickListener {
            val angkaDariTextView = binding.angka.text.toString()
            var angka = angkaDariTextView.toInt() + 1
            binding.angka.text = angka.toString()
        }
        binding.minus.setOnClickListener {
            val angkaDariTextView = binding.angka.text.toString()
            var angka = angkaDariTextView.toInt() - 1
            binding.angka.text = angka.toString()
        }
        binding.toast.setOnClickListener {
            val angka = binding.angka.text.toString()
            Toast.makeText(this, "Angka Saat Ini "+ angka, Toast.LENGTH_SHORT).show()
        }

    }
}