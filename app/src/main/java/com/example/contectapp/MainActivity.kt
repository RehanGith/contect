package com.example.contectapp

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.contectapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val array: ArrayList<String> = ArrayList()
        array.add("A")
        array.add("B")
        array.add("C")
        array.add("D")
        array.add("E")
        binding.rvContactsList.adapter = ContactAdapter(this, array)
        binding.rvContactsList.layoutManager = LinearLayoutManager(this)
        binding.rvContactsList.setHasFixedSize(true)
        binding.rvContactsList.setItemViewCacheSize(10)

    }
}